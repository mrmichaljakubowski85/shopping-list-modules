package com.tomtre.shoppinglist.backend.restcontroller;

public class ProductErrorResponse {
    private final int status;
    private final String message;
    private final long timeStamp;

    public ProductErrorResponse(int status, String message, long timeStamp) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public long getTimeStamp() {
        return timeStamp;
    }
}
