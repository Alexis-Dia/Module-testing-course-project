package com.moduleTesting.portal.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_status")
public class UserStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    public UserStatusEntity() {
    }

    public UserStatusEntity(String name) {
        this.name = name;
    }

    public UserStatusEntity(Integer id) {
        this.id = id;
    }

    public UserStatusEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserStatus{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}