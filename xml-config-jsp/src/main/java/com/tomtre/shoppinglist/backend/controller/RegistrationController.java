package com.tomtre.shoppinglist.backend.controller;

import com.tomtre.shoppinglist.backend.dto.RegisterUserDto;
import com.tomtre.shoppinglist.backend.entity.User;
import com.tomtre.shoppinglist.backend.service.UserService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.logging.Logger;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private Logger logger = Logger.getLogger(getClass().getName());

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/form")
    public String registerForm(Model model) {
        model.addAttribute("registerUserDto", new RegisterUserDto());
        return "registration-form";
    }

    @PostMapping("/processRegistration")
    public String processRegistration(
            @ModelAttribute("registerUserDto") @Valid RegisterUserDto registerUserDto,
            BindingResult bindingResult,
            Model model) {

        String userName = registerUserDto.getUserName();
//        logger.info("Processing registration form for: " + userName);

        //validation
        if (bindingResult.hasErrors())
            return "registration-form";

        boolean userNameExists = userService.checkIfUserNameExists(userName);
        if (userNameExists) {
//            logger.warning("User name already exists.");
            return handleUserOrEmailExists(model);
        }

        String email = registerUserDto.getEmail();
        boolean emailExists = userService.checkIfEmailExists(email);
        if (emailExists) {
//            logger.warning("Email already exists.");
            return handleUserOrEmailExists(model);
        }

        //create new account
        userService.save(registerUserDto);

//        logger.info("Successfully created user: " + userName);

        return "registration-confirmation";
    }

    private String handleUserOrEmailExists(Model model) {
        model.addAttribute("registerUserDto", new RegisterUserDto());
        model.addAttribute("registrationError", "User name or email already exists.");
        return "registration-form";
    }
}
