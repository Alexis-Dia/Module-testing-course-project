package com.moduleTesting.portal.rest.controllers;

import com.moduleTesting.portal.dto.UserDto;
import com.moduleTesting.portal.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/all")
    public List<UserDto> getAllUsers() {

        final List<UserDto> allUsers = userService.findAll();
        System.out.println(allUsers);

        return allUsers;
    }
}
