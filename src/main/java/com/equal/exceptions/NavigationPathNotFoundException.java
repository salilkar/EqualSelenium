package com.equal.exceptions;

public class NavigationPathNotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 5701831114941089052L;

    public NavigationPathNotFoundException() {
    }

    public NavigationPathNotFoundException(String message) {
        super(message);
    }

    public NavigationPathNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NavigationPathNotFoundException(Throwable cause) {
        super(cause);
    }
}
