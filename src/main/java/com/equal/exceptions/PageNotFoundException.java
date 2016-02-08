package com.equal.exceptions;


public class PageNotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 974268056759132490L;

    public PageNotFoundException() {
    }

    public PageNotFoundException(String message) {
        super(message);
    }

    public PageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PageNotFoundException(Throwable cause) {
        super(cause);
    }
}
