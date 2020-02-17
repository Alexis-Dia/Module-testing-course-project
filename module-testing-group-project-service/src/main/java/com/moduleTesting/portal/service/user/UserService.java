package com.moduleTesting.portal.service.user;

import com.moduleTesting.portal.dto.UserRole;
import com.moduleTesting.portal.dto.UserDto;
import com.moduleTesting.portal.entity.UserEntity;

import java.util.Date;
import java.util.List;

public interface UserService {

    List<UserEntity> findAll();

    UserDto getUserById(int id);

    void editUser(int userId, String lastName, String firstName, String patronymic, Date birthday);

    void changeStatus(int userId, UserRole userRole);

    void transferMoney(int userId, float money);

    void deleteUser(int userId);

    void createNewUser (String lastName, String firstName, String patronymic, Date birthday, String email, String password,
                        Integer roleId, Integer statusId);
}
