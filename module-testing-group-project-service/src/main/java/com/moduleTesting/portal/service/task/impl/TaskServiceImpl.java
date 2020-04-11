package com.moduleTesting.portal.service.task.impl;

import com.moduleTesting.portal.dto.TaskDto;
import com.moduleTesting.portal.dto.TaskStatus;
import com.moduleTesting.portal.dto.UserStatus;
import com.moduleTesting.portal.entity.*;
import com.moduleTesting.portal.repository.*;
import com.moduleTesting.portal.service.mapper.DtoMapper;
import com.moduleTesting.portal.service.task.TaskService;
import com.moduleTesting.portal.service.user.UserService;
import exceptions.CarNotFoundException;
import exceptions.TaskNotFoundException;
import exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.moduleTesting.portal.consts.Common.*;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    CarStatusRepository carStatusRepository;

    @Autowired
    TaskStatusRepository taskStatusRepository;

    @Autowired
    UserService userService;

    @Override
    public List<TaskDto> findAll() {
        return taskRepository.findAll().stream().map(DtoMapper::toTaskDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<TaskDto> findAllMineTasks(String authenticationName) {
        UserEntity userByLogin = userRepository.findByLogin(authenticationName).orElseThrow(() -> new UserNotFoundException(MSG_ERR_USER_WASN_T_FOUND));

        return taskRepository.findByDriver_Id(userByLogin.getId()).stream().map(DtoMapper::toTaskDto).collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> findAllByStatusTasks(String statusName) {
        return taskRepository.findByStatusNameContaining(statusName).stream().map(DtoMapper::toTaskDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Integer changeTaskStatus(Integer taskId, Integer statusId) {
        Integer rowNumber = taskRepository.updateStatusById(taskId, statusId);
        return rowNumber;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Integer changeTaskStatusToFinish(Integer taskId, Integer statusId, String authenticationName) {

        TaskEntity task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(MSG_ERR_TASK_NOT_FOUND));

        if (statusId == TaskStatus.FINISHED.getId()) {
            Integer driverId = task.getDriver().getId();
            userService.transferMoney(driverId, task.getReward());
        }

        Integer rowNumber = taskRepository.updateStatusById(taskId, statusId);

        return rowNumber;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Integer takeTask(Integer taskId, Integer carId, String authenticationName) {

        CarStatusEntity carStatusEntityBusy = carStatusRepository.findByName(UserStatus.BUSY.getName());
        TaskStatusEntity taskStatusInProgress = taskStatusRepository.findById(TaskStatus.IN_PROGRESS.getId());
        Optional<CarEntity> car = Optional.of(carRepository.findById(carId).orElseThrow(() -> new CarNotFoundException(MSG_ERR_CAR_NOT_FOUND)));
        Optional<UserEntity> user = Optional.of(userRepository.findByLogin(authenticationName).orElseThrow(() -> new UserNotFoundException(MSG_ERR_USER_WASN_T_FOUND)));
        TaskEntity task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(MSG_ERR_TASK_NOT_FOUND));

        userRepository.updateUserStatus(user.get().getId(), UserStatus.BUSY.getId());

        car.get().setCarStatusEntity(carStatusEntityBusy);
        carRepository.save(car.get());

        task.setCar(car.get());
        task.setDriver(user.get());
        task.setStatus(taskStatusInProgress);

        taskRepository.save(task);

        return null;
    }

    /**
     * @Transactional If we get err in second action findAll the taskRepository.save will rollback
     * @param taskDto
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<TaskDto> createNewTask(TaskDto taskDto) {
        taskRepository.save(new TaskEntity(taskDto.getName(), taskDto.getSummaryDistance(), taskDto.getWeight(),
            new TaskStatusEntity(TaskStatus.FREE.getId(), TaskStatus.FREE.getName()), taskDto.getReward()));
        final List<TaskDto> allTasks = findAll();
        return allTasks;
    }

}
