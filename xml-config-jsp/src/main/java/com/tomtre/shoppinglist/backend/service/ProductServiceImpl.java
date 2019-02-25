package com.tomtre.shoppinglist.backend.service;

import com.tomtre.shoppinglist.backend.dao.ProductDAO;
import com.tomtre.shoppinglist.backend.entity.Product;
import com.tomtre.shoppinglist.backend.exception.ProductAlreadyExistsException;
import com.tomtre.shoppinglist.backend.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public List<Product> getProducts() {
        return productDAO.getProducts();
    }

    @Override
    public Product getProduct(UUID productId) throws ProductNotFoundException {
        Product product = productDAO.getProduct(productId);
        if (product == null)
            throw new ProductNotFoundException("Product with ID not found: " + productId);
        return product;
    }

    @Override
    public void update(Product product) {
        productDAO.updateProduct(product);
    }

    @Override
    public void add(Product product) throws ProductAlreadyExistsException {
        UUID id = product.getId();
        if (id == null) {
            product.setId(UUID.randomUUID());
            productDAO.saveProduct(product);
        } else {
            //make sure we don't have conflict with IDs
            Product productFromRepository = productDAO.getProduct(id);
            if (productFromRepository == null) {
                productDAO.saveProduct(product);
            } else {
                throw new ProductAlreadyExistsException("Product with ID already exists: " + id);
            }
        }
    }

    @Override
    public void deleteProduct(UUID productId) {
        productDAO.removeProduct(productId);
    }

}
