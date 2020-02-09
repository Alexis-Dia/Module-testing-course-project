package com.moduleTesting.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController {

    @PostMapping("/load")
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
