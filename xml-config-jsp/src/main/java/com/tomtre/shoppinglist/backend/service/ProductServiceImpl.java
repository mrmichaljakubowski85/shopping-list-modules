package com.tomtre.shoppinglist.backend.service;

import com.tomtre.shoppinglist.backend.dao.ProductDAO;
import com.tomtre.shoppinglist.backend.entity.Product;
import com.tomtre.shoppinglist.backend.exception.ProductExistsException;
import com.tomtre.shoppinglist.backend.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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
        Optional<Product> productOptional = productDAO.getProduct(productId);
        if (productOptional.isPresent()) {
            return productOptional.get();
        } else {
            throw new ProductNotFoundException("Product with ID not found: " + productId, productId);
        }
    }

    @Override
    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }

    @Override
    public void addProduct(Product product) throws ProductExistsException {
        UUID id = product.getId();
        if (id == null) {
            product.setId(UUID.randomUUID());
            productDAO.saveProduct(product);
        } else {
            //We have to make sure there is no conflict with IDs
            boolean productExists = productDAO.checkIfProductExists(id);
            if (productExists) {
                throw new ProductExistsException("Product with ID already exists: " + id, id);
            } else {
                productDAO.saveProduct(product);
            }
        }
    }

    @Override
    public void deleteProduct(UUID productId) {
        productDAO.removeProduct(productId);
    }

    @Override
    public void checkProduct(UUID productId) {
        productDAO.checkProduct(productId);
    }

    @Override
    public void uncheckProduct(UUID productId) {
        productDAO.uncheckProduct(productId);
    }

}
