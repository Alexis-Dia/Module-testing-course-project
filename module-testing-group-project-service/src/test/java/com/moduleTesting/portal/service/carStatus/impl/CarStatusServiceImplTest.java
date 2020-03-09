package com.moduleTesting.portal.service.carStatus.impl;

import com.moduleTesting.portal.dto.CarStatus;
import com.moduleTesting.portal.entity.CarStatusEntity;
import com.moduleTesting.portal.repository.CarStatusRepository;
import com.moduleTesting.portal.service.carStatus.CarStatusService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class CarStatusServiceImplTest {

    private static final int WANTED_NUMBER_OF_INVOCATIONS_ONE = 1;

    @MockBean
    private CarStatusRepository carStatusRepository;

    @TestConfiguration
    static class BrandServiceImplTestContextConfiguration {

        @Bean
        public CarStatusService carService() {
            return new CarStatusServiceImpl();
        }
    }

    @Before
    public void setUp() {
        doReturn(Collections.singletonList(
            new CarStatusEntity(CarStatus.FREE.getName())
        )).when(carStatusRepository).findAll();
    }


    @Test
    public void findAll_Ok() {
        carStatusRepository.findAll();

        verify(carStatusRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE)).findAll();
        verifyNoMoreInteractions(carStatusRepository);
    }
}
