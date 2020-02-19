package com.moduleTesting.portal.service.user.impl;

import com.moduleTesting.portal.dto.UserDto;
import com.moduleTesting.portal.dto.UserRole;
import com.moduleTesting.portal.repository.UserRepository;
import com.moduleTesting.portal.service.mapper.DtoMapper;
import com.moduleTesting.portal.service.user.UserService;
import exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    public static final String USER_WASN_T_FOUND = "User wasn't found";
    public static final String DRIVER = "DRIVER";

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(DtoMapper::toUserDto).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findAllDrivers() {
        final String driver = UserRole.USER.getName();
        return userRepository.findAllByRoleEntity_NameContains(driver).stream().map(DtoMapper::toUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getDriverById(Integer userId) {
        return DtoMapper.toUserDto(userRepository.getUserByIdAndRoleEntity_Name(userId, DRIVER).orElseThrow(
            () -> new UserNotFoundException(USER_WASN_T_FOUND)
        ));
    }

    @Override
    public UserDto getAdmin() {
        final String admin = UserRole.ADMIN.getName();
        return DtoMapper.toUserDto(userRepository.findAllByRoleEntity_NameContains(admin).stream().findAny().get());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserDto editUser(UserDto userDto) {
        userRepository.updateUser(userDto.getUserID(), userDto.getLastName(),
            userDto.getFirstName(), userDto.getPatronymic(),userDto.getBirthday(), userDto.getEmailAddress(),
            userDto.getPassword(), userDto.getMoney());

        return DtoMapper.toUserDto(userRepository.getUserByIdAndRoleEntity_Name(userDto.getUserID(), DRIVER).orElseThrow(
            () -> new UserNotFoundException(USER_WASN_T_FOUND)
        ));
     }

    @Override
    public UserDto changeUserStatus(Integer userId, UserRole userRole) {
        return null;
    }

    @Override
    public void transferMoney(Integer userId, Float money) {
    }

    @Override
    public List<UserDto> deleteUser(Integer userId) {
        return null;
    }

    @Override
    public void createNewUser(String lastName, String firstName, String patronymic, Date birthday, String email,
                              String password, Integer roleId, Integer statusId) {
    }

}
