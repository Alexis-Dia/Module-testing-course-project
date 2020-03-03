package com.moduleTesting.portal.rest.controllers;

import com.moduleTesting.portal.dto.TaskDto;
import com.moduleTesting.portal.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/all")
    public List<TaskDto> getAllTasks() {

        final List<TaskDto> allTask = taskService.findAll();
        System.out.println(allTask);

        return allTask;
    }

    @GetMapping("/allMine")
    public List<TaskDto> getAllMineTasks() {

        final String authenticationName = SecurityContextHolder.getContext().getAuthentication().getName();

        final List<TaskDto> allTask = taskService.findAllMineTasks(authenticationName);
        System.out.println(allTask);

        return allTask;
    }

    @GetMapping("/byStatus")
    public List<TaskDto> getAllActiveTasks(@RequestParam("taskStatus") String taskStatus) {

        final List<TaskDto> allTask = taskService.findAllActiveTasks(taskStatus);
        System.out.println(allTask);

        return allTask;
    }

    @PutMapping("/changeTaskStatus")
    public Integer changeTaskStatus(@RequestParam("taskId") Integer taskId, @RequestParam("statusId") Integer statusId) {
        Integer changedRow = taskService.changeTaskStatus(taskId, statusId);

        return changedRow;
    }

    @PutMapping("/takeTask")
    public Integer takeTask(@RequestParam("taskId") Integer taskId, @RequestParam("carId") Integer carId) {
        final String authenticationName = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer changedRow = taskService.takeTask(taskId, carId, authenticationName);

        return changedRow;
    }

    @PostMapping("/createNew")
    public List<TaskDto> createNewTask(@RequestBody TaskDto taskDto) {
        List<TaskDto> tasks = taskService.createNewTask(taskDto);

        return tasks;
    }

}
