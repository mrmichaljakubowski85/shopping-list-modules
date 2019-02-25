package com.tomtre.shoppinglist.backend.service;

import com.tomtre.shoppinglist.backend.entity.Product;
import com.tomtre.shoppinglist.backend.exception.ProductAlreadyExistsException;
import com.tomtre.shoppinglist.backend.exception.ProductNotFoundException;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<Product> getProducts();

    void update(Product product);

    void add(Product product) throws ProductAlreadyExistsException;

    Product getProduct(UUID productId) throws ProductNotFoundException;

    void deleteProduct(UUID productId);
}
