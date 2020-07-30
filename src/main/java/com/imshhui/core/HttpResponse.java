package com.imshhui.core;

import java.io.IOException;
import java.io.OutputStream;

/**
 * User: liyulin
 * Date: 2020/7/29
 */
public class HttpResponse {
    private String status;
    private String server;
    private String contentType;
    private OutputStream outputStream;
    private byte[] content;

    public HttpResponse(String status, String server, String contentType, OutputStream outputStream, byte[] content) {
        this.status = status;
        this.server = server;
        this.contentType = contentType;
        this.outputStream = outputStream;
        this.content = content;
    }

    public void send() throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(status).append("\r\n");
        sb.append(server).append("\r\n");
        sb.append(contentType).append("\r\n");
        sb.append("Content-Length: " + content.length).append("\r\n");
        sb.append("\r\n");
        byte[] header = sb.toString().getBytes();
        outputStream.write(header, 0, header.length);
        if (content != null) {
            outputStream.write(content, 0, content.length);
        }
    }

    public static Builder builder() {
        return new HttpResponse.Builder();
    }

    public static class Builder {
        private String status;
        private String server = "Server: solar";
        private String contentType;
        private OutputStream outputStream;
        private byte[] content;

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

        public HttpResponse build() {
            return new HttpResponse(status, server, contentType, outputStream, content);
        }
    }
}
