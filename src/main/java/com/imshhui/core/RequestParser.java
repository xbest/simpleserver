package com.imshhui.core;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * User: liyulin
 * Date: 2020/7/28
 */
public class RequestParser {
    private String[] headers;

    public RequestParser(BufferedReader reader) throws IOException {
        headers = reader.readLine().split(" ");
    }

    public String getMethod() {
        return headers[0];
    }

    public String getURI() {
        return headers[1];
    }

    public String getVersion() {
        return headers[2];
    }
}
