package com.moduleTesting.portal.rest.controllers;

import com.moduleTesting.portal.dto.CarDto;
import com.moduleTesting.portal.dto.CarStatus;
import com.moduleTesting.portal.service.car.CarService;
import com.moduleTesting.portal.service.carStatus.CarStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/car")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private CarStatusService carStatusService;

    @GetMapping("/all")
    public List<CarDto> getAllCars() {

        final List<CarDto> allCars = carService.findAll();

        return allCars;
    }

    @GetMapping("/allFree")
    public List<CarDto> getAllFreeCars() {

        final List<CarDto> allCars = carService.getCarsByStatusName(CarStatus.FREE.getName());

        return allCars;
    }

    @PostMapping("/addNew")
    public List<CarDto> addNewCar(@RequestBody CarDto carDto) {

        final List<CarDto> cars = carService.addNewCar(carDto);

        return cars;
    }


    @PutMapping("/edit")
    public List<CarDto> editCar(@RequestBody CarDto carDto) {

        final List<CarDto> cars = carService.editCar(carDto);

        return cars;
    }

    @DeleteMapping("/removeById")
    public List<CarDto> removeCar(@RequestParam("carId") Integer carId) {

        final List<CarDto> cars = carService.removeCarById(carId);

        return cars;
    }
}
