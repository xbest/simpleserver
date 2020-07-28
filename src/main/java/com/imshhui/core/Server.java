package com.imshhui.core;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: liyulin
 * Date: 2020/7/28
 */
public class Server implements Runnable {
    private static final int PORT = 9090;
    private static final String ADDRESS = "localhost";
    public static final String BASE_PATH = "D:\\";

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(ADDRESS, PORT));
            while (!Thread.interrupted()) {
                new Thread(new RequestHandler(serverSocket.accept())).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        new Thread(this).start();
    }
}
