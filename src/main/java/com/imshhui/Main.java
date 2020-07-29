package com.imshhui;

import com.imshhui.core.SimpleHttpServer;

/**
 * User: liyulin
 * Date: 2020/7/28
 */
public class Main {
    public static void main(String[] args) {
        SimpleHttpServer server = new SimpleHttpServer();
        server.start();
    }
}
