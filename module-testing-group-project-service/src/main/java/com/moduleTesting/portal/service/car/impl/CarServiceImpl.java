package com.moduleTesting.portal.service.car.impl;

import com.moduleTesting.portal.dto.CarDto;
import com.moduleTesting.portal.dto.CarStatus;
import com.moduleTesting.portal.entity.BrandEntity;
import com.moduleTesting.portal.entity.CarEntity;
import com.moduleTesting.portal.entity.CarStatusEntity;
import com.moduleTesting.portal.repository.BrandRepository;
import com.moduleTesting.portal.repository.CarRepository;
import com.moduleTesting.portal.repository.CarStatusRepository;
import com.moduleTesting.portal.service.car.CarService;
import com.moduleTesting.portal.service.mapper.DtoMapper;
import exceptions.BrandNotFoundException;
import exceptions.CarNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private static final String CAR_NOT_FOUND = "Car not found";
    private static final String BRAND_NOT_FOUND = "Brand not found";

    @Autowired
    CarRepository carRepository;

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    CarStatusRepository carStatusRepository;

    /**
     * For mockito testing service layer there are at lest two ways:
     * 1. Using @TestConfiguration
     * 2. Using setters in service layers and manually create CarServiceImpl and using setters set  carRepository, brandRepository and so on.
     */
    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void setBrandRepository(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public void setCarStatusRepository(CarStatusRepository carStatusRepository) {
        this.carStatusRepository = carStatusRepository;
    }

    @Override
    public List<CarDto> findAll() {
        List<CarEntity> carAll = carRepository.findAll();
        return carAll.stream().map(DtoMapper::toCarDto).collect(Collectors.toList());
    }

    @Override
    public Optional<CarEntity> findById(Integer carId) {
        return carRepository.findById(carId);
    }

    @Override
    public List<CarDto> getCarsByStatusName(String statusName) {
        List<CarEntity> byCarStatusEntity_name = carRepository.findByCarStatusEntity_Name(statusName);
        return byCarStatusEntity_name.stream().map(DtoMapper::toCarDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<CarDto> addNewCar(CarDto carDto) {

        BrandEntity brandEntity = brandRepository.findById(carDto.getBrand().getId()).orElseThrow(() -> new BrandNotFoundException("Brand not found"));
        CarStatusEntity carStatusEntity = carStatusRepository.findByName(CarStatus.FREE.getName());

        CarEntity carEntity = new CarEntity(brandEntity, carDto.getYear(), carDto.getNumber(), carDto.getDateOfReceipt(), carStatusEntity);
        carRepository.save(carEntity);

        final List<CarDto> allCars = findAll();
        return allCars;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<CarDto> editCar(CarDto carDto) {

        carRepository.findById(carDto.getId()).orElseThrow(() -> new CarNotFoundException(CAR_NOT_FOUND));
        brandRepository.findById(carDto.getBrand().getId()).orElseThrow(() -> new BrandNotFoundException(BRAND_NOT_FOUND));

        carRepository.updateCar(carDto.getId(), carDto.getBrand().getId(), carDto.getYear(),
                carDto.getNumber(), carDto.getDateOfReceipt(), carDto.getCarStatus().getId());

        final List<CarDto> allCars = findAll();
        return allCars;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<CarDto> removeCarById(Integer carId) {

        carRepository.findById(carId).orElseThrow(() -> new CarNotFoundException(CAR_NOT_FOUND));

        carRepository.removeCarById(carId);

        final List<CarDto> allCars = findAll();
        return allCars;
    }

}
