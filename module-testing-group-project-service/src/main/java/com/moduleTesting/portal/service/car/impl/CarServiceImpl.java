package com.moduleTesting.portal.service.car.impl;

import com.moduleTesting.portal.dto.BrandDto;
import com.moduleTesting.portal.dto.CarStatus;
import com.moduleTesting.portal.entity.CarEntity;
import com.moduleTesting.portal.repository.CarRepository;
import com.moduleTesting.portal.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<CarEntity> findAll() {
        return carRepository.findAll();
    }

    @Override
    public void editCar(Integer carId, BrandDto brandId, Date year, String number, Date dateOfReceipt, CarStatus carStatus) {
    }

    @Override
    public void removeCar(Integer carId) {

    }

}
