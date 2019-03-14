package com.tomtre.shoppinglist.backend.dao;

import com.tomtre.shoppinglist.backend.entity.User;

import java.util.Optional;

public interface UserDao {

    boolean checkIfUserNameExists(String userName);

    boolean checkIfEmailExists(String email);

    Optional<User> findByUserName(String userName);

    void save(User user);

    Optional<User> findWithRolesByUserName(String userName);
}
