package com.moduleTesting.portal.repository;

import com.moduleTesting.portal.entity.CarStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarStatusRepository extends JpaRepository<CarStatus, Long> {

    List<CarStatus> findAll();
}
