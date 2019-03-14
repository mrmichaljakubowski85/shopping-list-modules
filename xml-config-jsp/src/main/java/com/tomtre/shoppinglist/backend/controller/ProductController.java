package com.tomtre.shoppinglist.backend.controller;

import com.tomtre.shoppinglist.backend.entity.Product;
import com.tomtre.shoppinglist.backend.entity.User;
import com.tomtre.shoppinglist.backend.exception.BadRequestException;
import com.tomtre.shoppinglist.backend.exception.ProductExistsException;
import com.tomtre.shoppinglist.backend.exception.ProductNotFoundException;
import com.tomtre.shoppinglist.backend.service.ProductService;
import com.tomtre.shoppinglist.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list")
    public String listProducts(HttpSession httpSession, Model model) {
        List<Product> products = productService.getProductsByUserId(getUserIdFromSession(httpSession));
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
    public String editProduct(HttpSession httpSession, @RequestParam("productId") UUID productId, Model model) throws ProductNotFoundException {
        Product product = productService.getProduct(productId, getUserIdFromSession(httpSession));
        model.addAttribute("product", product);
        return "add-edit-product-form";
    }

    @PostMapping("/save")
    public String saveProduct(HttpSession httpSession, @ModelAttribute("product") Product product) throws ProductExistsException {
        User tempUser = new User();
        tempUser.setId(getUserIdFromSession(httpSession));
        product.setUser(tempUser);
        if (product.getId() == null) {
            productService.addProduct(product);
        } else {
            productService.updateProduct(product);
        }
        return "redirect:/product/list";
    }

    @GetMapping("/delete")
    public String deleteProduct(HttpSession httpSession, @RequestParam("productId") UUID productId) {
        productService.deleteProduct(productId, getUserIdFromSession(httpSession));
        return "redirect:/product/list";
    }

    private long getUserIdFromSession(HttpSession httpSession) {
        return (long) httpSession.getAttribute("userId");
    }

    @GetMapping("/details")
    public String productDetails(HttpSession httpSession, @RequestParam("productId") UUID productId, Model model) throws ProductNotFoundException {
        Product product = productService.getProduct(productId, getUserIdFromSession(httpSession));
        model.addAttribute("product", product);
        return "product-details";
    }

    @GetMapping("/check")
    public String checkProduct(HttpSession httpSession, @RequestParam("productId") UUID productId, @RequestParam("action") String actionType) throws BadRequestException {
        switch (actionType) {
            case "check":
                productService.checkProduct(productId, getUserIdFromSession(httpSession));
                break;
            case "uncheck":
                productService.uncheckProduct(productId, getUserIdFromSession(httpSession));
                break;
            default:
                throw new BadRequestException();
        }
        return "redirect:/product/list";
    }

}
