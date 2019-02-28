package com.tomtre.shoppinglist.backend.exception;

import java.util.UUID;

public class BaseProductException extends Exception {
    private final UUID productId;

    public BaseProductException(UUID productId) {
        this.productId = productId;
    }

    public BaseProductException(String message, UUID productId) {
        super(message);
        this.productId = productId;
    }

    public BaseProductException(String message, Throwable cause, UUID productId) {
        super(message, cause);
        this.productId = productId;
    }

    public BaseProductException(Throwable cause, UUID productId) {
        super(cause);
        this.productId = productId;
    }

    public BaseProductException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, UUID productId) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.productId = productId;
    }

    public UUID getProductId() {
        return productId;
    }
}
