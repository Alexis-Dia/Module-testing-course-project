package com.moduleTesting.portal.rest.controllers;

import com.moduleTesting.portal.dto.UserDto;
import com.moduleTesting.portal.dto.UserRole;
import com.moduleTesting.portal.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/all")
    public List<UserDto> getAllUsers() {

        final List<UserDto> allUsers = userService.findAll();
        System.out.println(allUsers);

        return allUsers;
    }

    @PostMapping("/allDrivers")
    public List<UserDto> findAllDrivers() {

        final List<UserDto> allDrivers = userService.findAllDrivers();
        System.out.println(allDrivers);

        return allDrivers;
    }

    @PostMapping("/getById")
    public List<UserDto> getUserById() {

        final List<UserDto> allDrivers = userService.findAllDrivers();
        System.out.println(allDrivers);

        return allDrivers;
    }

    @PostMapping("/edit")
    public UserDto editUser(Integer userId, String lastName, String firstName, String patronymic, Date birthday) {

        final UserDto user = userService.editUser(userId, lastName, firstName, patronymic, birthday);
        System.out.println(user);

        return user;
    }

    @PostMapping("/changeStatus")
    public UserDto changeUserStatus(Integer userId, UserRole userRole) {

        final UserDto user = userService.changeUserStatus(userId, userRole);
        System.out.println(user);

        return user;
    }

    @PostMapping("/transferMoney")
    public void transferMoney(Integer userId, Float money) {

        userService.transferMoney(userId, money);
    }

    @PostMapping("/delete")
    public List<UserDto> deleteUser(Integer userId) {

        final List<UserDto> allUsers = userService.deleteUser(userId);
        System.out.println(allUsers);

        return allUsers;
    }

    @PostMapping("/new")
    public void createNew(String lastName, String firstName, String patronymic, Date birthday, String email,
                                   String password, Integer roleId, Integer statusId) {

        userService.createNewUser(lastName, firstName, patronymic, birthday, email,
            password, roleId, statusId);
    }
}
