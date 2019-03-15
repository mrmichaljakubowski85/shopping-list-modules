package com.tomtre.shoppinglist.backend.controller;

import com.tomtre.shoppinglist.backend.entity.Product;
import com.tomtre.shoppinglist.backend.entity.User;
import com.tomtre.shoppinglist.backend.exception.BadRequestException;
import com.tomtre.shoppinglist.backend.exception.ProductExistsException;
import com.tomtre.shoppinglist.backend.exception.ProductNotFoundException;
import com.tomtre.shoppinglist.backend.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public String listProducts(Principal principal, Model model) {
        List<Product> products = productService.findProductsOrderByCreateDateTime(getUserIdFromPrincipal(principal));
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
    public String editProduct(Principal principal, @RequestParam("productId") UUID productId, Model model) throws ProductNotFoundException {
        Product product = productService.getProduct(productId, getUserIdFromPrincipal(principal));
        model.addAttribute("product", product);
        return "add-edit-product-form";
    }

    @PostMapping("/save")
    public String saveProduct(Principal principal, @ModelAttribute("product") Product product) throws ProductExistsException {
         if (product.getId() == null) {
            productService.addProduct(product, getUserIdFromPrincipal(principal));
        } else {
            productService.updateProduct(product, getUserIdFromPrincipal(principal));
        }
        return "redirect:/product/list";
    }

    @GetMapping("/delete")
    public String deleteProduct(Principal principal, @RequestParam("productId") UUID productId) {
        productService.deleteProduct(productId, getUserIdFromPrincipal(principal));
        return "redirect:/product/list";
    }

    @GetMapping("/details")
    public String productDetails(Principal principal, @RequestParam("productId") UUID productId, Model model) throws ProductNotFoundException {
        Product product = productService.getProduct(productId, getUserIdFromPrincipal(principal));
        model.addAttribute("product", product);
        return "product-details";
    }

    @GetMapping("/check")
    public String checkProduct(Principal principal, HttpSession httpSession, @RequestParam("productId") UUID productId, @RequestParam("action") String actionType) throws BadRequestException {
        switch (actionType) {
            case "check":
                productService.checkProduct(productId, getUserIdFromPrincipal(principal));
                break;
            case "uncheck":
                productService.uncheckProduct(productId, getUserIdFromPrincipal(principal));
                break;
            default:
                throw new BadRequestException();
        }
        return "redirect:/product/list";
    }

    private long getUserIdFromPrincipal(Principal principal) {

        return
    }
}
