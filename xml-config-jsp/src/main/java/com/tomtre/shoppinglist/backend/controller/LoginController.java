package com.tomtre.shoppinglist.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class LoginController {

    @GetMapping("/login")
    public String loginForm(HttpServletRequest request, Principal principal) {
        return principal == null ?  "login-form" : "redirect:/";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }
}
