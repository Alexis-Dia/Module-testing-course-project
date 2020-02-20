package com.moduleTesting.portal.repository;

import com.moduleTesting.portal.entity.UserStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserStatusRepository extends JpaRepository<UserStatusEntity, Long> {

    Optional<UserStatusEntity> findByName(String Long);
}
