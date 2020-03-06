package com.moduleTesting.portal.rest.controllers;

import com.moduleTesting.portal.dto.UserDto;
import com.moduleTesting.portal.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<UserDto> getAllUsers() {

        final List<UserDto> allUsers = userService.findAll();

        return allUsers;
    }

    @GetMapping("/allDrivers")
    public List<UserDto> findAllDrivers() {

        final List<UserDto> allDrivers = userService.findAllDrivers();

        return allDrivers;
    }

    @GetMapping("/getById")
    public UserDto getDriverById(@RequestParam("id") Integer userId) {

        final UserDto userDto = userService.getDriverById(userId);

        return userDto;
    }

    @GetMapping("/getMe")
    public String getMe(@RequestParam("id") Integer userId) {

        final String authenticationName = SecurityContextHolder.getContext().getAuthentication().getName();

        userService.getDriverByNameNew_testMandotaryRequiredNewTransaction();

        return "userDto";
    }

    @GetMapping("/getAdmin")
    public UserDto geAdmin() {

        final UserDto userDto = userService.getAdmin();

        return userDto;
    }

    @PutMapping("/edit")
    public UserDto editUser(@RequestBody UserDto userDto) {

        final UserDto user = userService.editUser(userDto);

        return user;
    }

    @PutMapping("/editMe")
    public UserDto editCurrentUser(@RequestBody UserDto userDto) {

        final String authenticationName = SecurityContextHolder.getContext().getAuthentication().getName();

        final UserDto user = userService.editMe(userDto, authenticationName);

        return user;
    }

    @PutMapping("/changeStatus")
    public UserDto changeUserStatus(@RequestBody UserDto userDto) {

        final UserDto user = userService.changeUserStatus(userDto.getUserID(), userDto.getUserStatus().getId());

        return user;
    }

    @DeleteMapping("/delete")
    public List<UserDto> deleteUser(@RequestParam("id") Integer userId) {

        final List<UserDto> allUsers = userService.deleteUser(userId);

        return allUsers;
    }

    @PostMapping("/signUp")
    public void createNew(@RequestBody UserDto userDto) {

        //TODO - Try to create it using autowiring
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userService.createNewUser(userDto);

    }

}
