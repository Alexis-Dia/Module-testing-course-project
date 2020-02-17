package com.moduleTesting.portal.rest.controllers;

import com.moduleTesting.portal.dto.*;
import com.moduleTesting.portal.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/all")
    public List<TaskDto> getAllTasks() {

        final List<TaskDto> allTask = taskService.findAll();
        System.out.println(allTask);

        return allTask;
    }

    @PostMapping("/allActive")
    public List<TaskDto> getAllActiveTasks() {

        final List<TaskDto> allTask = taskService.findAllActiveTasks();
        System.out.println(allTask);

        return allTask;
    }

    @PostMapping("/changeTaskStatus")
    public TaskDto changeTaskStatus(Integer taskId) {
        TaskDto taskDto = taskService.changeTaskStatus(taskId);

        return taskDto;
    }

    @PostMapping("/createNewTask")
    public List<TaskDto> createNewTask(Integer taskId) {
        List<TaskDto> tasks = taskService.createNewTask();

        return tasks;
    }

}
