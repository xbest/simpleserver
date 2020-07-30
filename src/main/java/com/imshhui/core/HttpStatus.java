package com.imshhui.core;

/**
 * User: liyulin
 */
public interface HttpStatus {
    String VERSION = "HTTP/1.1";
    String OK = VERSION + " 200 OK";
    String MOVED_PERMANENTLY = VERSION + " 301 Moved Permanently";
    String NOT_MODIFIED = VERSION + " 304 Not Modified";
    String BAD_REQUEST = VERSION + " 400 Bad Request";
    String FORBIDDEN = VERSION + " 403 Forbidden";
    String NOT_FOUND = VERSION + " 404 Not Found";
    String METHOD_NOT_ALLOWED = VERSION + " 405 Method Not Allowed";
    String INTERNAL_SERVER_ERROR = VERSION + " 500 Internal Server Error";
    String HTTP_VERSION_NOT_SUPPORTED = VERSION + " 505 HTTP Version Not Supported";
}
