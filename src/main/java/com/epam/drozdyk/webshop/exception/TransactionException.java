package com.epam.drozdyk.webshop.exception;

public class TransactionException extends ApplicationException {

    public TransactionException(String message) {
        super(message);
    }

    public TransactionException(String message, Throwable cause) {
        super(message, cause);
    }
}
