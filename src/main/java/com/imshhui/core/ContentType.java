package com.imshhui.core;

/**
 * User: liyulin
 */
public interface ContentType {
    String CONTENT_TYPE = "Content-Type: ";
    String CHARSET = " charset=UTF-8";
    String TEXT_HTML = CONTENT_TYPE + "text/html;" + CHARSET;
    String TEXT_XML = CONTENT_TYPE + "text/xml;" + CHARSET;
    String IMAGE_JPEG = CONTENT_TYPE + "image/jpeg";
    String OCTET_STREAM = CONTENT_TYPE + "application/octet-stream";
    String TEXT_PLAIN = CONTENT_TYPE + "text/plain;" + CHARSET;
    String PDF = CONTENT_TYPE + "application/pdf";

    static String guessType(String filePath) {
        if (filePath.endsWith("jpg") || filePath.endsWith("jpeg")) {
            return IMAGE_JPEG;
        } else if (filePath.endsWith("zip")) {
            return OCTET_STREAM;
        } else if (filePath.endsWith("xml")) {
            return TEXT_XML;
        } else if (filePath.endsWith("html") || filePath.endsWith("htm")) {
            return TEXT_HTML;
        } else if (filePath.endsWith("pdf")) {
            return PDF;
        }
        return TEXT_PLAIN;
    }
}
