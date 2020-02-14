package com.moduleTesting.portal.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
public class User {

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
    private Role roleId;

    @JoinColumn(name = "status_id")
    @ManyToOne
    private UserStatus statusId;

    public User() {
    }

    public User(String lastName, String firstName, String patronymic, Date birthday, String login, String password, Float money, Role roleId, UserStatus statusId) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.login = login;
        this.password = password;
        this.money = money;
        this.roleId = roleId;
        this.statusId = statusId;
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

    public Role getRoleId() {
        return roleId;
    }

    public void setRoleId(Role roleId) {
        this.roleId = roleId;
    }

    public UserStatus getStatusId() {
        return statusId;
    }

    public void setStatusId(UserStatus statusId) {
        this.statusId = statusId;
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
            ", roleId=" + roleId +
            ", statusId=" + statusId +
            '}';
    }
}
