package com.moduleTesting.portal.service.task;

import com.moduleTesting.portal.entity.TaskEntity;

import java.util.List;

public interface TaskService {

    List<TaskEntity> findAll();
}
