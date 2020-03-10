package com.moduleTesting.portal.service.task;

import com.moduleTesting.portal.dto.TaskDto;

import java.util.List;

public interface TaskService {

    List<TaskDto> findAll();

    List<TaskDto> findAllMineTasks(String authenticationName);

    List<TaskDto> findAllByStatusTasks(String statusName);

    Integer changeTaskStatus(Integer taskId, Integer statusId);

    Integer changeTaskStatusToFinish(Integer taskId, Integer statusId, String authenticationName);

    Integer takeTask(Integer taskId, Integer carId, String authenticationName);

    List<TaskDto> createNewTask(TaskDto taskDto);

}
