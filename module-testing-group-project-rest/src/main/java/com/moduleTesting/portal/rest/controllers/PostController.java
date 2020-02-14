package com.moduleTesting.portal.rest.controllers;

import com.moduleTesting.portal.dto.User;
import com.moduleTesting.portal.entity.Car;
import com.moduleTesting.portal.entity.CarStatus;
import com.moduleTesting.portal.entity.Report;
import com.moduleTesting.portal.entity.Task;
import com.moduleTesting.portal.service.car.CarService;
import com.moduleTesting.portal.service.carStatus.CarStatusService;
import com.moduleTesting.portal.service.report.ReportService;
import com.moduleTesting.portal.service.task.TaskService;
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
    public User getTasks() {

        final List<CarStatus> all = carStatusService.findAll();
        System.out.println(all);

        final List<Car> allCars = carService.findAll();
        System.out.println(allCars);

        final List<Report> allReports = reportService.findAll();
        System.out.println(allReports);

        final List<Task> allTask = taskService.findAll();
        System.out.println(allTask);



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
