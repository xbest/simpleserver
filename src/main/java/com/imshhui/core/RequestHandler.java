package com.imshhui.core;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;

/**
 * User: liyulin
 * Date: 2020/7/28
 */
public class RequestHandler implements Runnable {
    private Socket socket;

    public RequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        BufferedReader localReader = null;
        PrintWriter writer = null;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());
            RequestParser parser = new RequestParser(reader);
            String uri = parser.getURI();
            String filePath = SimpleHttpServer.BASE_PATH + uri;
            File file = new File(filePath);
            if (file.isDirectory()) {
                writer.println(HttpStatus.OK);
                writer.println("Server: solar");
                writer.println("Content-Type: text/html; charset=UTF-8");
                writer.println("");
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
                sb.append("<hr>");
                writer.println(sb.toString());
                writer.println("");
            } else if (filePath.endsWith("jpg") || filePath.endsWith("jpeg")) {
                byte[] imgBytes = Files.readAllBytes(file.toPath());
                writer.println(HttpStatus.OK);
                writer.println("Server: solar");
                writer.println("Content-Type: image/jpeg");
                writer.println("Content-Length: " + imgBytes.length);
                writer.println("");
                socket.getOutputStream().write(imgBytes, 0, imgBytes.length);
            } else if (filePath.endsWith("zip")) {
                byte[] imgBytes = Files.readAllBytes(file.toPath());
                writer.println(HttpStatus.OK);
                writer.println("Server: solar");
                writer.println("Content-Type: application/octet-stream");
                writer.println("Content-Length: " + imgBytes.length);
                writer.println("");
                socket.getOutputStream().write(imgBytes, 0, imgBytes.length);
            } else {
                localReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
                writer.println(HttpStatus.OK);
                writer.println("Server: solar");
                writer.println("Content-Type: text/plain; charset=UTF-8");
                writer.println("");
                String line;
                while ((line = localReader.readLine()) != null) {
                    writer.println(line);
                }
            }
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
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
