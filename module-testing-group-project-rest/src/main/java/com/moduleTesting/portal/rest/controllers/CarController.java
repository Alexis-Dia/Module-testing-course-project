package com.moduleTesting.portal.rest.controllers;

import com.moduleTesting.portal.dto.CarStatus;
import com.moduleTesting.portal.service.car.CarService;
import com.moduleTesting.portal.service.carStatus.CarStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private CarStatusService carStatusService;

    @PostMapping("/all")
    public List<CarStatus> getAllCars() {

        final List<CarStatus> allCars = carStatusService.findAll();
        System.out.println(allCars);

        return allCars;
    }
}
