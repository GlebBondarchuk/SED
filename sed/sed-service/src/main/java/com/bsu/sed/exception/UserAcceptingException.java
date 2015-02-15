package com.bsu.sed.exception;

/**
 * @author gbondarchuk
 */

public class UserAcceptingException extends RuntimeException {
    private static final long serialVersionUID = 7584920685473205954L;

    public UserAcceptingException() {
        super();
    }

    public UserAcceptingException(String message) {
        super(message);
    }

    public UserAcceptingException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAcceptingException(Throwable cause) {
        super(cause);
    }
}

