package com.imshhui.core;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

/**
 * User: liyulin
 */
public class HttpResponse {
    private String status;
    private String server;
    private String contentType;
    private OutputStream outputStream;
    private byte[] content;
    private String lastModified;

    public HttpResponse(String status, String server, String contentType, OutputStream outputStream, byte[] content,
                        String lastModified) {
        this.status = status;
        this.server = server;
        this.contentType = contentType;
        this.outputStream = outputStream;
        this.content = content;
        this.lastModified = lastModified;
    }

    public void send() throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(status).append("\r\n");
        sb.append(server).append("\r\n");
        sb.append(contentType).append("\r\n");
        sb.append("Last-Modified: ").append(lastModified).append("\r\n");
        if (Objects.nonNull(content)) {
            sb.append("Content-Length: " + content.length).append("\r\n");
        }
        sb.append("\r\n");
        byte[] header = sb.toString().getBytes();
        outputStream.write(header, 0, header.length);
        if (Objects.nonNull(content)) {
            outputStream.write(content, 0, content.length);
        }
    }

    public static Builder builder() {
        return new HttpResponse.Builder();
    }

    public static class Builder {
        private String status = HttpStatus.OK;
        private String server = ServerConfig.SERVER_NAME;
        private String contentType = ContentType.TEXT_PLAIN;
        private OutputStream outputStream;
        private byte[] content;
        private String lastModified;

        public Builder() {
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder server(String server) {
            this.server = server;
            return this;
        }

        public Builder contentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public Builder outputStream(OutputStream outputStream) {
            this.outputStream = outputStream;
            return this;
        }

        public Builder content(byte[] content) {
            this.content = content;
            return this;
        }

        public Builder lastModified(String lastModified) {
            this.lastModified = lastModified;
            return this;
        }

        public HttpResponse build() {
            return new HttpResponse(status, server, contentType, outputStream, content, lastModified);
        }
    }
}
