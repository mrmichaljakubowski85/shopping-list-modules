package com.tomtre.shoppinglist.backend.exception;

public class ProductAlreadyExistsException extends Exception {
    public ProductAlreadyExistsException() {
        super();
    }

    public ProductAlreadyExistsException(String message) {
        super(message);
    }

    public ProductAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    protected ProductAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
