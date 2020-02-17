package com.moduleTesting.portal.rest.controllers;

import com.moduleTesting.portal.dto.BrandDto;
import com.moduleTesting.portal.dto.CarDto;
import com.moduleTesting.portal.dto.CarStatus;
import com.moduleTesting.portal.service.car.CarService;
import com.moduleTesting.portal.service.carStatus.CarStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@RequestMapping(value = "/car")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private CarStatusService carStatusService;

    @PostMapping("/all")
    public List<CarDto> getAllCars() {

        final List<CarDto> allCars = carService.findAll();
        System.out.println(allCars);

        return allCars;
    }

    @PostMapping("/edit")
    public List<CarDto> editCar(Integer carId, BrandDto brandId, Date year, String number, Date dateOfReceipt, CarStatus carStatus) {

        final List<CarDto> cars = carService.editCar(carId, brandId, year, number, dateOfReceipt, carStatus);
        System.out.println(cars);

        return cars;
    }

    @PostMapping("/remove")
    public List<CarDto> removeCar(Integer carId) {

        final List<CarDto> cars = carService.removeCar(carId);
        System.out.println(cars);

        return cars;
    }
}
