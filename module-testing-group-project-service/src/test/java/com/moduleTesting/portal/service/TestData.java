package com.moduleTesting.portal.service;

import com.moduleTesting.portal.dto.*;
import com.moduleTesting.portal.entity.*;

import java.util.Date;

public interface TestData {

    int WANTED_NUMBER_OF_INVOCATIONS_ONE_TIME = 1;
    int WANTED_NUMBER_OF_INVOCATIONS_ZERO = 0;
    int NUMBER_OF_ROW_SUCCESS = 1;
    int NUMBER_OF_ROW_NOT_SUCCESS = 0;
    float REWARD = 30.0f;
    float REWARD_500 = 500.0f;
    float RESULT_AMOUNT = 70f;
    float RESULT_DRIVER_AMOUNT = 130f;
    String CAR_STATUS_FREE = "FREE";
    String EXISTED_EMAIL = "sidorov@tut.by";
    String EXISTED_EMAIL_2 = "ivanov@tut.by";
    String NOT_EXISTED_EMAIL = "notexistet@mail.ru";
    String EXISTED_TASK_STATUS = "FREE";
    String NOT_EXISTED_TASK_STATUS = "NOT_EXISTED_STATUS";
    String CAR_NUMBER_JX_1234 = "JX-1234";
    String FREE = "FREE";
    Integer EXISTED_USER_ID = 1;
    Integer EXISTED_ADMIN_ID = 1;
    Integer NOT_EXISTED_USER_ID = 100;
    RoleEntity USER_ROLE = new RoleEntity(2,"DRIVER", 1);
    UserStatusEntity USER_STATUS = new UserStatusEntity(1, FREE);
    UserEntity USER_ENTITY = new UserEntity(
        1,
        "Alex",
        "Alexey",
        "Alexeyevich",
        new Date(),
        EXISTED_EMAIL,
        "alex",
        100.0f,
        USER_ROLE,
        USER_STATUS
    );
    UserEntity NOT_EXISTED_USER_ENTITY = new UserEntity(
        null,
        "Alex",
        "Alexeev",
        "Alexeevich",
        null,
        NOT_EXISTED_EMAIL,
        "1234",
        0.0f,
        new RoleEntity(2,"DRIVER", 2),
        USER_STATUS
    );
    CarEntity CAR_ENTITY = new CarEntity(
        new BrandEntity(),
        new Date(),
        CAR_NUMBER_JX_1234,
        new Date(),
        new CarStatusEntity(CarStatus.FREE.getName())
    );
    TaskStatusEntity FREE_TASK_STATUS_ENTITY = new TaskStatusEntity(1, FREE);
    TaskEntity TASK_ENTITY = new TaskEntity(
        "Minsk-Gomel",
        1000.0f,
        500.0f,
        USER_ENTITY,
        CAR_ENTITY,
        FREE_TASK_STATUS_ENTITY,
        500.0f
    );
    TaskDto TASK_DTO = new TaskDto(
        1,
        100f,
        100f,
        new UserDto(),
        new CarDto(),
        TaskStatus.IN_PROGRESS,
        "Alex",
        null,
        100f
    );
    UserDto USER_DTO = new UserDto(
        1,
        "Alex",
        "Alexeev",
        "Alexeevich",
        new Date(),
        EXISTED_EMAIL,
        "1234",
        0f
    );
    UserStatusEntity USER_STATUS_ENTITY = new UserStatusEntity(FREE);
    RoleEntity ROLE_ENTITY = new RoleEntity("Alex", 1);
}
