package com.moduleTesting.portal.service.car;

import com.moduleTesting.portal.dto.BrandDto;
import com.moduleTesting.portal.dto.CarDto;
import com.moduleTesting.portal.dto.CarStatus;

import java.util.Date;
import java.util.List;

public interface CarService {

    List<CarDto> findAll();

    List<CarDto> editCar(Integer carId, BrandDto brandId, Date year, String number, Date dateOfReceipt, CarStatus carStatus);

    List<CarDto> removeCar(Integer carId);

}
