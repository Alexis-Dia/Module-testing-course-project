package com.moduleTesting.portal.service.user.impl;

import com.moduleTesting.portal.dto.UserDto;
import com.moduleTesting.portal.dto.UserRole;
import com.moduleTesting.portal.entity.RoleEntity;
import com.moduleTesting.portal.entity.UserEntity;
import com.moduleTesting.portal.entity.UserStatusEntity;
import com.moduleTesting.portal.repository.RoleRepository;
import com.moduleTesting.portal.repository.UserRepository;
import com.moduleTesting.portal.repository.UserStatusRepository;
import com.moduleTesting.portal.service.mapper.DtoMapper;
import com.moduleTesting.portal.service.user.UserService;
import exceptions.NotEnoughPoundsException;
import exceptions.UserNotFoundException;
import exceptions.UserStatusNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final float INITIAL_MONEY_VALUE = 0f;
    private static final int ADMIN_ID = 1;
    private static final String USER_WASN_T_FOUND = "User wasn't found";
    private static final String DRIVER = "DRIVER";
    private static final String USER_STATUS_WASN_T_FOUND = "User status wasn't found";
    private static final String ROLE_WASN_T_FOUND = "Role wasn't found";
    private static final String FREE = "FREE";
    private static final String NOT_ENOUGH_POUNDS_ON_ADMIN_ACCOUNT = "Not enough pounds on admin account!";

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserStatusRepository userStatusRepository;

    @Autowired
    RoleRepository roleRepository;

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
    public UserDto findByLogin(String login) {
        return DtoMapper.toUserDto(userRepository.findByLogin(login).stream().findAny().get());
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
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserDto changeUserStatus(Integer userId, Integer userStatus) {

        userRepository.updateUserStatus(userId, userStatus);

        return DtoMapper.toUserDto(userRepository.getUserByIdAndRoleEntity_Name(userId, DRIVER).orElseThrow(
            () -> new UserNotFoundException(USER_WASN_T_FOUND)
        ));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<UserDto> deleteUser(Integer userId) {

        userRepository.getUserByIdAndRoleEntity_Name(userId, DRIVER).orElseThrow(
            () -> new UserNotFoundException(USER_WASN_T_FOUND)
        );

        userRepository.deleteById(userId);

        return userRepository.findAll().stream().map(DtoMapper::toUserDto).collect(Collectors.toList());
    }

    @Override
    public void createNewUser(UserDto userDto) {

        final RoleEntity userRole = roleRepository.findByName(DRIVER).orElseThrow(
            () -> new UserStatusNotFoundException(ROLE_WASN_T_FOUND)
        );

        final UserStatusEntity userStatusEntity = userStatusRepository.findByName(FREE).orElseThrow(
            () -> new UserStatusNotFoundException(USER_STATUS_WASN_T_FOUND)
        );

        UserEntity userEntity = userRepository.getUserByIdAndRoleEntity_Name(userDto.getUserID(), DRIVER).orElse(
            new UserEntity(userDto.getLastName(), userDto.getFirstName(), userDto.getPatronymic(), userDto.getBirthday(),
                userDto.getEmailAddress(), userDto.getPassword(), INITIAL_MONEY_VALUE, userRole, userStatusEntity)
        );

        userRepository.save(userEntity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void transferMoney(Integer userId, Float reward) {
        Float initialAmount = getAdmin().getMoney();
        Float resultAmount = initialAmount - reward;

        if (reward < initialAmount ) {
            userRepository.updateBalance(userId, reward);
            userRepository.updateBalance(ADMIN_ID, resultAmount);
        } else {
            throw new NotEnoughPoundsException(NOT_ENOUGH_POUNDS_ON_ADMIN_ACCOUNT);
        }
    }
}
