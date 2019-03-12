package com.tomtre.shoppinglist.backend.controller;

import com.tomtre.shoppinglist.backend.exception.BadRequestException;
import com.tomtre.shoppinglist.backend.exception.ProductExistsException;
import com.tomtre.shoppinglist.backend.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice(basePackages = "com.tomtre.shoppinglist.backend.controller")
public class ProductExceptionHandler {

    @ExceptionHandler(ProductExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleProductExistsException(ProductExistsException e, Model model) {
        model.addAttribute("productId", e.getProductId().toString());
        return "error/product-exists-error";
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleProductNotFoundException(ProductNotFoundException e, Model model) {
        model.addAttribute("productId", e.getProductId().toString());
        return "error/product-not-found-error";
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBadRequestException() {
        return "error/bad-request-error";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleException() {
        return "error/global-error";
    }
}
