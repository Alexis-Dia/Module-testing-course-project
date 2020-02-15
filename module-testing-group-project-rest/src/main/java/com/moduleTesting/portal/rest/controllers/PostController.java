package com.moduleTesting.portal.rest.controllers;

import com.moduleTesting.portal.dto.UserDto;
import com.moduleTesting.portal.entity.*;
import com.moduleTesting.portal.service.car.CarService;
import com.moduleTesting.portal.service.carStatus.CarStatusService;
import com.moduleTesting.portal.service.report.ReportService;
import com.moduleTesting.portal.service.task.TaskService;
import com.moduleTesting.portal.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/post")
public class PostController {

    private final CarStatusService carStatusService;

    private CarService carService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private UserService userService;

    @Autowired
    public PostController(CarStatusService carStatusService, CarService carService) {
        this.carStatusService = carStatusService;
        this.carService = carService;
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
    public UserDto getTasks() {

        final List<CarStatusEntity> all = carStatusService.findAll();
        System.out.println(all);

        final List<CarEntity> allCars = carService.findAll();
        System.out.println(allCars);

        final List<ReportEntity> allReports = reportService.findAll();
        System.out.println(allReports);

        final List<UserEntity> allUsers = userService.findAll();
        System.out.println(allUsers);

        final List<TaskEntity> allTask = taskService.findAll();
        System.out.println(allTask);




        /*Page<PostDto> lll = null;
        try{
            lll = service.getProductPage(filter);
        } catch (RuntimeException e) {
            System.out.println(e);
        }

        return lll;*/

        return new UserDto("aaa", "bbb");
    }

}
