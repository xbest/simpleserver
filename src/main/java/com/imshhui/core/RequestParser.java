package com.imshhui.core;

import java.io.BufferedReader;

/**
 * User: liyulin
 */
public class RequestParser {
    private String[] httpRequestLine;
    private String lastModified;

    public RequestParser(BufferedReader reader) throws Exception {
        if (reader == null) {
            throw new Exception("Not enough data received");
        }
        httpRequestLine = reader.readLine().split(" ");
        String line;
        while (!(line = reader.readLine()).isEmpty()) {
            String[] strings = line.split(":", 2);
            if (strings.length == 2 && "If-Modified-Since".equals(strings[0])) {
                lastModified = strings[1].trim();
            }
        }
    }

    public String getMethod() {
        return httpRequestLine[0];
    }

    public String getURI() {
        return httpRequestLine[1].replaceAll("%20"," ");
    }

    public String getVersion() {
        return httpRequestLine[2];
    }

    public String getLastModified() {
        return lastModified;
    }
}
