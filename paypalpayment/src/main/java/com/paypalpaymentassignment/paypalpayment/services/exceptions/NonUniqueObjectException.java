package com.paypalpaymentassignment.paypalpayment.services.exceptions;

public class NonUniqueObjectException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    
    public NonUniqueObjectException(String message) {
        super(message);
    }

    public NonUniqueObjectException(String message, Throwable cause) {
        super(message, cause);
    }
}
