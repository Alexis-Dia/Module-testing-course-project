package com.moduleTesting.portal.service.task.impl;

import com.moduleTesting.portal.dto.TaskDto;
import com.moduleTesting.portal.repository.TaskRepository;
import com.moduleTesting.portal.service.mapper.DtoMapper;
import com.moduleTesting.portal.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Override
    public List<TaskDto> findAll() {
        return taskRepository.findAll().stream().map(DtoMapper::toTaskDto).collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> findAllActiveTasks() {
        return null;
    }

    @Override
    public TaskDto changeTaskStatus(Integer taskId) {
        return null;
    }

    @Override
    public List<TaskDto> createNewTask() {
        return null;
    }

}
