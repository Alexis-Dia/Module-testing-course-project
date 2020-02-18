package com.moduleTesting.portal.repository;

import com.moduleTesting.portal.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    List<TaskEntity> findAll();

    List<TaskEntity> findByStatusNameContaining(String statusName);

    /**
     * The Second way - is use native query@Query(value = "UPDATE task SET status_id = ?2 WHERE id = ?1", nativeQuery = true)
     * @param taskId
     * @param statusId
     * @return Integer
     */
    @Modifying
    @Query("UPDATE TaskEntity task SET task.status.id = ?2 WHERE task.id = ?1")
    Integer updateStatusById(Integer taskId, Integer statusId);

    List<TaskEntity> findById(Integer taskId);

}
