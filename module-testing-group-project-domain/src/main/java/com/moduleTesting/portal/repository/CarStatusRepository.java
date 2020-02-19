package com.moduleTesting.portal.repository;

import com.moduleTesting.portal.entity.CarStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarStatusRepository extends JpaRepository<CarStatusEntity, Long> {

    List<CarStatusEntity> findAll();

    CarStatusEntity findByName(String name);
}
