package com.imshhui.core;

/**
 * User: liyulin
 * Date: 2020/7/29
 */
public interface HttpStatus {
    String OK = "HTTP/1.1 200 OK";
    String MOVED_PERMANENTLY = "HTTP/1.1 301 Moved Permanently";
    String BAD_REQUEST = "HTTP/1.1 400 Bad Request";
    String NOT_FOUND = "HTTP/1.1 404 Not Found";
    String INTERNAL_SERVER_ERROR = "HTTP/1.1 500 Internal Server Error";
}
