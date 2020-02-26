package com.moduleTesting.portal.service.task.impl;

import com.moduleTesting.portal.dto.TaskDto;
import com.moduleTesting.portal.dto.UserDto;
import com.moduleTesting.portal.entity.TaskEntity;
import com.moduleTesting.portal.entity.TaskStatusEntity;
import com.moduleTesting.portal.repository.TaskRepository;
import com.moduleTesting.portal.service.mapper.DtoMapper;
import com.moduleTesting.portal.service.task.TaskService;
import com.moduleTesting.portal.service.user.UserService;
import exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.moduleTesting.portal.consts.Common.MSG_ERR_USER_WASN_T_FOUND;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserService userService;

    @Override
    public List<TaskDto> findAll() {
        return taskRepository.findAll().stream().map(DtoMapper::toTaskDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<TaskDto> findAllMineTasks(String authenticationName) {
        UserDto userByLogin = userService.findByLogin(authenticationName).orElseThrow(() -> new UserNotFoundException(MSG_ERR_USER_WASN_T_FOUND));

        return taskRepository.findByDriver_Id(userByLogin.getUserID()).stream().map(DtoMapper::toTaskDto).collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> findAllActiveTasks(String statusName) {
        return taskRepository.findByStatusNameContaining(statusName).stream().map(DtoMapper::toTaskDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Integer changeTaskStatus(Integer taskId, Integer statusId) {
        Integer rowNumber = taskRepository.updateStatusById(taskId, statusId);
        return rowNumber;
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
            new TaskStatusEntity(taskDto.getTaskStatus().getId(), taskDto.getTaskStatus().getName()), taskDto.getReward()));
        final List<TaskDto> allTasks = findAll();
        return allTasks;
    }

}
