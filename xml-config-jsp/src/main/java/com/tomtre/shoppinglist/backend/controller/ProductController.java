package com.tomtre.shoppinglist.backend.controller;

import com.tomtre.shoppinglist.backend.dto.CustomSecurityUser;
import com.tomtre.shoppinglist.backend.entity.Product;
import com.tomtre.shoppinglist.backend.exception.BadRequestException;
import com.tomtre.shoppinglist.backend.exception.ProductExistsException;
import com.tomtre.shoppinglist.backend.exception.ProductNotFoundException;
import com.tomtre.shoppinglist.backend.service.ProductService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

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

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/list")
    public String listProducts(@AuthenticationPrincipal CustomSecurityUser customSecurityUser, Model model) {
        List<Product> products = productService.findProductsOrderByCreateDateTime(customSecurityUser.getId());
        model.addAttribute("products", products);
        return "product-list";
    }

    @GetMapping("/add")
    public String addProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "add-edit-product";
    }

    @PostMapping("/edit")
    public String editProduct(@AuthenticationPrincipal CustomSecurityUser customSecurityUser, @RequestParam("productId") UUID productId, Model model)
            throws ProductNotFoundException {
        Product product = productService.getProduct(productId, customSecurityUser.getId());
        model.addAttribute("product", product);
        return "add-edit-product";
    }

    @PostMapping("/save")
    public String saveProduct(@AuthenticationPrincipal CustomSecurityUser customSecurityUser, @ModelAttribute("product") @Valid Product product, BindingResult bindingResult) throws ProductExistsException {
        if (bindingResult.hasErrors()) {
            return "add-edit-product";
        }
        if (product.getId() == null) {
            productService.addProduct(product, customSecurityUser.getId());
        } else {
            productService.updateProduct(product, customSecurityUser.getId());
        }
        return "redirect:/product/list";
    }

    @PostMapping("/delete")
    public String deleteProduct(@AuthenticationPrincipal CustomSecurityUser customSecurityUser, @RequestParam("productId") UUID productId) {
        productService.deleteProduct(productId, customSecurityUser.getId());
        return "redirect:/product/list";
    }

    @PostMapping("/details")
    public String productDetails(@AuthenticationPrincipal CustomSecurityUser customSecurityUser, @RequestParam("productId") UUID productId, Model model) throws ProductNotFoundException {
        Product product = productService.getProduct(productId, customSecurityUser.getId());
        model.addAttribute("product", product);
        return "product-details";
    }

    @PostMapping("/check")
    public String checkProduct(@AuthenticationPrincipal CustomSecurityUser customSecurityUser, @RequestParam("productId") UUID productId, @RequestParam("action") String actionType) throws BadRequestException {
        switch (actionType) {
            case "check":
                productService.checkProduct(productId, customSecurityUser.getId());
                break;
            case "uncheck":
                productService.uncheckProduct(productId, customSecurityUser.getId());
                break;
            default:
                throw new BadRequestException();
        }
        return "redirect:/product/list";
    }

}
