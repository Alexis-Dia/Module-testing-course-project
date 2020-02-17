package com.moduleTesting.portal.service.car.impl;

import com.moduleTesting.portal.dto.BrandDto;
import com.moduleTesting.portal.dto.CarDto;
import com.moduleTesting.portal.dto.CarStatus;
import com.moduleTesting.portal.repository.CarRepository;
import com.moduleTesting.portal.service.car.CarService;
import com.moduleTesting.portal.service.mapper.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<CarDto> findAll() {
        return carRepository.findAll().stream().map(DtoMapper::toCarDto).collect(Collectors.toList());
    }

    @Override
    public List<CarDto> editCar(Integer carId, BrandDto brandId, Date year, String number, Date dateOfReceipt, CarStatus carStatus) {
        return null;
    }

    @Override
    public List<CarDto> removeCar(Integer carId) {
        return null;
    }

}
