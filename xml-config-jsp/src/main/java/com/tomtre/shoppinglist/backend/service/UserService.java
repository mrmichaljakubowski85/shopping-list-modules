package com.tomtre.shoppinglist.backend.service;

import com.tomtre.shoppinglist.backend.dto.RegisterUserDto;
import com.tomtre.shoppinglist.backend.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    boolean checkIfUserNameExists(String userName);

    boolean checkIfEmailExists(String email);

    Optional<User> findByUserName(String userName);

    void save(RegisterUserDto registerUserDto);

}
