package com.moduleTesting.portal.repository;

import com.moduleTesting.portal.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findAll();

    List<UserEntity> findAllByRoleEntity_NameContains(String status);

    Optional<UserEntity> getUserByIdAndRoleEntity_Name(Integer userId, String roleName);

    @Modifying
    @Query("UPDATE UserEntity user SET user.lastName = ?2, user.firstName = ?3, user.patronymic = ?4," +
        " user.birthday = ?5, user.login = ?6, user.password = ?7, user.money = ?8 WHERE user.id = ?1")
    Integer updateUser(Integer userId, String lastName, String firstName, String patronymic,
                       Date birthday, String login, String password, Float money);

    @Modifying
    @Query("UPDATE UserEntity userEntity SET userEntity.status.id = ?2 WHERE userEntity.id = ?1")
    Integer updateUserStatus(Integer userId, Integer statusId);
}