package com.moduleTesting.portal.service.car;

import com.moduleTesting.portal.dto.CarDto;
import com.moduleTesting.portal.entity.CarEntity;
import com.moduleTesting.portal.repository.BrandRepository;
import com.moduleTesting.portal.repository.CarRepository;
import com.moduleTesting.portal.repository.CarStatusRepository;

import java.util.List;
import java.util.Optional;

public interface CarService {

    void setCarRepository(CarRepository carRepository);

    void setBrandRepository(BrandRepository brandRepository);

    void setCarStatusRepository(CarStatusRepository carStatusRepository);

    List<CarDto> findAll();

    Optional<CarEntity> findById(Integer carId);

    List<CarDto> getCarsByStatusName(String statusName);

    List<CarDto> addNewCar(CarDto carDto);

    List<CarDto> editCar(CarDto carDto);

    List<CarDto> removeCarById(Integer carId);

}
