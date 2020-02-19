package com.moduleTesting.portal.service.car;

import com.moduleTesting.portal.dto.CarDto;

import java.util.List;

public interface CarService {

    List<CarDto> findAll();

    List<CarDto> addNewCar(CarDto carDto);

    List<CarDto> editCar(CarDto carDto);

    List<CarDto> removeCarById(Integer carId);

}
