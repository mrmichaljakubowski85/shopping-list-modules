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
        return "product-list";
    }

    @GetMapping("/add")
    public String addProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "add-edit-product-form";
    }

    @GetMapping("/edit")
    public String editProduct(@RequestParam("productId") UUID productId, Model model) {
        try {
            Product product = productService.getProduct(productId);
            model.addAttribute("product", product);
            return "add-edit-product-form";
        } catch (ProductNotFoundException e) {
            //todo show custom page with error
            return null;
        }
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product) {
        if (product.getId() == null) {
            try {
                productService.addProduct(product);
            } catch (ProductAlreadyExistsException e) {
                //todo show custom page with error
//                return "redirect:/product/errorrrrr";
            }
        } else {
            productService.updateProduct(product);
        }
        return "redirect:/product/list";
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam("productId") UUID productId) {
        productService.deleteProduct(productId);
        return "redirect:/product/list";
    }

    @GetMapping("/details")
    public String productDetails(@RequestParam("productId") UUID productId, Model model) {
        try {
            Product product = productService.getProduct(productId);
            model.addAttribute("product", product);
            return "product-details";
        } catch (ProductNotFoundException e) {
            //todo show custom page with error
            return null;
        }
    }

    @GetMapping("/check")
    public String checkProduct(@RequestParam("productId") UUID productId, @RequestParam("type") boolean check) {
        if (check) {
            productService.checkProduct(productId);
        } else {
            productService.uncheckProduct(productId);
        }
        return "redirect:/product/list";
    }

}
