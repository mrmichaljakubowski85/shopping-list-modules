package com.tomtre.shoppinglist.backend.restcontroller;

import com.tomtre.shoppinglist.backend.config.RestServiceDescriptor;
import com.tomtre.shoppinglist.backend.dto.CustomSecurityUser;
import com.tomtre.shoppinglist.backend.entity.Product;
import com.tomtre.shoppinglist.backend.exception.ProductExistsException;
import com.tomtre.shoppinglist.backend.exception.ProductNotFoundException;
import com.tomtre.shoppinglist.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    public List<Product> getProducts(Principal principal) {
        return productService.findProductsOrderByCreateDateTime(getUserId(principal));
    }

    @GetMapping("/products/{productId}")
    public Product getProduct(Principal principal, @PathVariable UUID productId) throws ProductNotFoundException {
        return productService.getProduct(productId,getUserId(principal));
    }

    @PostMapping("/products")
    public Product addProduct(Principal principal, @RequestBody Product product) throws ProductExistsException {
        productService.addProduct(product, getUserId(principal));
        return product;
    }

    @PutMapping("/products")
    public Product updateProduct(Principal principal, @RequestBody Product product) {
        productService.updateProduct(product, getUserId(principal));
        return product;
    }

    @DeleteMapping("/products/{productId}")
    //todo what to return?
    public void deleteProduct(Principal principal, @PathVariable UUID productId) {
        productService.deleteProduct(productId, getUserId(principal));
    }

    private long getUserId(Principal principal) {
        return ((CustomSecurityUser) principal).getId();
    }

}
