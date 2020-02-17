package com.moduleTesting.portal.service.mapper;

import com.moduleTesting.portal.dto.UserDto;
import com.moduleTesting.portal.dto.UserRole;
import com.moduleTesting.portal.dto.UserStatus;
import com.moduleTesting.portal.entity.RoleEntity;
import com.moduleTesting.portal.entity.UserEntity;
import com.moduleTesting.portal.entity.UserStatusEntity;

import java.util.Arrays;

public class DtoMapper {

    public static UserRole toUserRole(RoleEntity roleEntity) {
        return Arrays.stream(UserRole.values()).filter(role -> role.getName().equals(roleEntity.getName())).findAny().get();
    }

    public static UserStatus toUserStatus(UserStatusEntity userStatusEntity) {
        return Arrays.stream(UserStatus.values()).filter(userStatus -> userStatus.getName().equals(userStatusEntity.getName())).findAny().get();
    }

    public static UserDto toUserDto(UserEntity userEntity) {

        final UserRole userRole = toUserRole(userEntity.getRoleEntity());
        final UserStatus userStatus = toUserStatus(userEntity.getUserStatusEntity());

        return new UserDto(userEntity.getId(), userEntity.getLastName(), userEntity.getFirstName(),
            userEntity.getPatronymic(), userEntity.getBirthday(), userEntity.getLogin(), userEntity.getPassword(),
            userEntity.getMoney(), userRole, userStatus);
    }

}
