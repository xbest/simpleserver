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
            String filePath = Server.BASE_PATH + parser.getURI();
            if (filePath.endsWith("jpg") || filePath.endsWith("jpeg")) {
                File img = new File(filePath);
                byte[] imgBytes = Files.readAllBytes(img.toPath());
                writer.println("HTTP/1.1 200 OK");
                writer.println("Server: imshhui");
                writer.println("Content-Type: image/jpeg");
                writer.println("Content-Length: " + imgBytes.length);
                writer.println("");
                socket.getOutputStream().write(imgBytes, 0, imgBytes.length);
            } else if (filePath.endsWith("zip")) {
                writer.println("Content-Type: application/octet-stream");
            } else {
                localReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
                writer.println("HTTP/1.1 200 OK");
                writer.println("Server: imshhui");
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
            writer.println("HTTP/1.1 500");
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
