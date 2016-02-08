package com.equal.exceptions;


public class XlsDataNotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -1837621891842141562L;

    public XlsDataNotFoundException() {
    }

    public XlsDataNotFoundException(String message) {
        super(message);
    }

    public XlsDataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public XlsDataNotFoundException(Throwable cause) {
        super(cause);
    }
}
