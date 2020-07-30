package com.imshhui.core;

/**
 * User: liyulin
 */
public interface HttpStatus {
    String OK = "HTTP/1.1 200 OK";
    String MOVED_PERMANENTLY = "HTTP/1.1 301 Moved Permanently";
    String NOT_MODIFIED = "HTTP/1.1 304 Not Modified";
    String BAD_REQUEST = "HTTP/1.1 400 Bad Request";
    String FORBIDDEN = "HTTP/1.1 403 Forbidden";
    String NOT_FOUND = "HTTP/1.1 404 Not Found";
    String METHOD_NOT_ALLOWED = "HTTP/1.1 405 Method Not Allowed";
    String INTERNAL_SERVER_ERROR = "HTTP/1.1 500 Internal Server Error";
    String HTTP_VERSION_NOT_SUPPORTED = "HTTP/1.1 505 HTTP Version Not Supported";
}
