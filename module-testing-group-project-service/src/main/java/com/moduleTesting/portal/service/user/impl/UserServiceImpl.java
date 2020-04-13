package com.moduleTesting.portal.service.user.impl;

import com.moduleTesting.portal.dto.UserDto;
import com.moduleTesting.portal.dto.UserRole;
import com.moduleTesting.portal.dto.UserStatus;
import com.moduleTesting.portal.entity.RoleEntity;
import com.moduleTesting.portal.entity.UserEntity;
import com.moduleTesting.portal.entity.UserStatusEntity;
import com.moduleTesting.portal.repository.CarRepository;
import com.moduleTesting.portal.repository.RoleRepository;
import com.moduleTesting.portal.repository.UserRepository;
import com.moduleTesting.portal.repository.UserStatusRepository;
import com.moduleTesting.portal.service.annotations.SelfInject;
import com.moduleTesting.portal.service.mapper.DtoMapper;
import com.moduleTesting.portal.service.user.UserService;
import exceptions.NotCurrentUserException;
import exceptions.NotEnoughPoundsException;
import exceptions.UserAlreadyExistsException;
import exceptions.UserNotFoundException;
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

    @Autowired
    CarRepository carRepository;

    @SelfInject
    public UserService userServiceProxy;

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

    /**
     * This is the case from Eugene Borisov report with self-inject problem in Spring.
     * By default Spring makes rollbackFor RunTimeException. Therefore we have to consider it.
     * Using noRollbackFor = {RuntimeException.class} I just want to say that I don't need default behaviour in transaction.
     * The helpful article about @Transaction - https://akorsa.ru/2017/01/sovety-i-oshibki-v-spring-transactions/
     * @param userId
     * @param amount
     * @throws Exception
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = {RuntimeException.class})
    public void transferMoney(Integer userId, Float amount) throws Exception {
        userRepository.updateUserStatus(userId, UserStatus.FREE.getId());
        userServiceProxy.withdraw(ADMIN_ID, amount);
        userServiceProxy.deposit(userId, amount);
    }

    @Transactional(propagation = Propagation.MANDATORY, noRollbackFor = {RuntimeException.class})
    public void withdraw(Integer fromUser, Float amount) throws Exception {
        Float adminInitialAmount = getAdmin().getMoney();
        Float resultAdminAmount = adminInitialAmount - amount;
        if (amount < adminInitialAmount ) {
            userRepository.updateBalance(fromUser, resultAdminAmount);
        } else {
            throw new NotEnoughPoundsException(NOT_ENOUGH_POUNDS_ON_ADMIN_ACCOUNT);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = {RuntimeException.class})
    public void deposit(Integer toUser, Float amount) throws Exception {
        Float userInitialAmount = getDriverById(toUser).getMoney();
        Float resultUserAmount = userInitialAmount + amount;
        userRepository.updateBalance(toUser, resultUserAmount);
        userServiceProxy.informBankManager();
    }

    /**
     *     In case when noRollbackFor = {Exception.class, IllegalStateException.class} and we have two queries: userRepository.updateUser,
     *     carRepository.updateCar here and when second one will throw an exception (existedBrandId - doesn't exist), for some reason Spring will throw:
     *     org.springframework.transaction.UnexpectedRollbackException - "Transaction silently rolled back because it has been marked as rollback-only"
     *     this transaction for some reason will be rolled back.
     *     I found one answer, but I don't sure that it's the right reason:
     *     https://stackoverflow.com/questions/19349898/unexpectedrollbackexception-transaction-rolled-back-because-it-has-been-marked
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = {Exception.class, RuntimeException.class})
    public void informBankManager() throws Exception {
        System.out.println("Send email to the manager.");
/*        final int existedCarId = 8;
        final int existedBrandId = 6;
        final int unExistedBrandId = 111;
        final String newNumber = "HT-8324";
        final int existedUserId = 9;
        final String newLastName = "Vasilev5";
        userRepository.updateLastName(existedUserId, newLastName);
        carRepository.updateCar(existedCarId, unExistedBrandId, new Date(), newNumber, new Date(), CarStatus.FREE.getId());*/
        //final int i = 1/0;
        //throw new Exception();
        //throw new RuntimeException();
    }

}
