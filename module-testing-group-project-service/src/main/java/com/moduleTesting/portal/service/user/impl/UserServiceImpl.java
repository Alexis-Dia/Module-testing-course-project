package com.moduleTesting.portal.service.user.impl;

import com.moduleTesting.portal.dto.UserDto;
import com.moduleTesting.portal.dto.UserRole;
import com.moduleTesting.portal.dto.UserStatus;
import com.moduleTesting.portal.entity.RoleEntity;
import com.moduleTesting.portal.entity.UserEntity;
import com.moduleTesting.portal.entity.UserStatusEntity;
import com.moduleTesting.portal.repository.RoleRepository;
import com.moduleTesting.portal.repository.UserRepository;
import com.moduleTesting.portal.repository.UserStatusRepository;
import com.moduleTesting.portal.service.mapper.DtoMapper;
import com.moduleTesting.portal.service.user.UserService;
import exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.moduleTesting.portal.consts.Common.*;

@Service
public class UserServiceImpl implements UserService {

    private static final float INITIAL_MONEY_VALUE = 0f;
    private static final int ADMIN_ID = 1;
    private static final String DRIVER = "DRIVER";
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
            () -> new UserNotFoundException(MSG_ERR_USER_WASN_T_FOUND)
        ));
    }

    @Override
    public UserDto getMe(Integer userId, String authenticationName) {

        Optional<UserEntity> userByIdAndRoleEntityName = userRepository.getUserByIdAndRoleEntity_Name(userId, DRIVER);

        if (!userByIdAndRoleEntityName.orElseThrow(() -> new UserNotFoundException(MSG_ERR_USER_WASN_T_FOUND)).getLogin().equals(authenticationName)) {
            throw new NotCurrentUserException(MSG_ERR_GETTING_NOT_CURRENT_USER_IS_FORBIDDEN);
        }

        return DtoMapper.toUserDto(userByIdAndRoleEntityName.get());
    }

    @Override
    public UserDto getAdmin() {
        final String admin = UserRole.ADMIN.getName();
        return DtoMapper.toUserDto(userRepository.findAllByRoleEntity_NameContains(admin).stream().findAny().get());
    }

    // FIXME: Try to change if-clause to lambda-style using ifPresent. I've noticed that it demands Java 9+ version of compiler
    @Override
    public Optional<UserDto> findByLogin(String login) {
        Optional<UserEntity> byLogin = userRepository.findByLogin(login);
        Optional<UserEntity> userEntity = byLogin.stream().findAny();
        if (!userEntity.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(DtoMapper.toUserDto(byLogin.stream().findAny().get()));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserDto editUser(UserDto userDto) {
        userRepository.updateUser(userDto.getUserID(), userDto.getLastName(),
            userDto.getFirstName(), userDto.getPatronymic(),userDto.getBirthday(), userDto.getEmailAddress(),
            userDto.getPassword(), userDto.getMoney());

        return DtoMapper.toUserDto(userRepository.getUserByIdAndRoleEntity_Name(userDto.getUserID(), DRIVER).orElseThrow(
            () -> new UserNotFoundException(MSG_ERR_USER_WASN_T_FOUND)
        ));
     }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserDto editMe(UserDto userDto, String authenticationName) {

        if (!userDto.getEmailAddress().equals(authenticationName)) {
            throw new NotCurrentUserException(MSG_ERR_EDITING_NOT_CURRENT_USER_IS_FORBIDDEN);
        }

        userRepository.updateUser(userDto.getUserID(), userDto.getLastName(),
            userDto.getFirstName(), userDto.getPatronymic(),userDto.getBirthday(), userDto.getEmailAddress(),
            userDto.getPassword(), userDto.getMoney());

        return DtoMapper.toUserDto(userRepository.getUserByIdAndRoleEntity_Name(userDto.getUserID(), DRIVER).orElseThrow(
            () -> new UserNotFoundException(MSG_ERR_USER_WASN_T_FOUND)
        ));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserDto changeUserStatus(Integer userId, Integer userStatus) {

        userRepository.updateUserStatus(userId, userStatus);

        return DtoMapper.toUserDto(userRepository.getUserByIdAndRoleEntity_Name(userId, DRIVER).orElseThrow(
            () -> new UserNotFoundException(MSG_ERR_USER_WASN_T_FOUND)
        ));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<UserDto> deleteUser(Integer userId) {

        userRepository.getUserByIdAndRoleEntity_Name(userId, DRIVER).orElseThrow(
            () -> new UserNotFoundException(MSG_ERR_USER_WASN_T_FOUND)
        );

        userRepository.deleteById(userId);

        return userRepository.findAll().stream().map(DtoMapper::toUserDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createNewUser(UserDto userDto) {

        //TODO - Try to check working of correct transaction work
        Optional<UserDto> userByLogin = this.findByLogin(userDto.getEmailAddress());
        userByLogin.ifPresent(ob -> {throw new UserAlreadyExistsException(MSG_ERR_USER_ALREADY_EXISTS);});

        final RoleEntity userRole = new RoleEntity(UserRole.USER.getId());

        final UserStatusEntity userStatusEntity = new UserStatusEntity(UserStatus.FREE.getId());

        UserEntity userEntity = userRepository.getUserByIdAndRoleEntity_Name(userDto.getUserID(), DRIVER).orElse(
            new UserEntity(userDto.getLastName(), userDto.getFirstName(), userDto.getPatronymic(), userDto.getBirthday(),
                userDto.getEmailAddress(), userDto.getPassword(), INITIAL_MONEY_VALUE, userRole, userStatusEntity)
        );

        userRepository.save(userEntity);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
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
