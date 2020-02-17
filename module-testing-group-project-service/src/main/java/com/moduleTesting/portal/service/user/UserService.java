package com.moduleTesting.portal.service.user;

import com.moduleTesting.portal.dto.UserDto;
import com.moduleTesting.portal.dto.UserRole;

import java.util.Date;
import java.util.List;

public interface UserService {

    List<UserDto> findAll();

    List<UserDto> findAllDrivers();

    UserDto getUserById(Integer userId);

    UserDto editUser(Integer userId, String lastName, String firstName, String patronymic, Date birthday);

    UserDto changeUserStatus(Integer userId, UserRole userRole);

    void transferMoney(Integer userId, Float money);

    List<UserDto> deleteUser(Integer userId);

    void createNewUser (String lastName, String firstName, String patronymic, Date birthday, String email, String password,
                        Integer roleId, Integer statusId);
}
