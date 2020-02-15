package com.moduleTesting.portal.entity;

import javax.persistence.*;
import java.util.Date;

/*
MSSQL has a list of reserved word uncluding word 'user'
https://stackoverflow.com/questions/38818302/incorrect-syntax-near-the-keyword-table-and-could-not-extract-resultset
 */
@Entity
@Table(name = "`user`")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "money")
    private Float money;

    @JoinColumn(name = "role_id")
    @ManyToOne
    private RoleEntity roleEntity;

    @JoinColumn(name = "status_id")
    @ManyToOne
    private UserStatusEntity status;

    public UserEntity() {
    }

    public UserEntity(String lastName, String firstName, String patronymic, Date birthday, String login, String password, Float money, RoleEntity roleEntity, UserStatusEntity status) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.login = login;
        this.password = password;
        this.money = money;
        this.roleEntity = roleEntity;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }

    public UserStatusEntity getStatus() {
        return status;
    }

    public void setStatus(UserStatusEntity status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", lastName='" + lastName + '\'' +
            ", firstName='" + firstName + '\'' +
            ", patronymic='" + patronymic + '\'' +
            ", birthday='" + birthday + '\'' +
            ", login='" + login + '\'' +
            ", password='" + password + '\'' +
            ", money='" + money + '\'' +
            ", role=" + roleEntity +
            ", status=" + status +
            '}';
    }
}
