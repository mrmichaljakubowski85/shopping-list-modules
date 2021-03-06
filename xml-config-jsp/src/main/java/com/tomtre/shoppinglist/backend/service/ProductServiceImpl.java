package com.tomtre.shoppinglist.backend.service;

import com.tomtre.shoppinglist.backend.dao.ProductDao;
import com.tomtre.shoppinglist.backend.entity.Product;
import com.tomtre.shoppinglist.backend.entity.User;
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

    private final ProductDao productDAO;

    @Autowired
    public ProductServiceImpl(ProductDao productDAO) {
        this.productDAO = productDAO;
    }


    @Override
    public List<Product> findProductsOrderByCreateDateTime(long userId) {
        return productDAO.findProductsOrderByCreateDateTime(userId);
    }

    @Override
    public Product getProduct(UUID productId, long userId) throws ProductNotFoundException {
        Optional<Product> productOptional = productDAO.findProduct(productId, userId);
        if (productOptional.isPresent()) {
            return productOptional.get();
        } else {
            throw new ProductNotFoundException("Product with ID not found: " + productId, productId);
        }
    }

    @Override
    public void updateProduct(Product product, long userId) {
        setUserRelation(product, userId);
        productDAO.updateProduct(product);
    }

    @Override
    public void addProduct(Product product, long userId) throws ProductExistsException {
        setUserRelation(product, userId);
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
    public void deleteProduct(UUID productId, long userId) {
        productDAO.removeProduct(productId, userId);
    }

    @Override
    public void checkProduct(UUID productId, long userId) {
        productDAO.checkProduct(productId, userId);
    }

    @Override
    public void uncheckProduct(UUID productId, long userId) {
        productDAO.uncheckProduct(productId, userId);
    }

    private void setUserRelation(Product product, long userId) {
        User user = new User();
        user.setId(userId);
        product.setUser(user);
    }
}
