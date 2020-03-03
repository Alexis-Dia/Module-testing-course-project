package com.moduleTesting.portal.rest.controllers;

import com.moduleTesting.portal.dto.UserDto;
import com.moduleTesting.portal.service.user.UserService;
import exceptions.UserAlreadyExistsException;
import exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.moduleTesting.portal.consts.Common.*;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @GetMapping("/authenticate")
    public UserDto authenticate(@RequestParam("emailAddress") String emailAddress, @RequestParam("password") String password) {

        Optional<UserDto> userByLogin = userService.findByLogin(emailAddress);

        if (!userByLogin.isPresent()) {
            throw new UserNotFoundException(MSG_ERR_USER_WASN_T_FOUND);
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String decodedPassword = userByLogin.get().getPassword();
        if (!bCryptPasswordEncoder.matches(password, decodedPassword)) {
            throw new UserNotFoundException(MSG_ERR_INCORRECT_PASSORD);
        }

        userByLogin.get().setPassword(password);
        return userByLogin.get();
    }

    @PostMapping("/checkUser")
    public void checkUser(@RequestBody UserDto userDto) {

        Optional<UserDto> userByLogin = userService.findByLogin(userDto.getEmailAddress());

        if (userByLogin.isPresent()) {
            throw new UserAlreadyExistsException(MSG_ERR_USER_ALREADY_EXISTS);
        }

    }
}
