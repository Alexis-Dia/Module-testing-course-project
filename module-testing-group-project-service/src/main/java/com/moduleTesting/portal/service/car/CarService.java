package com.moduleTesting.portal.service.car;

import com.moduleTesting.portal.dto.CarDto;
import com.moduleTesting.portal.entity.CarEntity;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<CarDto> findAll();

    Optional<CarEntity> findById(Integer carId);

    List<CarDto> getAllFreeCars(String statusName);

    List<CarDto> addNewCar(CarDto carDto);

    List<CarDto> editCar(CarDto carDto);

    List<CarDto> removeCarById(Integer carId);

}
