package com.moduleTesting.portal.service.mapper;

import com.moduleTesting.portal.dto.UserDto;
import com.moduleTesting.portal.entity.UserEntity;

public class DtoMapper {

    public static UserDto toUserDto(UserEntity userEntity) {

        return new UserDto();
    }
}
