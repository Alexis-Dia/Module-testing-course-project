package com.moduleTesting.portal.service.car.impl;

import com.moduleTesting.portal.dto.CarStatus;
import com.moduleTesting.portal.entity.CarEntity;
import com.moduleTesting.portal.entity.CarStatusEntity;
import com.moduleTesting.portal.repository.BrandRepository;
import com.moduleTesting.portal.repository.CarRepository;
import com.moduleTesting.portal.repository.CarStatusRepository;
import exceptions.BrandNotFoundException;
import exceptions.CarNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceImplTest {

    private static final int CAR_ID = 1;
    private static final int NOT_EXISTED_CAR_ID = 100;
    private static final String CAR_STATUS_FREE = "FREE";
    private static final String CAR_NOT_FOUND = "CAR_NOT_FOUND";
    private static final int EXISTED_BRAND_ID = 2;
    private static final int NOT_EXISTED_BRAND_ID = 100;
    private static final String BRAND_NOT_FOUND = "BRAND_NOT_FOUND";

    //@InjectMocks
    //@Mock
    //CarServiceImpl carService = new CarServiceImpl();;

    CarServiceImpl carServiceImpl;
    CarRepository carRepository;
    BrandRepository brandRepository;
    CarStatusRepository carStatusRepository;

    @Before
    public void setUp() {
        carServiceImpl = mock(CarServiceImpl.class);

        carRepository = mock(CarRepository.class);
        doReturn(Collections.singletonList(new CarEntity())).when(carRepository).findAll();
        doReturn(Optional.ofNullable(new CarEntity())).when(carRepository).findById(CAR_ID);
        doReturn(Collections.singletonList(new CarEntity())).when(carRepository).findByCarStatusEntity_Name(CAR_STATUS_FREE);
        //doNothing().when(carRepository).save(ArgumentMatchers.any(CarEntity.class));
        //doNothing().when(carRepository).removeCarById(CAR_ID);
        given(carRepository.findById(NOT_EXISTED_CAR_ID)).willThrow(new CarNotFoundException(CAR_NOT_FOUND));
        given(carRepository.removeCarById(NOT_EXISTED_CAR_ID)).willThrow(new CarNotFoundException(CAR_NOT_FOUND));

        brandRepository = mock(BrandRepository.class);
        //doNothing().when(brandRepository).findById(EXISTED_BRAND_ID);
        given(brandRepository.findById(NOT_EXISTED_BRAND_ID)).willThrow(new BrandNotFoundException(BRAND_NOT_FOUND));

        carStatusRepository = mock(CarStatusRepository.class);
        doReturn(new CarStatusEntity()).when(carStatusRepository).findByName(CarStatus.FREE.getName());
    }

    @Test
    public void testFindAllIsCalled() {
        carServiceImpl.findAll();

        verify(carRepository).findAll();
        verifyNoInteractions(carRepository);
    }

    @Test
    public void findById() {
    }

    @Test
    public void getAllFreeCars() {
    }

    @Test
    public void addNewCar() {
    }

    @Test
    public void editCar() {
    }

    @Test
    public void removeCarById() {
    }
}
