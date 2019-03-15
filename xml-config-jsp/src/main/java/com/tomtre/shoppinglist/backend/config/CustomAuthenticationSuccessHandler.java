package com.tomtre.shoppinglist.backend.config;

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

    private final UserService userService;

    public CustomAuthenticationSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        String userName = authentication.getName();
        logger.info(">>> onAuthenticationSuccess, before findByUserName");
        Optional<User> userOptional = userService.findByUserName(userName);

        //store in the httpSession
        HttpSession httpSession = request.getSession(false);
        if (httpSession != null && userOptional.isPresent()) {
            User user = userOptional.get();
            httpSession.setAttribute("user", user);
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
