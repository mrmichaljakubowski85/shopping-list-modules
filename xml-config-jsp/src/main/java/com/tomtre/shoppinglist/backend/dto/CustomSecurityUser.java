package com.tomtre.shoppinglist.backend.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomSecurityUser extends User {

    private final long id;
    private final String fullName;



}
