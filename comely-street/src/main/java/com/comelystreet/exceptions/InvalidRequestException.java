package com.comelystreet.exceptions;

public class InvalidRequestException extends Exception {

    /**
 * 
 */
    private static final long serialVersionUID = -187488266722250374L;

    public InvalidRequestException() {
        super();
    }

    public InvalidRequestException(final String msg) {
        super(msg);
    }

    public InvalidRequestException(final Throwable t) {
        super(t);
    }

    public InvalidRequestException(final String msg, final Throwable t) {
        super(msg, t);
    }
}
