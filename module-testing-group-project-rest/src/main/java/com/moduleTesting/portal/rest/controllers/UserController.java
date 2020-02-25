package com.moduleTesting.portal.rest.controllers;

import com.moduleTesting.portal.dto.UserDto;
import com.moduleTesting.portal.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/all")
    public List<UserDto> getAllUsers() {

        final List<UserDto> allUsers = userService.findAll();

        return allUsers;
    }

    @PostMapping("/allDrivers")
    public List<UserDto> findAllDrivers() {

        final List<UserDto> allDrivers = userService.findAllDrivers();

        return allDrivers;
    }

    @PostMapping("/getById")
    public UserDto getDriverById(@RequestParam("id") Integer userId) {

        final UserDto userDto = userService.getDriverById(userId);

        return userDto;
    }

    @PostMapping("/getAdmin")
    public UserDto geAdmin() {

        final UserDto userDto = userService.getAdmin();

        return userDto;
    }

    @PostMapping("/edit")
    public UserDto editUser(@RequestBody UserDto userDto) {

        final UserDto user = userService.editUser(userDto);

        return user;
    }

    @PostMapping("/changeStatus")
    public UserDto changeUserStatus(@RequestBody UserDto userDto) {

        final UserDto user = userService.changeUserStatus(userDto.getUserID(), userDto.getUserStatus().getId());

        return user;
    }

    @PostMapping("/delete")
    public List<UserDto> deleteUser(@RequestParam("id") Integer userId) {

        final List<UserDto> allUsers = userService.deleteUser(userId);

        return allUsers;
    }

    @PostMapping("/new")
    public void createNew(@RequestBody UserDto userDto) {

        userService.createNewUser(userDto);

    }

    @PostMapping("/transferMoney")
    public void transferMoney(@RequestParam("userId") Integer userId, @RequestParam("money") Float money) {

        userService.transferMoney(userId, money);
    }

}
