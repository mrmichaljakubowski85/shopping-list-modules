package com.tomtre.shoppinglist.backend.dao;

import com.tomtre.shoppinglist.backend.entity.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface ProductDao {

    List<Product> findProductsOrderByCreateDateTime(long userId);

    Optional<Product> findProduct(UUID productId, long userId);

    void removeProduct(UUID productId, long userId);

    void saveProduct(Product product);

    void updateProduct(Product product);

    void checkProduct(UUID productId, long userId);

    void uncheckProduct(UUID productId, long userId);

    boolean checkIfProductExists(UUID productId);
}
