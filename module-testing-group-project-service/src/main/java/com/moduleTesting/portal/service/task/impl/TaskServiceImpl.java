package com.moduleTesting.portal.service.task.impl;

import com.moduleTesting.portal.entity.Task;
import com.moduleTesting.portal.repository.TaskRepository;
import com.moduleTesting.portal.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }
}
