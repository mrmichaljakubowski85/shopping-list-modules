package com.tomtre.shoppinglist.backend.dao;

import com.tomtre.shoppinglist.backend.entity.Product;

import java.util.List;
import java.util.UUID;


public interface ProductDAO {

    List<Product> getProducts();

    Product getProduct(UUID productId);

    void removeProduct(UUID productId);

    void saveProduct(Product product);

    void updateProduct(Product product);

    void checkProduct(UUID productId);

    void uncheckProduct(UUID productId);
}
