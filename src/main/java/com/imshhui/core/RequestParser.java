package com.imshhui.core;

import java.io.BufferedReader;

/**
 * User: liyulin
 * Date: 2020/7/28
 */
public class RequestParser {
    private String[] httpRequestLine;

    public RequestParser(BufferedReader reader) throws Exception {
        if (reader == null) {
            throw new Exception("Not enough data received");
        }
        httpRequestLine = reader.readLine().split(" ");
    }

    public String getMethod() {
        return httpRequestLine[0];
    }

    public String getURI() {
        return httpRequestLine[1];
    }

    public String getVersion() {
        return httpRequestLine[2];
    }
}
