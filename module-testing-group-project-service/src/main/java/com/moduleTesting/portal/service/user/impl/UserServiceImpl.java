package com.moduleTesting.portal.service.user.impl;

import com.moduleTesting.portal.entity.User;
import com.moduleTesting.portal.repository.UserRepository;
import com.moduleTesting.portal.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
