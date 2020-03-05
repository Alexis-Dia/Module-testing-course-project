package com.moduleTesting.portal.rest.controllers;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@Controller
public class IndexController
{
    @GetMapping("/")
    public String index(Principal principal)
    {
        if(principal != null)
        {
            ((OAuth2Authentication) principal).setAuthenticated(true);
            return "You've successfully logged in";
        }
        return "No authenticated";
    }
}