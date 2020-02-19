package com.moduleTesting.portal.service.user;

import com.moduleTesting.portal.dto.UserDto;

import java.util.Date;
import java.util.List;

public interface UserService {

    List<UserDto> findAll();

    List<UserDto> findAllDrivers();

    UserDto getDriverById(Integer userId);

    UserDto getAdmin();

    UserDto editUser(UserDto userDto);

    UserDto changeUserStatus(Integer userId, Integer userStatus);

    void transferMoney(Integer userId, Float money);

    List<UserDto> deleteUser(Integer userId);

    void createNewUser (String lastName, String firstName, String patronymic, Date birthday, String email, String password,
                        Integer roleId, Integer statusId);
}
