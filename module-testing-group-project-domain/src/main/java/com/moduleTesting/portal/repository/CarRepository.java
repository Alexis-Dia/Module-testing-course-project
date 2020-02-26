package com.moduleTesting.portal.repository;

import com.moduleTesting.portal.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {

    List<CarEntity> findAll();

    Optional<CarEntity> findById(Integer brandId);

    List<CarEntity> findByCarStatusEntity_Name(String status);

    @Modifying
    @Query("UPDATE CarEntity car SET car.brandEntity.id = ?2, car.year = ?3, car.number = ?4, car.dateOfReceipt = ?5, car.carStatusEntity.id = ?6 WHERE car.id = ?1")
    Integer updateCar(Integer carId, Integer brandId, Date year, String number, Date dateOfReceipt, Integer carStatusId);

    @Modifying
    @Query("DELETE FROM CarEntity car WHERE car.id = ?1")
    Integer removeCarById(Integer carId);
}