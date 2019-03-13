package com.tomtre.shoppinglist.backend.service;

import com.tomtre.shoppinglist.backend.entity.Product;
import com.tomtre.shoppinglist.backend.exception.ProductExistsException;
import com.tomtre.shoppinglist.backend.exception.ProductNotFoundException;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<Product> getProductsByUserId(long userId);

    Product getProduct(UUID productId) throws ProductNotFoundException;

    void updateProduct(Product product);

    void addProduct(Product product) throws ProductExistsException;

    void deleteProduct(UUID productId);

    void checkProduct(UUID productId);

    void uncheckProduct(UUID productId);
}
