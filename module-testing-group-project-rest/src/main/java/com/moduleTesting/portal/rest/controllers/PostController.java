package com.moduleTesting.portal.rest.controllers;

import com.moduleTesting.portal.dto.User;
import com.moduleTesting.portal.entity.CarStatus;
import com.moduleTesting.portal.service.carStatus.CarStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/post")
public class PostController {

    private final CarStatusService carStatusService;

    @Autowired
    public PostController(CarStatusService carStatusService) {
        this.carStatusService = carStatusService;
    }

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

        final List<CarStatus> all = carStatusService.findAll();
        System.out.println(all);
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
