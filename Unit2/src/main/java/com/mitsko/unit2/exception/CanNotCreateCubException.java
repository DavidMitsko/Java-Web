package com.mitsko.unit2.exception;

public class CanNotCreateCubException extends Exception {
    public CanNotCreateCubException() {
    }

    public CanNotCreateCubException(String message) {
        super(message);
    }

    public CanNotCreateCubException(String message, Throwable cause) {
        super(message, cause);
    }

    public CanNotCreateCubException(Throwable cause) {
        super(cause);
    }
}
