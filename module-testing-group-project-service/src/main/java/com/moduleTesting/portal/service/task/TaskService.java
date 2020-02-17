package com.moduleTesting.portal.service.task;

import com.moduleTesting.portal.dto.TaskDto;

import java.util.List;

public interface TaskService {

    List<TaskDto> findAll();

    List<TaskDto> findAllActiveTasks();

    TaskDto changeTaskStatus(Integer taskId);

    List<TaskDto> createNewTask();
}
