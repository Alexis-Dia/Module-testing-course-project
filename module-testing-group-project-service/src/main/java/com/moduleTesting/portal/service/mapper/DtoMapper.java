package com.moduleTesting.portal.service.mapper;

import com.moduleTesting.portal.dto.*;
import com.moduleTesting.portal.entity.*;

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

    public static CarStatus toCarStatus(CarStatusEntity carStatusEntity) {
        return Arrays.stream(CarStatus.values()).filter(carStatus -> carStatus.getName().equals(carStatusEntity.getName())).findAny().get();
    }

    public static BrandDto toBrandDto (BrandEntity brandEntity) {
        return new BrandDto(brandEntity.getId(), brandEntity.getBrand(), brandEntity.getCarryingCapacity(), brandEntity.getModel());
    }

    public static CarDto toCarDto(CarEntity carEntity) {
        final CarStatus carStatus = DtoMapper.toCarStatus(carEntity.getCarStatusEntity());
        final BrandDto brandDto = DtoMapper.toBrandDto(carEntity.getBrandEntity());
        return new CarDto(carEntity.getId(), brandDto, carEntity.getYear(), carEntity.getNumber(),
            carEntity.getDateOfReceipt(), carStatus);
    }

}
