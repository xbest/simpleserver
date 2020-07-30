package com.imshhui.core;

import java.io.IOException;
import java.util.Properties;

/**
 * User: liyulin
 * Date: 2020/7/29
 */
public class ServerConfig {
    static int PORT = 9066;
    static String BASE_PATH = "/";
    static boolean DIRECTORY_LISTING = false;

    static {
        Properties properties = new Properties();
        try {
            properties.load(ServerConfig.class.getResourceAsStream("/server.properties"));
            PORT = Integer.valueOf(properties.getProperty("port"));
            BASE_PATH = properties.getProperty("base_path");
            DIRECTORY_LISTING = Boolean.valueOf(properties.getProperty("directory_listing"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
