package com.moduleTesting.portal.service.brand.impl;

import com.moduleTesting.portal.dto.BrandDto;
import com.moduleTesting.portal.entity.BrandEntity;
import com.moduleTesting.portal.repository.BrandRepository;
import com.moduleTesting.portal.service.brand.BrandService;
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

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class BrandServiceImplTest {

    private static final int EXISTED_BRAND_ID = 2;
    private static final int WANTED_NUMBER_OF_INVOCATIONS_ONE = 1;
    private static final BrandDto EXISTED_BRAND = new BrandDto(EXISTED_BRAND_ID, "VW", 1000.0f, "Espace");

    @Autowired
    private BrandService brandService;

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public BrandService carService() {
            return new BrandServiceImpl();
        }
    }

    @MockBean
    private BrandRepository brandRepository;

    @Before
    public void setUp() {
        doReturn(Collections.singletonList(
            new BrandEntity(EXISTED_BRAND.getBrand(), EXISTED_BRAND.getCarryingCapacity(), EXISTED_BRAND.getModel())
        )).when(brandRepository).findAll();
    }

    @Test
    public void findAll() {
        brandService.findAll();

        verify(brandRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE)).findAll();
        verifyNoMoreInteractions(brandRepository);
    }
}
