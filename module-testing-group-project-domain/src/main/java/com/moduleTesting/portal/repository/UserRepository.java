package com.moduleTesting.portal.repository;

import com.moduleTesting.portal.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findAll();

    List<UserEntity> findAllByRoleEntity_NameContains(String status);

    Optional<UserEntity> getUserByIdAndRoleEntity_Name(Integer userId, String roleName);
}