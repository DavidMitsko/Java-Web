package main.java.com.mitsko.unit2.exception;

public class CanNotCreateCubException extends Throwable {
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
