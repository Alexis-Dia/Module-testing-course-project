package com.moduleTesting.portal.service.report.impl;

import com.moduleTesting.portal.dto.CarStatus;
import com.moduleTesting.portal.dto.ReportDto;
import com.moduleTesting.portal.entity.*;
import com.moduleTesting.portal.repository.ReportRepository;
import com.moduleTesting.portal.repository.TaskRepository;
import com.moduleTesting.portal.service.report.ReportService;
import exceptions.TaskNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class ReportServiceImplTest {

    private static final int WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME = 1;
    private static final int WANTED_NUMBER_OF_INVOCATIONS_ZERO = 0;
    private static final String CAR_NUMBER_JX_1234 = "JX-1234";
    private static final Integer EXISTED_TASK_ID = 4;
    private static final Integer NOT_EXISTED_TASK_ID = 100;
    private static final String FREE = "FREE";
    private static final UserStatusEntity USER_STATUS = new UserStatusEntity(FREE);
    private static final RoleEntity USER_ROLE = new RoleEntity("DRIVER", 1);
    private static final ReportEntity REPORT_ENTITY = new ReportEntity(new Date(), 100f, 100f, new Date());
    private static final UserEntity USER_ENTITY = new UserEntity(1, "Alex", "Alexey",
        "Alexeyevich", new Date(), "alex@tut.by", "alex", 100.0f, USER_ROLE, USER_STATUS);
    private static final CarEntity CAR_ENTITY = new CarEntity(
        new BrandEntity(),
        new Date(),
        CAR_NUMBER_JX_1234,
        new Date(),
        new CarStatusEntity(CarStatus.FREE.getName())
    );
    private static final TaskStatusEntity FREE_TASK_STATUS_ENTITY = new TaskStatusEntity(1, "FREE");
    private static final TaskEntity TASK_ENTITY = new TaskEntity(
        "Minsk-Gomel", 1000.0f, 500.0f,
        USER_ENTITY, CAR_ENTITY, FREE_TASK_STATUS_ENTITY, 500.0f);
    public static final ReportDto REPORT_DTO = new ReportDto(1, new Date(), 100f, 100f, new Date());

    @Autowired
    private ReportService reportService;

    @TestConfiguration
    static class ReportServiceImplTestContextConfiguration {

        @Bean
        public ReportService reportService() {
            return new ReportServiceImpl();
        }
    }

    @Before
    public void setUp() {
        doReturn(Collections.singletonList(REPORT_ENTITY)).when(taskRepository).findAll();
        doReturn(Optional.of(TASK_ENTITY)).when(taskRepository).findById(EXISTED_TASK_ID);
        doReturn(TASK_ENTITY).when(taskRepository).save(TASK_ENTITY);
    }

    @MockBean
    private ReportRepository reportRepository;

    @MockBean
    private TaskRepository taskRepository;

    @Test
    public void findAll_OK() {
        reportService.findAll();

        verify(reportRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).findAll();
        verifyNoMoreInteractions(reportRepository);
    }

    @Test
    public void getReportsByTaskId_Ok() {
        reportService.getReportsByTaskId(EXISTED_TASK_ID);

        verify(taskRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).findById(EXISTED_TASK_ID);
        verifyNoMoreInteractions(taskRepository);
    }

    @Test(expected= TaskNotFoundException.class)
    public void getReportsByTaskId_Exception_TaskNotFound() {
        reportService.getReportsByTaskId(NOT_EXISTED_TASK_ID);

        verify(taskRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ZERO)).findById(EXISTED_TASK_ID);
        verifyNoMoreInteractions(taskRepository);
    }

    @Test
    public void createReport_Ok() {
        reportService.createReport(EXISTED_TASK_ID, REPORT_DTO);

        verify(taskRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).findById(EXISTED_TASK_ID);
        verify(taskRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).save(ArgumentMatchers.any(TaskEntity.class));
        verifyNoMoreInteractions(taskRepository);
    }

    @Test(expected= TaskNotFoundException.class)
    public void createReport_Exception_TaskNotFound() {
        reportService.createReport(NOT_EXISTED_TASK_ID, REPORT_DTO);

        verify(taskRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ZERO)).findById(EXISTED_TASK_ID);
        verify(taskRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ZERO)).save(ArgumentMatchers.any(TaskEntity.class));
        verifyNoMoreInteractions(taskRepository);
    }
}
