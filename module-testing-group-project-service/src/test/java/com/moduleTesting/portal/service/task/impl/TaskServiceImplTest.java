package com.moduleTesting.portal.service.task.impl;

import com.moduleTesting.portal.dto.*;
import com.moduleTesting.portal.entity.CarEntity;
import com.moduleTesting.portal.entity.CarStatusEntity;
import com.moduleTesting.portal.entity.TaskEntity;
import com.moduleTesting.portal.entity.TaskStatusEntity;
import com.moduleTesting.portal.repository.*;
import com.moduleTesting.portal.service.task.TaskService;
import com.moduleTesting.portal.service.user.UserService;
import exceptions.CarNotFoundException;
import exceptions.NotEnoughPoundsException;
import exceptions.TaskNotFoundException;
import exceptions.UserNotFoundException;
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
import java.util.List;
import java.util.Optional;

import static com.moduleTesting.portal.consts.Common.*;
import static com.moduleTesting.portal.service.TestData.*;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class TaskServiceImplTest {

    private static final int NOT_EXISTED_CAR_ID = 100;
    private static final int EXISTED_TASK_ID = 1;
    private static final int NOT_EXISTED_TASK_ID = 100;
    private static final int NOT_EXISTED_TASK_STATUS_ID = 100;
    private static final int EXISTED_TASK_STATUS_ID = 1;
    private static final int ROW_HAS_NOT_CHANGED = 0;
    private static final int EXISTED_CAR_ID = 4;
    private static final int EXPECTED_SIZE_OF_EMPTY_LIST = 0;
    private static final int PROGRESS_STATUS_ID = 4;
    private static final int FINISHED_STATUS_ID = 5;

    @Autowired
    private TaskService taskService;

    @TestConfiguration
    static class TaskServiceImplTestContextConfiguration {

        @Bean
        public TaskService carService() {
            return new TaskServiceImpl();
        }
    }

    @Before
    public void setUp() {
        doReturn(Collections.singletonList(TASK_ENTITY)).when(taskRepository).findAll();
        doReturn(Optional.ofNullable(USER_ENTITY)).when(userRepository).findByLogin(EXISTED_EMAIL);
        given(userRepository.findByLogin(NOT_EXISTED_EMAIL)).willThrow(new UserNotFoundException(MSG_ERR_USER_WASN_T_FOUND));
        doReturn(Collections.singletonList(TASK_ENTITY)).when(taskRepository).findByStatusNameContaining(EXISTED_TASK_STATUS);
        doReturn(Collections.emptyList()).when(taskRepository).findByStatusNameContaining(NOT_EXISTED_TASK_STATUS);
        doReturn(NUMBER_OF_ROW_SUCCESS).when(taskRepository).updateStatusById(EXISTED_TASK_ID, EXISTED_TASK_STATUS_ID);
        doReturn(ROW_HAS_NOT_CHANGED).when(taskRepository).updateStatusById(NOT_EXISTED_TASK_ID, NOT_EXISTED_TASK_STATUS_ID);
        doReturn(ROW_HAS_NOT_CHANGED).when(taskRepository).updateStatusById(EXISTED_TASK_ID, NOT_EXISTED_TASK_STATUS_ID);
        doReturn(ROW_HAS_NOT_CHANGED).when(taskRepository).updateStatusById(NOT_EXISTED_TASK_ID, EXISTED_TASK_STATUS_ID);
        doReturn(Optional.ofNullable(TASK_ENTITY)).when(taskRepository).findById(EXISTED_TASK_ID);
        doReturn(null).when(taskRepository).findById(NOT_EXISTED_TASK_ID);
        given(taskRepository.findById(NOT_EXISTED_USER_ID)).willThrow(new TaskNotFoundException(MSG_ERR_TASK_NOT_FOUND));
        doReturn(NUMBER_OF_ROW_SUCCESS).when(userRepository).updateUserStatus(EXISTED_USER_ID, UserStatus.FREE.getId());
        doReturn(null).when(userRepository).updateUserStatus(NOT_EXISTED_USER_ID, UserStatus.FREE.getId());
        doNothing().when(userService).transferMoney(EXISTED_USER_ID, REWARD);
        doReturn(NUMBER_OF_ROW_SUCCESS).when(userRepository).updateBalance(EXISTED_USER_ID, REWARD_500);
        given(userRepository.updateBalance(NOT_EXISTED_USER_ID, REWARD_500)).willThrow(new NotEnoughPoundsException(MSG_ERR_NOT_ENOUGH_POUNDS_ON_ADMIN_ACCOUNT));
        doReturn(NUMBER_OF_ROW_NOT_SUCCESS).when(userRepository).updateBalance(NOT_EXISTED_USER_ID, REWARD_500);
        doReturn(new CarStatusEntity(CAR_STATUS_FREE)).when(carStatusRepository).findByName(CarStatus.FREE.getName());
        doReturn(new TaskStatusEntity(TaskStatus.IN_PROGRESS.getName())).when(taskStatusRepository).findById(TaskStatus.IN_PROGRESS.getId());
        doReturn(Optional.ofNullable(CAR_ENTITY)).when(carRepository).findById(EXISTED_CAR_ID);
        doReturn(new CarEntity()).when(carRepository).save(new CarEntity());
        doReturn(TASK_ENTITY).when(taskRepository).save(ArgumentMatchers.any(TaskEntity.class));
    }

    @MockBean
    private TaskRepository taskRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CarRepository carRepository;

    @MockBean
    private CarStatusRepository carStatusRepository;

    @MockBean
    private TaskStatusRepository taskStatusRepository;

    @MockBean
    private UserService userService;

    @Test
    public void testFindAll_Ok() {
        taskService.findAll();

        verify(taskRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).findAll();
        verifyNoMoreInteractions(taskRepository);
    }

    @Test
    public void testFindAllMineTasks_Ok() {
        taskService.findAllMineTasks(EXISTED_EMAIL);

        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).findByLogin(EXISTED_EMAIL);
        verify(taskRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).findByDriver_Id(EXISTED_USER_ID);
        verifyNoMoreInteractions(userRepository);
        verifyNoMoreInteractions(taskRepository);
    }

    @Test(expected=UserNotFoundException.class)
    public void testFindAllMineTasks_ErrorUserNotFound() {
        taskService.findAllMineTasks(NOT_EXISTED_EMAIL);

        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).findByLogin(NOT_EXISTED_EMAIL);
        verify(taskRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ZERO)).findByDriver_Id(null);
        verifyNoMoreInteractions(userRepository);
        verifyNoMoreInteractions(taskRepository);
    }

    @Test
    public void testFindAllByStatusTasks_Ok() {
        taskService.findAllByStatusTasks(FREE);

        verify(taskRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).findByStatusNameContaining(EXISTED_TASK_STATUS);
        verifyNoMoreInteractions(taskRepository);
    }

    @Test
    public void testFindAllByStatusTasks_EmptyList() {
        List<TaskDto> allByStatusTasks = taskService.findAllByStatusTasks(NOT_EXISTED_TASK_STATUS);

        assertEquals(EXPECTED_SIZE_OF_EMPTY_LIST, allByStatusTasks.size());
        verify(taskRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).findByStatusNameContaining(NOT_EXISTED_TASK_STATUS);
        verifyNoMoreInteractions(taskRepository);
    }

    @Test
    public void testChangeTaskStatus_Ok() {
        taskService.changeTaskStatus(EXISTED_TASK_ID, EXISTED_TASK_STATUS_ID);

        verify(taskRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).updateStatusById(EXISTED_TASK_ID, EXISTED_TASK_STATUS_ID);
        verifyNoMoreInteractions(taskRepository);
    }

    @Test
    public void testChangeTaskStatusToFinish_Finished() {
        taskService.changeTaskStatusToFinish(EXISTED_TASK_ID, FINISHED_STATUS_ID, EXISTED_EMAIL);

        verify(taskRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).findById(EXISTED_TASK_ID);
        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).updateUserStatus(EXISTED_USER_ID, UserStatus.FREE.getId());
        verify(taskRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).updateStatusById(EXISTED_TASK_ID, FINISHED_STATUS_ID);
        verify(userService, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).transferMoney(EXISTED_USER_ID, REWARD_500);
        verifyNoMoreInteractions(taskRepository);
        verifyNoMoreInteractions(userRepository);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void testChangeTaskStatusToFinish_NotFinished() {
        taskService.changeTaskStatusToFinish(EXISTED_TASK_ID, PROGRESS_STATUS_ID, EXISTED_EMAIL);

        verify(taskRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).findById(EXISTED_TASK_ID);
        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).updateUserStatus(EXISTED_USER_ID, UserStatus.FREE.getId());
        verify(taskRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).updateStatusById(EXISTED_TASK_ID, PROGRESS_STATUS_ID);
        verify(userService, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ZERO)).transferMoney(EXISTED_USER_ID, REWARD_500);
        verifyNoMoreInteractions(taskRepository);
        verifyNoMoreInteractions(userRepository);
        verifyNoMoreInteractions(userService);
    }

    @Test(expected= TaskNotFoundException.class)
    public void testChangeTaskStatusToFinish_Fail() {
        taskService.changeTaskStatusToFinish(NOT_EXISTED_TASK_ID, PROGRESS_STATUS_ID, EXISTED_EMAIL);

        verify(taskRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ZERO)).findById(EXISTED_TASK_ID);
        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ZERO)).updateUserStatus(EXISTED_USER_ID, UserStatus.FREE.getId());
        verify(taskRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ZERO)).updateStatusById(EXISTED_TASK_ID, PROGRESS_STATUS_ID);
        verify(userService, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ZERO)).transferMoney(EXISTED_USER_ID, REWARD_500);
        verifyNoMoreInteractions(taskRepository);
        verifyNoMoreInteractions(userRepository);
        verifyNoMoreInteractions(userService);
    }


    @Test
    public void testTakeTask_Ok() {
        taskService.takeTask(EXISTED_TASK_ID, EXISTED_CAR_ID, EXISTED_EMAIL);

        verify(carStatusRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).findByName(UserStatus.BUSY.getName());
        verify(taskStatusRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).findById(TaskStatus.IN_PROGRESS.getId());
        verify(carRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).findById(EXISTED_CAR_ID);
        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).findByLogin(EXISTED_EMAIL);
        verify(taskRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).findById(EXISTED_TASK_ID);
        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).updateUserStatus(EXISTED_USER_ID, UserStatus.BUSY.getId());
        verify(carRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).save(ArgumentMatchers.any(CarEntity.class));
        verify(taskRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).save(ArgumentMatchers.any(TaskEntity.class));
        verifyNoMoreInteractions(taskRepository);
        verifyNoMoreInteractions(userRepository);
        verifyNoMoreInteractions(userService);
        verifyNoMoreInteractions(carStatusRepository);
        verifyNoMoreInteractions(taskStatusRepository);
    }

    @Test(expected= CarNotFoundException.class)
    public void testTakeTask_Exception_CarNotFound() {
        taskService.takeTask(EXISTED_TASK_ID, NOT_EXISTED_CAR_ID, EXISTED_EMAIL);

        verify(carStatusRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).findByName(UserStatus.BUSY.getName());
        verify(taskStatusRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).findById(TaskStatus.IN_PROGRESS.getId());
        verify(carRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ZERO)).findById(EXISTED_CAR_ID);
        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ZERO)).findByLogin(EXISTED_EMAIL);
        verify(taskRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ZERO)).findById(EXISTED_TASK_ID);
        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).updateUserStatus(EXISTED_USER_ID, UserStatus.BUSY.getId());
        verify(carRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).save(ArgumentMatchers.any(CarEntity.class));
        verify(taskRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).save(ArgumentMatchers.any(TaskEntity.class));
        verifyNoMoreInteractions(taskRepository);
        verifyNoMoreInteractions(userRepository);
        verifyNoMoreInteractions(userService);
        verifyNoMoreInteractions(carStatusRepository);
        verifyNoMoreInteractions(taskStatusRepository);
    }

    @Test(expected= UserNotFoundException.class)
    public void testTakeTask_Exception_UserNotFound() {
        taskService.takeTask(EXISTED_TASK_ID, EXISTED_CAR_ID, NOT_EXISTED_EMAIL);

        verify(carStatusRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).findByName(UserStatus.BUSY.getName());
        verify(taskStatusRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).findById(TaskStatus.IN_PROGRESS.getId());
        verify(carRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).findById(EXISTED_CAR_ID);
        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ZERO)).findByLogin(EXISTED_EMAIL);
        verify(taskRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ZERO)).findById(EXISTED_TASK_ID);
        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).updateUserStatus(EXISTED_USER_ID, UserStatus.BUSY.getId());
        verify(carRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).save(ArgumentMatchers.any(CarEntity.class));
        verify(taskRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).save(ArgumentMatchers.any(TaskEntity.class));
        verifyNoMoreInteractions(taskRepository);
        verifyNoMoreInteractions(userRepository);
        verifyNoMoreInteractions(userService);
        verifyNoMoreInteractions(carStatusRepository);
        verifyNoMoreInteractions(taskStatusRepository);
    }

    @Test
    public void testCreateNewTask_Ok() {
        taskService.createNewTask(TASK_DTO);

        verify(taskRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).save(ArgumentMatchers.any(TaskEntity.class));
        verify(taskRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).findAll();

        verifyNoMoreInteractions(taskRepository);
    }

    @Test(expected= TaskNotFoundException.class)
    public void testTakeTask_Exception_TaskNotFound() {
        taskService.takeTask(NOT_EXISTED_TASK_ID, EXISTED_CAR_ID, EXISTED_EMAIL);

        verify(carStatusRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).findByName(UserStatus.BUSY.getName());
        verify(taskStatusRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).findById(TaskStatus.IN_PROGRESS.getId());
        verify(carRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).findById(EXISTED_CAR_ID);
        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).findByLogin(EXISTED_EMAIL);
        verify(taskRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ZERO)).findById(EXISTED_TASK_ID);
        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).updateUserStatus(EXISTED_USER_ID, UserStatus.BUSY.getId());
        verify(carRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).save(ArgumentMatchers.any(CarEntity.class));
        verify(taskRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).save(ArgumentMatchers.any(TaskEntity.class));
        verifyNoMoreInteractions(taskRepository);
        verifyNoMoreInteractions(userRepository);
        verifyNoMoreInteractions(userService);
        verifyNoMoreInteractions(carStatusRepository);
        verifyNoMoreInteractions(taskStatusRepository);
    }

}
