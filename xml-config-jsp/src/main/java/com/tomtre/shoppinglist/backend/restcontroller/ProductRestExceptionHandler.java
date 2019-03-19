package com.tomtre.shoppinglist.backend.restcontroller;

import com.tomtre.shoppinglist.backend.controller.ProductExceptionHandler;
import com.tomtre.shoppinglist.backend.exception.ProductExistsException;
import com.tomtre.shoppinglist.backend.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.logging.Logger;

@ControllerAdvice(basePackages = "com.tomtre.shoppinglist.backend.restcontroller")
public class ProductRestExceptionHandler {

    public static final Logger logger = Logger.getLogger(ProductExceptionHandler.class.getName());

    @ExceptionHandler(ProductExistsException.class)
    public ResponseEntity<ProductErrorResponse> handleProductAlreadyExistsException(ProductExistsException e) {
        ProductErrorResponse productErrorResponse = new ProductErrorResponse(
                HttpStatus.CONFLICT.value(),
                e.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(productErrorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductErrorResponse> handleProductNotFoundException(ProductNotFoundException e) {
        ProductErrorResponse productErrorResponse = new ProductErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(productErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler

    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> handleException(Exception e) {
        logger.warning("REST Exception occurred: " + e.toString());
        ProductErrorResponse productErrorResponse = new ProductErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                System.currentTimeMillis());
        return new ResponseEntity<>(productErrorResponse, HttpStatus.BAD_REQUEST);
    }

}
