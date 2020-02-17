package com.moduleTesting.portal.service.user.impl;

import com.moduleTesting.portal.dto.UserRole;
import com.moduleTesting.portal.dto.UserDto;
import com.moduleTesting.portal.entity.UserEntity;
import com.moduleTesting.portal.repository.UserRepository;
import com.moduleTesting.portal.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<UserEntity> findAllDrivers() {
        return null;
    }

    @Override
    public UserDto getUserById(int id) {
        return null;
    }

    @Override
    public void editUser(int userId, String lastName, String firstName, String patronymic, Date birthday) {
    }

    @Override
    public void changeStatus(int userId, UserRole userRole) {
    }

    @Override
    public void transferMoney(int userId, float money) {
    }

    @Override
    public void deleteUser(int userId) {
    }

    @Override
    public void createNewUser(String lastName, String firstName, String patronymic, Date birthday, String email,
                              String password, Integer roleId, Integer statusId) {
    }

}
