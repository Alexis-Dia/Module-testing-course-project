package com.moduleTesting.rest.controllers;

import org.springframework.web.bind.annotation.*;

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

}
