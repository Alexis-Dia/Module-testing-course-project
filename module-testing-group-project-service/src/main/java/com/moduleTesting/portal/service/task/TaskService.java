package com.moduleTesting.portal.service.task;

import com.moduleTesting.portal.dto.TaskDto;

import java.util.List;

public interface TaskService {

    List<TaskDto> findAll();

    List<TaskDto> findAllActiveTasks(String statusName);

    Integer changeTaskStatus(Integer taskId, Integer statusId);

    List<TaskDto> createNewTask(TaskDto taskDto);
}
