package com.imshhui.core;

/**
 * User: liyulin
 * Date: 2020/7/29
 */
public interface ContentType {
    String TEXT_HTML = "Content-Type: text/html; charset=UTF-8";
    String IMAGE_JPEG = "Content-Type: image/jpeg";
    String OCTET_STREAM = "Content-Type: application/octet-stream";
    String TEXT_PLAIN = "Content-Type: text/plain; charset=UTF-8";

    static String guessType(String filePath) {
        if (filePath.endsWith("jpg") || filePath.endsWith("jpeg")) {
            return IMAGE_JPEG;
        } else if (filePath.endsWith("zip")) {
            return OCTET_STREAM;
        } else {
            return TEXT_PLAIN;
        }
    }
}
