package com.moduleTesting.portal.service.mapper;

import com.moduleTesting.portal.dto.*;
import com.moduleTesting.portal.entity.*;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class DtoMapper {

    public static UserRole toUserRole(RoleEntity roleEntity) {
        return Arrays.stream(UserRole.values()).filter(role ->
            role.getName().equals(roleEntity.getName())).findAny().get();
    }

    public static UserStatus toUserStatus(UserStatusEntity userStatusEntity) {
        return Arrays.stream(UserStatus.values()).filter(userStatus ->
            userStatus.getName().equals(userStatusEntity.getName())).findAny().get();
    }

    public static UserDto toUserDto(UserEntity userEntity) {

        if (userEntity != null) {
            final UserRole userRole = toUserRole(userEntity.getRoleEntity());
            final UserStatus userStatus = toUserStatus(userEntity.getUserStatusEntity());

            return new UserDto(userEntity.getId(), userEntity.getLastName(), userEntity.getFirstName(),
                userEntity.getPatronymic(), userEntity.getBirthday(), userEntity.getLogin(), userEntity.getPassword(),
                userEntity.getMoney(), userRole, userStatus);

        }
        return new UserDto();
    }

    public static CarStatus toCarStatus(CarStatusEntity carStatusEntity) {
        return Arrays.stream(CarStatus.values()).filter(carStatus ->
            carStatus.getName().equals(carStatusEntity.getName())).findAny().get();
    }

    public static BrandDto toBrandDto (BrandEntity brandEntity) {
        return new BrandDto(brandEntity.getId(), brandEntity.getBrand(),
            brandEntity.getCarryingCapacity(), brandEntity.getModel());
    }

    public static CarDto toCarDto(CarEntity carEntity) {
        if (carEntity != null) {
            final CarStatus carStatus = DtoMapper.toCarStatus(carEntity.getCarStatusEntity());
            final BrandDto brandDto = DtoMapper.toBrandDto(carEntity.getBrandEntity());
            return new CarDto(carEntity.getId(), brandDto, carEntity.getYear(), carEntity.getNumber(),
                carEntity.getDateOfReceipt(), carStatus);
        }
        return new CarDto();
    }

    public static TaskStatus toTaskStatus(TaskStatusEntity taskStatusEntity) {
        return Arrays.stream(TaskStatus.values()).filter(taskStatus ->
            taskStatus.getName().equals(taskStatusEntity.getName())).findAny().get();
    }

    public static ReportDto toReportDto (ReportEntity reportEntity) {
        if (reportEntity != null) {
            return new ReportDto(reportEntity.getId(), reportEntity.getDeparture(), reportEntity.getWeight(),
                reportEntity.getDistance(), reportEntity.getArrival());
        }
        return new ReportDto();
    }

    public static TaskDto toTaskDto(TaskEntity taskEntity) {
        final Set<ReportDto> reports = taskEntity.getReports().stream().map(DtoMapper::toReportDto).collect(Collectors.toSet());
        return new TaskDto(taskEntity.getId(), taskEntity.getSummaryDistance(), taskEntity.getWeight(),
            DtoMapper.toUserDto(taskEntity.getDriver()), DtoMapper.toCarDto(taskEntity.getCar()),
            DtoMapper.toTaskStatus(taskEntity.getStatus()), taskEntity.getName(), reports, taskEntity.getReward());
    }

}
