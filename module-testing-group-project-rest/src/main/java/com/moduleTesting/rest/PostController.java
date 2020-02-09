package com.moduleTesting.rest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/post", method = RequestMethod.GET)
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
