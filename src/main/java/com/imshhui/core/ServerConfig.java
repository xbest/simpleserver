package com.imshhui.core;

import java.io.IOException;
import java.util.Properties;

/**
 * User: liyulin
 */
public class ServerConfig {
    static int PORT = 9066;
    static String BASE_PATH = "/";
    static boolean DIRECTORY_LISTING = false;
    static String SERVER_NAME = "Server: solar";

    static {
        Properties properties = new Properties();
        try {
            properties.load(ServerConfig.class.getResourceAsStream("/server.properties"));
            PORT = Integer.valueOf(properties.getProperty("port"));
            BASE_PATH = properties.getProperty("base_path");
            DIRECTORY_LISTING = Boolean.valueOf(properties.getProperty("directory_listing"));
            SERVER_NAME = properties.getProperty("server_name");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
