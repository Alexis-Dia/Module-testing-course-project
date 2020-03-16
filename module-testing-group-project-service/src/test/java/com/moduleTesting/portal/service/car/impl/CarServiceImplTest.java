package com.moduleTesting.portal.service.car.impl;

import com.moduleTesting.portal.dto.BrandDto;
import com.moduleTesting.portal.dto.CarDto;
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
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class CarServiceImplTest {

    private static final int CAR_ID = 1;
    private static final String CAR_STATUS_FREE = "FREE";
    private static final String CAR_NOT_FOUND = "CAR_NOT_FOUND";
    private static final int EXISTED_BRAND_ID = 2;
    private static final int NOT_EXISTED_BRAND_ID = 100;
    private static final int WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME = 1;
    private static final String CAR_NUMBER_JX_1234 = "JX-1234";
    private static final String NOT_EXISTED_CAR_STATUS = "NOT_EXISTED_CAR_STATUS";
    private static final int EXPECTED_SIZE_OF_EMPTY_LIST = 0;
    private static final BrandDto EXISTED_BRAND = new BrandDto(EXISTED_BRAND_ID, "VW", 1000.0f, "Espace");
    private static final BrandDto NOT_EXISTED_BRAND = new BrandDto(NOT_EXISTED_BRAND_ID, "Renault", 1000.0f, "Espace");
    private static final int EXISTED_CAR_ID = 4;
    private static final int NOT_EXISTED_CAR_ID = 100;
    private static final CarDto NEW_CAR_DTO_WITH_EXISTED_BRAND = new CarDto(EXISTED_CAR_ID, EXISTED_BRAND, new Date(), CAR_NUMBER_JX_1234, new Date(), CarStatus.FREE);
    private static final CarDto NEW_CAR_DTO_WITH_NOT_EXISTED_BRAND = new CarDto(EXISTED_CAR_ID, NOT_EXISTED_BRAND, new Date(), CAR_NUMBER_JX_1234, new Date(), CarStatus.FREE);
    private static final CarDto NEW_CAR_DTO_WITH_EXISTED_CAR_ID = new CarDto(NOT_EXISTED_CAR_ID, EXISTED_BRAND, new Date(), CAR_NUMBER_JX_1234, new Date(), CarStatus.FREE);
    private static final int WANTED_NUMBER_OF_INVOCATIONS_ONE = 1;
    private static final int WANTED_NUMBER_OF_INVOCATIONS_ZERO = 0;
    private static final int EXPECTED_SIZE_OF_LIST = 1;
    private static final int TO_BE_RETURNED_OK_RESULT = 1;
    private static final String BRAND_NOT_FOUND = "BRAND_NOT_FOUND";
    public static final int NUMBER_OF_ROW = 1;

    private CarService carService;

    /**
     * For mockito testing service layer there are at lest two ways:
     * 1. Using @TestConfiguration
     * 2. Using setters in service layers and manually create CarServiceImpl and using setters set  carRepository, brandRepository and so on.
     */
/*    @Autowired
    private CarService carService;

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public CarService carService() {
            return new CarServiceImpl();
        }
    }*/

    @MockBean
    private CarRepository carRepository;

    @MockBean
    private BrandRepository brandRepository;

    @MockBean
    CarStatusRepository carStatusRepository;

    @Before
    public void setUp() {
        carService = new CarServiceImpl();
        carService.setCarRepository(carRepository);
        carService.setBrandRepository(brandRepository);
        carService.setCarStatusRepository(carStatusRepository);

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
        doReturn(Collections.emptyList()).when(carRepository).findByCarStatusEntity_Name(NOT_EXISTED_CAR_STATUS);
        doReturn(Optional.ofNullable(
            new BrandEntity(EXISTED_BRAND.getBrand(), EXISTED_BRAND.getCarryingCapacity(), EXISTED_BRAND.getModel()))
        ).when(brandRepository).findById(EXISTED_BRAND_ID);
        doReturn(NUMBER_OF_ROW).when(carRepository).save(new CarEntity());
        given(carRepository.findById(NOT_EXISTED_CAR_ID)).willThrow(new CarNotFoundException(CAR_NOT_FOUND));
        doReturn(TO_BE_RETURNED_OK_RESULT).when(carRepository).removeCarById(EXISTED_CAR_ID);
        given(carRepository.removeCarById(NOT_EXISTED_CAR_ID)).willThrow(new CarNotFoundException(CAR_NOT_FOUND));
        given(brandRepository.findById(NOT_EXISTED_BRAND_ID)).willThrow(new BrandNotFoundException(BRAND_NOT_FOUND));
        doReturn(new CarStatusEntity(CAR_STATUS_FREE)).when(carStatusRepository).findByName(CarStatus.FREE.getName());
        doReturn(Optional.ofNullable(new CarEntity())).when(carRepository).findById(EXISTED_CAR_ID);
    }

    @Test
    public void testFindAll_IsCalled() {
        carService.findAll();

        verify(carRepository).findAll();
        verify(carRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).findAll();
        verifyNoMoreInteractions(carRepository);
    }

    @Test
    public void testFindById_IdIsExisted() {
        carService.findById(CAR_ID);

        verify(carRepository).findById(CAR_ID);
        verifyNoMoreInteractions(carRepository);
    }

    @Test(expected=CarNotFoundException.class)
    public void testFindById_IdIsNotExisted() {
        carService.findById(NOT_EXISTED_CAR_ID);

        verify(carRepository).findById(CAR_ID);
        verifyNoMoreInteractions(carRepository);
    }

    @Test
    public void testGetAllFreeCars_ByExistedStatus() {
        carService.getCarsByStatusName(CarStatus.FREE.getName());

        verify(carRepository).findByCarStatusEntity_Name(CAR_STATUS_FREE);
        verifyNoMoreInteractions(carRepository);
    }

    @Test
    public void testGetAllFreeCars_ByNotExistedStatus() {
        List<CarDto> carsByStatusName = carService.getCarsByStatusName(NOT_EXISTED_CAR_STATUS);

        assertEquals(EXPECTED_SIZE_OF_EMPTY_LIST, carsByStatusName.size());
        verify(carRepository).findByCarStatusEntity_Name(NOT_EXISTED_CAR_STATUS);
        verifyNoMoreInteractions(carRepository);
    }

    //FIXME Add case when template already exists
    @Test
    public void testAddNewCar_ByExistedBrand_Ok() {
        carService.addNewCar(NEW_CAR_DTO_WITH_EXISTED_BRAND);

        verify(brandRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE)).findById(Integer.valueOf(2));
        verify(carStatusRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE)).findByName(CarStatus.FREE.getName());
        verify(carRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE)).save(ArgumentMatchers.any());
        verify(carRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE)).findAll();
        verifyNoMoreInteractions(carStatusRepository);
        verifyNoMoreInteractions(carRepository);
        verifyNoMoreInteractions(brandRepository);
    }

    @Test(expected=BrandNotFoundException.class)
    public void testAddNewCar_ByNotExistedBrand_Ok() {
        carService.addNewCar(NEW_CAR_DTO_WITH_NOT_EXISTED_BRAND);

        verify(brandRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ZERO)).findById(anyInt());
        verify(carStatusRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ZERO)).findByName(CarStatus.FREE.getName());
        verify(carRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ZERO)).save(ArgumentMatchers.any());
        verify(carRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ZERO)).findAll();
        verifyNoMoreInteractions(carStatusRepository);
        verifyNoMoreInteractions(carRepository);
        verifyNoMoreInteractions(brandRepository);
    }

    @Test
    public void testEditCar_Ok() {
        List<CarDto> cars = carService.editCar(NEW_CAR_DTO_WITH_EXISTED_BRAND);

        assertEquals(EXPECTED_SIZE_OF_LIST, cars.size());
        verify(carRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE)).findById(EXISTED_CAR_ID);
        verify(brandRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE)).findById(EXISTED_BRAND_ID);
        verify(carRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE)).updateCar(
            ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any(),
            ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
        verify(carRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE)).findAll();
        verifyNoMoreInteractions(carStatusRepository);
        verifyNoMoreInteractions(carRepository);
        verifyNoMoreInteractions(brandRepository);
    }

    @Test(expected=BrandNotFoundException.class)
    public void testEditCar_ByNotExistedBrand_Ok() {
        List<CarDto> cars = carService.editCar(NEW_CAR_DTO_WITH_NOT_EXISTED_BRAND);

        assertEquals(EXPECTED_SIZE_OF_EMPTY_LIST, cars.size());
        verify(carRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ZERO)).findById(EXISTED_CAR_ID);
        verify(brandRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ZERO)).findById(EXISTED_BRAND_ID);
        verify(carRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ZERO)).updateCar(
            ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any(),
            ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
        verify(carRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ZERO)).findAll();
        verifyNoMoreInteractions(carStatusRepository);
        verifyNoMoreInteractions(carRepository);
        verifyNoMoreInteractions(brandRepository);
    }

    @Test(expected=CarNotFoundException.class)
    public void testEditCar_ByNotExistedCar_Ok() {
        List<CarDto> cars = carService.editCar(NEW_CAR_DTO_WITH_EXISTED_CAR_ID);

        assertEquals(EXPECTED_SIZE_OF_EMPTY_LIST, cars.size());
        verify(carRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE)).findById(EXISTED_CAR_ID);
        verify(brandRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ZERO)).findById(EXISTED_BRAND_ID);
        verify(carRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ZERO)).updateCar(
            ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any(),
            ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
        verify(carRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ZERO)).findAll();
        verifyNoMoreInteractions(carStatusRepository);
        verifyNoMoreInteractions(carRepository);
        verifyNoMoreInteractions(brandRepository);
    }

    @Test
    public void testRemoveCarById_ByExistedCarId() {
        carService.removeCarById(EXISTED_CAR_ID);

        verify(carRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE)).findById(EXISTED_CAR_ID);
        verify(carRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE)).removeCarById(EXISTED_CAR_ID);
        verify(carRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE)).findAll();
        verifyNoMoreInteractions(carStatusRepository);
        verifyNoMoreInteractions(carRepository);
        verifyNoMoreInteractions(brandRepository);
    }

    @Test(expected=CarNotFoundException.class)
    public void testRemoveCarById_ByNotExistedCarId() {
        carService.removeCarById(NOT_EXISTED_CAR_ID);

        verify(carRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE)).findById(NOT_EXISTED_CAR_ID);
        verify(carRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ZERO)).removeCarById(NOT_EXISTED_CAR_ID);
        verify(carRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ZERO)).findAll();
        verifyNoMoreInteractions(carStatusRepository);
        verifyNoMoreInteractions(carRepository);
        verifyNoMoreInteractions(brandRepository);
    }
}
