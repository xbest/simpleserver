package com.imshhui;

import com.imshhui.core.SimpleHttpServer;

/**
 * User: liyulin
 */
public class Main {
    public static void main(String[] args) {
        SimpleHttpServer server = new SimpleHttpServer();
        server.start();
    }
}
