package com.tomtre.shoppinglist.backend.service;

import com.tomtre.shoppinglist.backend.entity.Product;
import com.tomtre.shoppinglist.backend.exception.ProductExistsException;
import com.tomtre.shoppinglist.backend.exception.ProductNotFoundException;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<Product> findProductsOrderByCreateDateTime(long userId);

    Product getProduct(UUID productId, long userId) throws ProductNotFoundException;

    void updateProduct(Product product, long userId);

    void addProduct(Product product, long userId) throws ProductExistsException;

    void deleteProduct(UUID productId, long userId);

    void checkProduct(UUID productId, long userId);

    void uncheckProduct(UUID productId, long userId);
}
