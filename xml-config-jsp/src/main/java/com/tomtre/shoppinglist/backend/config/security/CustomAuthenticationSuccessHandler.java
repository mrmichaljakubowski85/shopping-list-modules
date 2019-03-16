package com.tomtre.shoppinglist.backend.config.security;

import com.tomtre.shoppinglist.backend.entity.User;
import com.tomtre.shoppinglist.backend.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    public static final String PRINCIPAL_SESSION_ATTRIBUTE_NAME = "customSecurityUser";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        HttpSession httpSession = request.getSession(false);
        if (httpSession != null) {
            httpSession.setAttribute(PRINCIPAL_SESSION_ATTRIBUTE_NAME, authentication.getPrincipal());
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
