package com.tomtre.shoppinglist.backend.config;

import com.tomtre.shoppinglist.backend.entity.User;
import com.tomtre.shoppinglist.backend.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final UserService userService;

    public CustomAuthenticationSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String userName = authentication.getName();
        User loggedUser = userService.findByUserName(userName);

        //store in the httpSession
        HttpSession httpSession = request.getSession(false);
        if (httpSession != null) {
            httpSession.setAttribute("user", loggedUser);
        }

        //forward to home page
        response.sendRedirect(request.getContextPath() + "/");
    }
}
