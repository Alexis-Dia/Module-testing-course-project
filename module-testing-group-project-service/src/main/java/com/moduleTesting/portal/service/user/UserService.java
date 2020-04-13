package com.moduleTesting.portal.service.user;

import com.moduleTesting.portal.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> findAll();

    List<UserDto> findAllDrivers();

    UserDto getDriverById(Integer userId);

    UserDto getMe(Integer userId, String authenticationName);

    UserDto getAdmin();

    Optional<UserDto> findByLogin(String login);

    UserDto editUser(UserDto userDto);

    UserDto editMe(UserDto userDto, String authenticationName);

    UserDto changeUserStatus(Integer userId, Integer userStatus);

    List<UserDto> deleteUser(Integer userId);

    void createNewUser(UserDto userDto);

    void transferMoney(Integer userId, Float money) throws Exception;

    void informBankManager() throws Exception;

    void withdraw(Integer fromUser, Float amount) throws Exception;

    void deposit(Integer toUser, Float amount) throws Exception;
}
