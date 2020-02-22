package com.moduleTesting.portal.rest.security.impl;

import com.moduleTesting.portal.dto.UserDto;
import com.moduleTesting.portal.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    private static final String USER_NOT_FOUND = "User not found";

    @Autowired
    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    public UserDto loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserDto user = userService.findByLogin(username);
            return user;
        } catch (Exception e) {

            throw new UsernameNotFoundException(USER_NOT_FOUND, e);
        }
    }
}