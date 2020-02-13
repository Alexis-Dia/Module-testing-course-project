package com.moduleTesting.service.carStatus.impl;

import com.moduleTesting.portal.entity.CarStatus;
import com.moduleTesting.portal.repository.CarStatusRepository;
import com.moduleTesting.service.carStatus.CarStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarStatusServiceImpl implements CarStatusService {

    CarStatusRepository carStatusRepository;

    @Autowired
    public CarStatusServiceImpl(CarStatusRepository carStatusRepository) {
        this.carStatusRepository = carStatusRepository;
    }

    @Override
    public List<CarStatus> findAll() {
        return carStatusRepository.findAll();
    }
}
