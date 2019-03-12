package com.tomtre.shoppinglist.backend.service;

import com.tomtre.shoppinglist.backend.SecurityRoles;
import com.tomtre.shoppinglist.backend.dao.RoleDao;
import com.tomtre.shoppinglist.backend.dao.UserDao;
import com.tomtre.shoppinglist.backend.dto.RegisterUserDto;
import com.tomtre.shoppinglist.backend.entity.Role;
import com.tomtre.shoppinglist.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean checkIfUserNameExists(String userName) {
        return userDao.checkIfUserNameExists(userName);
    }

    @Override
    public boolean checkIfEmailExists(String email) {
        return userDao.checkIfEmailExists(email);
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    @Override
    public void save(RegisterUserDto registerUserDto) {
        User user = convertRegisterUserDtoToUser(registerUserDto);
        userDao.save(user);
    }

    private User convertRegisterUserDtoToUser(RegisterUserDto registerUserDto) {
        User user = new User();
        user.setUserName(registerUserDto.getUserName());
        user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        user.setFirstName(registerUserDto.getFirstName());
        user.setLastName(registerUserDto.getLastName());
        user.setEmail(registerUserDto.getEmail());

        Role userRole = roleDao.findRoleByName(SecurityRoles.USER);
        user.setRoles(Collections.singletonList(userRole));

        return user;
    }

    //from UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userDao.findByUserName(username);
        if (!userOptional.isPresent())
            throw new UsernameNotFoundException("Invalid username");
        return convertUserToSpringUser(userOptional.get());
    }

    private UserDetails convertUserToSpringUser(User user) {
        String userName = user.getUserName();
        String password = user.getPassword();
        Collection<? extends GrantedAuthority> grantedAuthorities = mapRolesToAuthorities(user.getRoles());
        return new org.springframework.security.core.userdetails.User(userName, password, grantedAuthorities);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
