package com.moduleTesting.portal.service.car.impl;

import com.moduleTesting.portal.dto.CarStatus;
import com.moduleTesting.portal.entity.BrandEntity;
import com.moduleTesting.portal.entity.CarEntity;
import com.moduleTesting.portal.entity.CarStatusEntity;
import com.moduleTesting.portal.repository.BrandRepository;
import com.moduleTesting.portal.repository.CarRepository;
import com.moduleTesting.portal.repository.CarStatusRepository;
import com.moduleTesting.portal.service.car.CarService;
import exceptions.BrandNotFoundException;
import exceptions.CarNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class CarServiceImplTest {

    private static final int CAR_ID = 1;
    private static final int NOT_EXISTED_CAR_ID = 100;
    private static final String CAR_STATUS_FREE = "FREE";
    private static final String CAR_NOT_FOUND = "CAR_NOT_FOUND";
    private static final int EXISTED_BRAND_ID = 2;
    private static final int NOT_EXISTED_BRAND_ID = 100;
    private static final String BRAND_NOT_FOUND = "BRAND_NOT_FOUND";
    public static final int WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME = 1;
    public static final String CAR_NUMBER_JX_1234 = "JX-1234";

    //@InjectMocks
    //@Mock
    //CarServiceImpl carService = new CarServiceImpl();;

    @Autowired
    private CarService carService;

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public CarService carService() {
            return new CarServiceImpl();
        }
    }

    @MockBean
    private CarRepository carRepository;

    @MockBean
    BrandRepository brandRepository;

    @MockBean
    CarStatusRepository carStatusRepository;

    @Before
    public void setUp() {
        //carServiceImpl = mock(CarServiceImpl.class);
        //carRepository = mock(CarRepository.class);

        doReturn(Collections.singletonList(
            new CarEntity(
                new BrandEntity(),
                new Date(),
                CAR_NUMBER_JX_1234,
                new Date(),
                new CarStatusEntity(CarStatus.FREE.getName()))
            )
        ).when(carRepository).findAll();
        doReturn(Optional.ofNullable(new CarEntity())).when(carRepository).findById(CAR_ID);
        doReturn(Collections.singletonList(new CarEntity())).when(carRepository).findByCarStatusEntity_Name(CAR_STATUS_FREE);
        //doNothing().when(carRepository).save(ArgumentMatchers.any(CarEntity.class));
        //doNothing().when(carRepository).removeCarById(CAR_ID);
        given(carRepository.findById(NOT_EXISTED_CAR_ID)).willThrow(new CarNotFoundException(CAR_NOT_FOUND));
        given(carRepository.removeCarById(NOT_EXISTED_CAR_ID)).willThrow(new CarNotFoundException(CAR_NOT_FOUND));

        brandRepository = mock(BrandRepository.class);
        //doNothing().when(brandRepository).findById(EXISTED_BRAND_ID);
        given(brandRepository.findById(NOT_EXISTED_BRAND_ID)).willThrow(new BrandNotFoundException(BRAND_NOT_FOUND));

        //carStatusRepository = mock(CarStatusRepository.class);
        doReturn(new CarStatusEntity()).when(carStatusRepository).findByName(CarStatus.FREE.getName());
    }

    @Test
    public void testFindAllIsCalled() {
        carService.findAll();
        verify(carRepository).findAll();
        verify(carRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).findAll();
        verifyNoMoreInteractions(carRepository);
    }

    @Test
    public void testFindById_IdIsExisted() {
        carService.findById(CAR_ID);
    }

    @Test
    public void testFindById_IdIsNotExisted() {
        //carService.findById(NOT_EXISTED_CAR_ID);
    }


    @Test
    public void testGetAllFreeCars() {
    }

    @Test
    public void testAddNewCar() {
    }

    @Test
    public void testEditCar() {
    }

    @Test
    public void testRemoveCarById() {
    }
}
