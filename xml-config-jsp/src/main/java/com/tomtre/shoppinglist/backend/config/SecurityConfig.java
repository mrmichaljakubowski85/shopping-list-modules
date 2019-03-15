package com.tomtre.shoppinglist.backend.config;

import com.tomtre.shoppinglist.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    public SecurityConfig(UserService userService, PasswordEncoder passwordEncoder, CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/product/**").hasRole(SecurityRoles.USER)
                .antMatchers("/api/**").hasRole(SecurityRoles.USER)
                .and()
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/authenticateTheUser")
                    .successHandler(customAuthenticationSuccessHandler)
                    .permitAll()
                .and()
                .logout()
//                    .logoutSuccessHandler(customLogoutSuccessHandler)
                    .permitAll()
                .and()
                .exceptionHandling()
                    .accessDeniedPage("/access-denied");
    }


    private DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }
}
