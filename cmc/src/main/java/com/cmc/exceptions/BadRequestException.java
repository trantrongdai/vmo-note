package com.cmc.exceptions;

/**
 * Bad request exception class
 */
public class BadRequestException extends RuntimeException {

    private Object data;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public Object getData() {
        return data;
    }

    public BadRequestException setData(Object data) {
        this.data = data;
        return this;
    }
}
