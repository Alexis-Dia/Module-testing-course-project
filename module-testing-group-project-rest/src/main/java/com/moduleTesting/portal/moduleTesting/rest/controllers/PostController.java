package com.moduleTesting.portal.moduleTesting.rest.controllers;

import com.moduleTesting.portal.dto.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/post")
public class PostController {

    @GetMapping("/load")
    public String getPosts() {

        /*Page<PostDto> lll = null;
        try{
            lll = service.getProductPage(filter);
        } catch (RuntimeException e) {
            System.out.println(e);
        }

        return lll;*/

        return "hello";
    }

    @GetMapping("/all")
    public User getTasks() {

        /*Page<PostDto> lll = null;
        try{
            lll = service.getProductPage(filter);
        } catch (RuntimeException e) {
            System.out.println(e);
        }

        return lll;*/

        return new User("aaa", "bbb");
    }

}
