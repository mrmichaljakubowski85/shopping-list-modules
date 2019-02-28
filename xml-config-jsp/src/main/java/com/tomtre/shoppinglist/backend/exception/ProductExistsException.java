package com.tomtre.shoppinglist.backend.exception;

import java.util.UUID;

public class ProductExistsException extends BaseProductException {

    public ProductExistsException(UUID productId) {
        super(productId);
    }

    public ProductExistsException(String message, UUID productId) {
        super(message, productId);
    }

    public ProductExistsException(String message, Throwable cause, UUID productId) {
        super(message, cause, productId);
    }

    public ProductExistsException(Throwable cause, UUID productId) {
        super(cause, productId);
    }

    public ProductExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, UUID productId) {
        super(message, cause, enableSuppression, writableStackTrace, productId);
    }
}
