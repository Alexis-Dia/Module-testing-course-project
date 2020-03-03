package com.moduleTesting.portal.repository;

import com.moduleTesting.portal.entity.TaskStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskStatusRepository extends JpaRepository<TaskStatusEntity, Long> {

    TaskStatusEntity findById(Integer taskId);

}
