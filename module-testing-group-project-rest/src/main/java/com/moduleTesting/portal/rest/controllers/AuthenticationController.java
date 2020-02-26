package com.moduleTesting.portal.rest.controllers;

import com.moduleTesting.portal.dto.UserDto;
import com.moduleTesting.portal.service.user.UserService;
import exceptions.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.moduleTesting.portal.consts.Common.MSG_ERR_USER_ALREADY_EXISTS;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @GetMapping("/authenticate")
    public void authenticate(@RequestBody UserDto userDto) {

        String emailAddress = userDto.getEmailAddress();
        Optional<UserDto> userByLogin = userService.findByLogin(emailAddress);

        if (userByLogin.isPresent()) {
            throw new UserAlreadyExistsException(MSG_ERR_USER_ALREADY_EXISTS);
        }

    }
}
