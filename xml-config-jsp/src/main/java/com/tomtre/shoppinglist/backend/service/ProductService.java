package com.tomtre.shoppinglist.backend.service;

import com.tomtre.shoppinglist.backend.entity.Product;
import com.tomtre.shoppinglist.backend.exception.ProductAlreadyExistsException;
import com.tomtre.shoppinglist.backend.exception.ProductNotFoundException;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<Product> getProducts();

    Product getProduct(UUID productId) throws ProductNotFoundException;

    void updateProduct(Product product);

    void addProduct(Product product) throws ProductAlreadyExistsException;

    void deleteProduct(UUID productId);

    void checkProduct(UUID productId);

    void uncheckProduct(UUID productId);
}
