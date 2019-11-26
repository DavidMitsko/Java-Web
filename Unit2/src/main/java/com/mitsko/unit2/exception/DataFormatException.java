package com.mitsko.unit2.exception;

public class DataFormatException extends Exception {
    public DataFormatException() {
    }

    public DataFormatException(String message) {
        super(message);
    }

    public DataFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataFormatException(Throwable cause) {
        super(cause);
    }
}
