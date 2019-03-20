package com.tomtre.shoppinglist.backend.restcontroller;

import com.tomtre.shoppinglist.backend.config.RestServiceDescriptor;
import com.tomtre.shoppinglist.backend.dto.CustomSecurityUser;
import com.tomtre.shoppinglist.backend.entity.Product;
import com.tomtre.shoppinglist.backend.exception.ProductExistsException;
import com.tomtre.shoppinglist.backend.exception.ProductNotFoundException;
import com.tomtre.shoppinglist.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public List<Product> getProducts(@AuthenticationPrincipal CustomSecurityUser customSecurityUser) {
        return productService.findProductsOrderByCreateDateTime(customSecurityUser.getId());
    }

    @GetMapping("/products/{productId}")
    public Product getProduct(@AuthenticationPrincipal CustomSecurityUser customSecurityUser, @PathVariable UUID productId) throws ProductNotFoundException {
        return productService.getProduct(productId, customSecurityUser.getId());
    }

    @PostMapping("/products")
    public Product addProduct(@AuthenticationPrincipal CustomSecurityUser customSecurityUser, @RequestBody Product product) throws ProductExistsException {
        productService.addProduct(product, customSecurityUser.getId());
        return product;
    }

    @PutMapping("/products")
    public Product updateProduct(@AuthenticationPrincipal CustomSecurityUser customSecurityUser, @RequestBody Product product) {
        productService.updateProduct(product, customSecurityUser.getId());
        return product;
    }

    @DeleteMapping("/products/{productId}")
    public UUID deleteProduct(@AuthenticationPrincipal CustomSecurityUser customSecurityUser, @PathVariable UUID productId) {
        productService.deleteProduct(productId, customSecurityUser.getId());
        return productId;
    }

}
