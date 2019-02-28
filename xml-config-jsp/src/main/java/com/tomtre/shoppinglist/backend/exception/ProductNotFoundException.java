package com.tomtre.shoppinglist.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

public class ProductNotFoundException extends BaseProductException {

    public ProductNotFoundException(UUID productId) {
        super(productId);
    }

    public ProductNotFoundException(String message, UUID productId) {
        super(message, productId);
    }

    public ProductNotFoundException(String message, Throwable cause, UUID productId) {
        super(message, cause, productId);
    }

    public ProductNotFoundException(Throwable cause, UUID productId) {
        super(cause, productId);
    }

    public ProductNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, UUID productId) {
        super(message, cause, enableSuppression, writableStackTrace, productId);
    }
}
