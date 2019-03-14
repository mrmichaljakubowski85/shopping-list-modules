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
import java.util.Optional;
import java.util.logging.Logger;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger logger = Logger.getLogger(CustomAuthenticationSuccessHandler.class.getName());

    private final UserService userService;

    public CustomAuthenticationSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        logger.info("User logged in: authentication: " + authentication);
        String userName = authentication.getName();
        Optional<User> userOptional = userService.findByUserName(userName);

        //store in the httpSession
        HttpSession httpSession = request.getSession(false);
        if (httpSession != null && userOptional.isPresent()) {
            User user = userOptional.get();
            httpSession.setAttribute("userName", user.getUserName());
            httpSession.setAttribute("name", user.getFirstName() + " " + user.getLastName());
            httpSession.setAttribute("userId", user.getId());
        }

        //forward to home page
        response.sendRedirect(request.getContextPath() + "/");
    }
}
