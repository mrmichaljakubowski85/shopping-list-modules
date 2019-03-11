package com.tomtre.shoppinglist.backend.service;

import com.tomtre.shoppinglist.backend.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUserName(String userName);
}
