package com.moduleTesting.portal.service.user.impl;

import com.moduleTesting.portal.dto.UserDto;
import com.moduleTesting.portal.dto.UserRole;
import com.moduleTesting.portal.dto.UserStatus;
import com.moduleTesting.portal.entity.UserEntity;
import com.moduleTesting.portal.repository.RoleRepository;
import com.moduleTesting.portal.repository.UserRepository;
import com.moduleTesting.portal.repository.UserStatusRepository;
import com.moduleTesting.portal.service.user.UserService;
import exceptions.NotCurrentUserException;
import exceptions.NotEnoughPoundsException;
import exceptions.UserAlreadyExistsException;
import exceptions.UserNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

import static com.moduleTesting.portal.service.TestData.*;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    private static final int ADMIN_ID = 1;
    private static final String DRIVER = "DRIVER";

    @Autowired
    private UserService userService;

    @TestConfiguration
    static class userServiceImplTestContextConfiguration {

        @Bean
        public UserService reportService() {
            return new UserServiceImpl();
        }
    }

    @MockBean
    UserRepository userRepository;

    @MockBean
    UserStatusRepository userStatusRepository;

    @MockBean
    RoleRepository roleRepository;

    @Before
    public void setUp() {
        doReturn(Collections.singletonList(USER_ENTITY)).when(userRepository).findAll();
        doReturn(Collections.singletonList(USER_ENTITY)).when(userRepository).findAllByRoleEntity_NameContains(UserRole.USER.getName());
        doReturn(Collections.singletonList(USER_ENTITY)).when(userRepository).findAllByRoleEntity_NameContains(UserRole.ADMIN.getName());
        doReturn(Optional.of(USER_ENTITY)).when(userRepository).getUserByIdAndRoleEntity_Name(EXISTED_USER_ID, DRIVER);
        doReturn(Optional.empty()).when(userRepository).getUserByIdAndRoleEntity_Name(NOT_EXISTED_USER_ID, DRIVER);
        doReturn(Optional.of(USER_ENTITY)).when(userRepository).findByLogin(EXISTED_EMAIL);
        doReturn(Optional.empty()).when(userRepository).findByLogin(NOT_EXISTED_EMAIL);
        doReturn(NUMBER_OF_ROW_SUCCESS).when(userRepository).updateUser(
            USER_DTO.getUserID(),
            USER_DTO.getLastName(),
            USER_DTO.getFirstName(),
            USER_DTO.getPatronymic(),
            USER_DTO.getBirthday(),
            USER_DTO.getEmailAddress(),
            USER_DTO.getPassword(),
            USER_DTO.getMoney()
        );
        doReturn(NUMBER_OF_ROW_SUCCESS).when(userRepository).updateUserStatus(EXISTED_USER_ID, UserStatus.BUSY.getId());
        doReturn(NUMBER_OF_ROW_SUCCESS).when(userRepository).deleteById(EXISTED_USER_ID);
        doReturn(NUMBER_OF_ROW_SUCCESS).when(userRepository).deleteById(EXISTED_USER_ID);
        doReturn(NUMBER_OF_ROW_NOT_SUCCESS).when(userRepository).deleteById(NOT_EXISTED_USER_ID);
        doReturn(Optional.of(ROLE_ENTITY)).when(roleRepository).findByName(DRIVER);
        doReturn(Optional.of(USER_STATUS_ENTITY)).when(userStatusRepository).findByName(FREE);
        doReturn(NOT_EXISTED_USER_ENTITY).when(userRepository).save(NOT_EXISTED_USER_ENTITY);
        doReturn(NUMBER_OF_ROW_SUCCESS).when(userRepository).updateBalance(EXISTED_USER_ID, REWARD);
        doReturn(NUMBER_OF_ROW_SUCCESS).when(userRepository).updateBalance(EXISTED_ADMIN_ID, RESULT_AMOUNT);
    }

    @Test
    public void testFindAll_Ok() {
        userService.findAll();

        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).findAll();
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testFindAllDrivers_Ok() {
        userService.findAllDrivers();

        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).findAllByRoleEntity_NameContains(UserRole.USER.getName());
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testGetDriverById_Ok() {
        userService.getDriverById(EXISTED_USER_ID);

        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).getUserByIdAndRoleEntity_Name(EXISTED_USER_ID, DRIVER);
        verifyNoMoreInteractions(userRepository);
    }

    @Test(expected= UserNotFoundException.class)
    public void testGetDriverById_UserWasNotFound() {
        userService.getDriverById(NOT_EXISTED_USER_ID);

        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ZERO)).getUserByIdAndRoleEntity_Name(EXISTED_USER_ID, DRIVER);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testGetMe_Ok() {
        userService.getMe(EXISTED_USER_ID, EXISTED_EMAIL);

        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).getUserByIdAndRoleEntity_Name(EXISTED_USER_ID, DRIVER);
        verifyNoMoreInteractions(userRepository);
    }

    @Test(expected= UserNotFoundException.class)
    public void testGetMe_UserWasNotFound() {
        userService.getMe(NOT_EXISTED_USER_ID, EXISTED_EMAIL);

        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ZERO)).getUserByIdAndRoleEntity_Name(NOT_EXISTED_USER_ID, DRIVER);
        verifyNoMoreInteractions(userRepository);
    }

    @Test(expected= NotCurrentUserException.class)
    public void testGetMe_NotCurrentUser() {
        userService.getMe(EXISTED_USER_ID, EXISTED_EMAIL_2);

        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).getUserByIdAndRoleEntity_Name(NOT_EXISTED_USER_ID, DRIVER);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testGetAdmin_Ok() {
        userService.getAdmin();

        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).findAllByRoleEntity_NameContains(UserRole.ADMIN.getName());
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testFindByLogin_UserIsPresent_Ok() {
        userService.findByLogin(EXISTED_EMAIL);

        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).findByLogin(EXISTED_EMAIL);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testFindByLogin_UserIsPresent_Not() {
        Optional<UserDto> userByLogin = userService.findByLogin(NOT_EXISTED_EMAIL);

        assertFalse(userByLogin.isPresent());
        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).findByLogin(NOT_EXISTED_EMAIL);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testEditUser_Ok() {
        userService.editUser(USER_DTO);

        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).updateUser(
            USER_DTO.getUserID(),
            USER_DTO.getLastName(),
            USER_DTO.getFirstName(),
            USER_DTO.getPatronymic(),
            USER_DTO.getBirthday(),
            USER_DTO.getEmailAddress(),
            USER_DTO.getPassword(),
            USER_DTO.getMoney()
        );
        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).getUserByIdAndRoleEntity_Name(USER_DTO.getUserID(), DRIVER);
        verifyNoMoreInteractions(userRepository);
    }

    @Test(expected= UserNotFoundException.class)
    public void testEditUser_UserNotFound() {
        userService.editUser(new UserDto(
            NOT_EXISTED_USER_ID,
            USER_DTO.getLastName(),
            USER_DTO.getFirstName(),
            USER_DTO.getPatronymic(),
            USER_DTO.getBirthday(),
            USER_DTO.getEmailAddress(),
            USER_DTO.getPassword(),
            USER_DTO.getMoney()
        ));

        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).updateUser(
            NOT_EXISTED_USER_ID,
            USER_DTO.getLastName(),
            USER_DTO.getFirstName(),
            USER_DTO.getPatronymic(),
            USER_DTO.getBirthday(),
            USER_DTO.getEmailAddress(),
            USER_DTO.getPassword(),
            USER_DTO.getMoney()
        );
        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ZERO)).getUserByIdAndRoleEntity_Name(USER_DTO.getUserID(), DRIVER);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testEditMe_Ok() {
        userService.editMe(USER_DTO, EXISTED_EMAIL);

        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).updateUser(
            USER_DTO.getUserID(),
            USER_DTO.getLastName(),
            USER_DTO.getFirstName(),
            USER_DTO.getPatronymic(),
            USER_DTO.getBirthday(),
            USER_DTO.getEmailAddress(),
            USER_DTO.getPassword(),
            USER_DTO.getMoney()
        );
        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).getUserByIdAndRoleEntity_Name(USER_DTO.getUserID(), DRIVER);
        verifyNoMoreInteractions(userRepository);
    }

    @Test(expected= NotCurrentUserException.class)
    public void testEditMe_NotCurrentUser() {
        userService.editMe(USER_DTO, NOT_EXISTED_EMAIL);

        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ZERO)).updateUser(
            USER_DTO.getUserID(),
            USER_DTO.getLastName(),
            USER_DTO.getFirstName(),
            USER_DTO.getPatronymic(),
            USER_DTO.getBirthday(),
            USER_DTO.getEmailAddress(),
            USER_DTO.getPassword(),
            USER_DTO.getMoney()
        );
        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ZERO)).getUserByIdAndRoleEntity_Name(USER_DTO.getUserID(), DRIVER);
        verifyNoMoreInteractions(userRepository);
    }

    @Test(expected= UserNotFoundException.class)
    public void testEditMe_UserNotFound() {
        userService.editMe(new UserDto(
            NOT_EXISTED_USER_ID,
            USER_DTO.getLastName(),
            USER_DTO.getFirstName(),
            USER_DTO.getPatronymic(),
            USER_DTO.getBirthday(),
            USER_DTO.getEmailAddress(),
            USER_DTO.getPassword(),
            USER_DTO.getMoney()
        ), EXISTED_EMAIL);

        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).updateUser(
            NOT_EXISTED_USER_ID,
            USER_DTO.getLastName(),
            USER_DTO.getFirstName(),
            USER_DTO.getPatronymic(),
            USER_DTO.getBirthday(),
            USER_DTO.getEmailAddress(),
            USER_DTO.getPassword(),
            USER_DTO.getMoney()
        );
        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ZERO)).getUserByIdAndRoleEntity_Name(USER_DTO.getUserID(), DRIVER);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testChangeUserStatus_Ok() {
        userService.changeUserStatus(EXISTED_USER_ID, UserStatus.FREE.getId());

        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).updateUserStatus(EXISTED_USER_ID, UserStatus.FREE.getId());
        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).getUserByIdAndRoleEntity_Name(EXISTED_USER_ID, DRIVER);
        verifyNoMoreInteractions(userRepository);
    }

    @Test(expected= UserNotFoundException.class)
    public void testChangeUserStatus_UserNotFound() {
        userService.changeUserStatus(NOT_EXISTED_USER_ID, UserStatus.FREE.getId());

        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).updateUserStatus(EXISTED_USER_ID, UserStatus.FREE.getId());
        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).getUserByIdAndRoleEntity_Name(NOT_EXISTED_USER_ID, DRIVER);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testDeleteUser_Ok() {
        userService.deleteUser(EXISTED_USER_ID);

        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).getUserByIdAndRoleEntity_Name(EXISTED_USER_ID, DRIVER);
        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).deleteById(EXISTED_USER_ID);
        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).findAll();
        verifyNoMoreInteractions(userRepository);
    }

    @Test(expected= UserNotFoundException.class)
    public void testDeleteUser_UserNotFound() {
        userService.deleteUser(NOT_EXISTED_USER_ID);

        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).getUserByIdAndRoleEntity_Name(NOT_EXISTED_USER_ID, DRIVER);
        verifyNoMoreInteractions(userRepository);
    }

    @Test(expected= UserAlreadyExistsException.class)
    public void testCreateNewUser_UserAlreadyExists() {
        userService.createNewUser(USER_DTO);

        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).findByLogin(EXISTED_EMAIL);
        verify(roleRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ZERO)).findByName(DRIVER);
        verify(userStatusRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ZERO)).findByName(FREE);
        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ZERO)).getUserByIdAndRoleEntity_Name(USER_DTO.getUserID(), DRIVER);
        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ZERO)).save(NOT_EXISTED_USER_ENTITY);
        verifyNoMoreInteractions(userRepository);
    }

    //FIXME - I don't know how to test fork with orElse -operator in UserServiceImpl class in createNewUser method
    @Test
    public void testCreateNewUser_Ok() {
        userService.createNewUser(
            new UserDto(
                NOT_EXISTED_USER_ID,
                USER_DTO.getLastName(),
                USER_DTO.getFirstName(),
                USER_DTO.getPatronymic(),
                null,
                NOT_EXISTED_EMAIL,
                USER_DTO.getPassword(),
                USER_DTO.getMoney()
            )
        );

        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).findByLogin(NOT_EXISTED_EMAIL);
        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).getUserByIdAndRoleEntity_Name(NOT_EXISTED_USER_ID, DRIVER);
        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).save(ArgumentMatchers.any(UserEntity.class));
    }


    @Test
    public void testTransferMoney_Ok() {
        userService.transferMoney(EXISTED_USER_ID, REWARD);

        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).findAllByRoleEntity_NameContains(UserRole.ADMIN.getName());
        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).updateBalance(EXISTED_USER_ID, REWARD);
        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).updateBalance(ADMIN_ID, RESULT_AMOUNT);

        verifyNoMoreInteractions(userRepository);
    }

    @Test(expected= NotEnoughPoundsException.class)
    public void testTransferMoney_NotEnoughPounds() {
        userService.transferMoney(EXISTED_USER_ID, REWARD_500);

        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).findAllByRoleEntity_NameContains(UserRole.ADMIN.getName());
        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).updateBalance(EXISTED_USER_ID, REWARD_500);
        verify(userRepository, Mockito.times(WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME)).updateBalance(ADMIN_ID, RESULT_AMOUNT);

        verifyNoMoreInteractions(userRepository);
    }
}
