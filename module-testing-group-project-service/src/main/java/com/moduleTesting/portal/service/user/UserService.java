package com.moduleTesting.portal.service.user;

import com.moduleTesting.portal.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> findAll();

    List<UserDto> findAllDrivers();

    UserDto getDriverById(Integer userId);

    UserDto getMe(Integer userId, String authenticationName);

    void getDriverByNameNew_testMandotaryRequiredNewTransaction();

    UserDto getAdmin();

    Optional<UserDto> findByLogin(String login);

    UserDto editUser(UserDto userDto);

    UserDto editMe(UserDto userDto, String authenticationName);

    UserDto changeUserStatus(Integer userId, Integer userStatus);

    List<UserDto> deleteUser(Integer userId);

    void createNewUser(UserDto userDto);

    void transferMoney(Integer userId, Float money);
}
