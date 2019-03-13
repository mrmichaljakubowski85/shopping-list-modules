package com.tomtre.shoppinglist.backend.controller;

import com.tomtre.shoppinglist.backend.entity.Product;
import com.tomtre.shoppinglist.backend.exception.BadRequestException;
import com.tomtre.shoppinglist.backend.exception.ProductExistsException;
import com.tomtre.shoppinglist.backend.exception.ProductNotFoundException;
import com.tomtre.shoppinglist.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
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
    public String listProducts(HttpSession httpSession, Principal principal, Model model) {
        List<Product> products = productService.getProducts();
        String userName = principal.getName();
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
    public String editProduct(@RequestParam("productId") UUID productId, Model model) throws ProductNotFoundException {
        Product product = productService.getProduct(productId);
        model.addAttribute("product", product);
        return "add-edit-product-form";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product) throws ProductExistsException {
        if (product.getId() == null) {
            productService.addProduct(product);
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
    public String productDetails(@RequestParam("productId") UUID productId, Model model) throws ProductNotFoundException {
        Product product = productService.getProduct(productId);
        model.addAttribute("product", product);
        return "product-details";
    }

    @GetMapping("/check")
    public String checkProduct(@RequestParam("productId") UUID productId, @RequestParam("action") String actionType) throws BadRequestException {
        switch (actionType) {
            case "check":
                productService.checkProduct(productId);
                break;
            case "uncheck":
                productService.uncheckProduct(productId);
                break;
            default:
                throw new BadRequestException();
        }
        return "redirect:/product/list";
    }

}
