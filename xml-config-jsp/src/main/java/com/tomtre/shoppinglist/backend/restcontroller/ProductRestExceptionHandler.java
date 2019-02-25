package com.tomtre.shoppinglist.backend.restcontroller;

import com.tomtre.shoppinglist.backend.exception.ProductAlreadyExistsException;
import com.tomtre.shoppinglist.backend.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> handleException(ProductAlreadyExistsException e) {
        ProductErrorResponse productErrorResponse = new ProductErrorResponse(
                //todo check status
                HttpStatus.CONFLICT.value(),
                e.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(productErrorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> handleException(ProductNotFoundException e) {
        ProductErrorResponse productErrorResponse = new ProductErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(productErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> handleException(Exception e) {
        ProductErrorResponse productErrorResponse = new ProductErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                System.currentTimeMillis());
        return new ResponseEntity<>(productErrorResponse, HttpStatus.BAD_REQUEST);
    }

}
