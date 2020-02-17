package com.moduleTesting.portal.service.car;

import com.moduleTesting.portal.dto.BrandDto;
import com.moduleTesting.portal.dto.CarStatus;
import com.moduleTesting.portal.entity.CarEntity;

import java.util.Date;
import java.util.List;

public interface CarService {

    List<CarEntity> findAll();

    void editCar(Integer carId, BrandDto brandId, Date year, String number, Date dateOfReceipt, CarStatus carStatus);

    void removeCar(Integer carId);

}
