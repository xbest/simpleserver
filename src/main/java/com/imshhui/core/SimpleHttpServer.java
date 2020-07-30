package com.imshhui.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: liyulin
 */
public class SimpleHttpServer implements Runnable {
    private static final ExecutorService pool = Executors.newFixedThreadPool(10);

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(ServerConfig.PORT);
            while (!Thread.interrupted()) {
                pool.execute(new RequestHandler(serverSocket.accept()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        new Thread(this).start();
    }
}
