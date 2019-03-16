package com.tomtre.shoppinglist.backend.controller;

import com.tomtre.shoppinglist.backend.config.security.CustomAuthenticationSuccessHandler;
import com.tomtre.shoppinglist.backend.dto.CustomSecurityUser;
import com.tomtre.shoppinglist.backend.entity.Product;
import com.tomtre.shoppinglist.backend.exception.BadRequestException;
import com.tomtre.shoppinglist.backend.exception.ProductExistsException;
import com.tomtre.shoppinglist.backend.exception.ProductNotFoundException;
import com.tomtre.shoppinglist.backend.service.ProductService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

//todo change to post (becasue of csrf)
@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list")
    public String listProducts(HttpSession httpSession, Model model) {
        List<Product> products = productService.findProductsOrderByCreateDateTime(getUserId(httpSession));
        model.addAttribute("products", products);
        return "product-list";
    }

    @GetMapping("/add")
    public String addProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "add-edit-product-form";
    }

    @PostMapping("/edit")
    public String editProduct(HttpSession httpSession, @RequestParam("productId") UUID productId, Model model)
            throws ProductNotFoundException {
        Product product = productService.getProduct(productId, getUserId(httpSession));
        model.addAttribute("product", product);
        return "add-edit-product-form";
    }

    @PostMapping("/save")
    public String saveProduct(HttpSession httpSession, @ModelAttribute("product") @Valid Product product, BindingResult bindingResult) throws ProductExistsException {
        if (bindingResult.hasErrors()) {
            return "add-edit-product-form";
        }
        if (product.getId() == null) {
            productService.addProduct(product, getUserId(httpSession));
        } else {
            productService.updateProduct(product, getUserId(httpSession));
        }
        return "redirect:/product/list";
    }

    @PostMapping("/delete")
    public String deleteProduct(HttpSession httpSession, @RequestParam("productId") UUID productId) {
        productService.deleteProduct(productId, getUserId(httpSession));
        return "redirect:/product/list";
    }

    @PostMapping("/details")
    public String productDetails(HttpSession httpSession, @RequestParam("productId") UUID productId, Model model) throws ProductNotFoundException {
        Product product = productService.getProduct(productId, getUserId(httpSession));
        model.addAttribute("product", product);
        return "product-details";
    }

    @PostMapping("/check")
    public String checkProduct(HttpSession httpSession, @RequestParam("productId") UUID productId, @RequestParam("action") String actionType) throws BadRequestException {
        switch (actionType) {
            case "check":
                productService.checkProduct(productId, getUserId(httpSession));
                break;
            case "uncheck":
                productService.uncheckProduct(productId, getUserId(httpSession));
                break;
            default:
                throw new BadRequestException();
        }
        return "redirect:/product/list";
    }

    private long getUserId(HttpSession session) {
        CustomSecurityUser customSecurityUser = (CustomSecurityUser) session.getAttribute(CustomAuthenticationSuccessHandler.PRINCIPAL_SESSION_ATTRIBUTE_NAME);
        return customSecurityUser.getId();
    }
}
