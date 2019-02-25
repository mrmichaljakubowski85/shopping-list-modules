package com.tomtre.shoppinglist.backend.controller;

import com.tomtre.shoppinglist.backend.entity.Product;
import com.tomtre.shoppinglist.backend.exception.ProductAlreadyExistsException;
import com.tomtre.shoppinglist.backend.exception.ProductNotFoundException;
import com.tomtre.shoppinglist.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list")
    public String listProducts(Model model) {
        List<Product> products = productService.getProducts();
        model.addAttribute("products", products);
        return "list-products";
    }

    @GetMapping("/add")
    public String addProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "product-form";
    }

    @PostMapping("/saveProduct")
    public String addOrUpdateProduct(@ModelAttribute("product") Product product) {
        if (product.getId() == null) {
            try {
                productService.add(product);
            } catch (ProductAlreadyExistsException e) {
                //todo show custom page with error
            }
        } else {
            productService.update(product);
        }
        return "redirect:/product/list";
    }

    @GetMapping("/update")
    public String updateProduct(@RequestParam("productId") UUID productId, Model model) {
        Product product = null;
        try {
            product = productService.getProduct(productId);
            model.addAttribute("product", product);
        } catch (ProductNotFoundException e) {
            //todo show custom page with error
        }
        return "product-form";
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam("productId") UUID productId) {
        productService.deleteProduct(productId);
        return "redirect:/product/list";
    }
}
