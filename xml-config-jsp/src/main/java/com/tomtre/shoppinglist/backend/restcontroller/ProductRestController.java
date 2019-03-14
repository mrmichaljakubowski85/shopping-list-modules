package com.tomtre.shoppinglist.backend.restcontroller;

import com.tomtre.shoppinglist.backend.config.RestServiceDescriptor;
import com.tomtre.shoppinglist.backend.entity.Product;
import com.tomtre.shoppinglist.backend.exception.ProductExistsException;
import com.tomtre.shoppinglist.backend.exception.ProductNotFoundException;
import com.tomtre.shoppinglist.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(RestServiceDescriptor.FULL_PATH)
public class ProductRestController {

    private final ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        //todo change
        return productService.getProductsByUserId(0);
    }

    @GetMapping("/products/{productId}")
    public Product getProduct(@PathVariable UUID productId) throws ProductNotFoundException {
        //todo change
        return productService.getProduct(productId, 0);
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) throws ProductExistsException {
        //todo product need User (id)
        productService.addProduct(product);
        return product;
    }

    @PutMapping("/products")
    public Product updateProduct(@RequestBody Product product) {
        //todo product need User (id)
        productService.updateProduct(product);
        return product;
    }

    @DeleteMapping("/products/{productId}")
    //todo what to return?
    //todo change paramter
    public void deleteProduct(@PathVariable UUID productId) {
        productService.deleteProduct(productId, 0);
    }
}
