package com.moduleTesting.portal.service.user;

import com.moduleTesting.portal.entity.UserEntity;

import java.util.List;

public interface UserService {

    List<UserEntity> findAll();
}
