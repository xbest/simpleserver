package com.imshhui.core;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;

/**
 * User: liyulin
 * Date: 2020/7/28
 */
public class RequestHandler implements Runnable {
    private static final String SERVER = "Server: solar";
    private Socket socket;

    public RequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        BufferedReader localReader = null;
        PrintWriter writer = null;
        OutputStream outputStream = null;
        try {
            outputStream = socket.getOutputStream();
            HttpResponse.Builder responseBuilder = HttpResponse.builder();
            responseBuilder.status(HttpStatus.OK).server(SERVER).outputStream(outputStream);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());
            RequestParser parser = new RequestParser(reader);
            String uri = parser.getURI();
            String filePath = SimpleHttpServer.BASE_PATH + uri;
            File file = new File(filePath);
            if (file.isDirectory()) {
                StringBuilder sb = new StringBuilder();
                String path = file.getPath();
                sb.append("<!DOCTYPE html>\r\n");
                sb.append("<html><head><title>");
                sb.append("Listing of: ");
                sb.append(path);
                sb.append("</title></head><body>\r\n");
                sb.append("<h2>Directory listing for ");
                sb.append(path);
                sb.append("</h2>\r\n");
                sb.append("<hr>");
                sb.append("<ul>");
                sb.append("<li><a href=\"..");
                sb.append("/".equals(uri) ? "/" : uri.substring(0, uri.lastIndexOf("/")));
                sb.append("\">..</a></li>\r\n");
                for (File f : file.listFiles()) {
                    if (!f.canRead()) {
                        continue;
                    }
                    String name = f.getName();
                    sb.append("<li><a href=\"..");
                    sb.append("/".equals(uri) ? "" : uri);
                    sb.append("\\" + name);
                    sb.append("\">");
                    sb.append(name);
                    sb.append("</a></li>\r\n");
                }
                sb.append("</ul></body></html>\r\n");
                sb.append("<hr>\r\n");
                sb.append("\r\n");
//                writer.println(sb.toString());
//                writer.println("");
                responseBuilder.contentType(ContentType.TEXT_HTML).content(sb.toString().getBytes());

            } else if (filePath.endsWith("jpg") || filePath.endsWith("jpeg")) {
                responseBuilder.contentType(ContentType.IMAGE_JPEG).content(Files.readAllBytes(file.toPath()));
            } else if (filePath.endsWith("zip")) {
                responseBuilder.contentType(ContentType.OCTET_STREAM).content(Files.readAllBytes(file.toPath()));
            } else {
                responseBuilder.contentType(ContentType.TEXT_PLAIN).content(Files.readAllBytes(file.toPath()));
            }
            responseBuilder.build().send();
        } catch (Exception e) {
            writer.println(HttpStatus.INTERNAL_SERVER_ERROR);
            writer.println("");
            writer.flush();
        } finally {
            close(reader, localReader, writer);
        }
    }

    private void close(Closeable... closeables) {
        for (Closeable closeable : closeables) {
            try {
                if (closeable != null) {
                    closeable.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
