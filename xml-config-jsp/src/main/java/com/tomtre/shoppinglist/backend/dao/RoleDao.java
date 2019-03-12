package com.tomtre.shoppinglist.backend.dao;

import com.tomtre.shoppinglist.backend.entity.Role;

public interface RoleDao {
    Role findRoleByName(String user);
}
