package com.moduleTesting.portal.entity;

import javax.persistence.*;

@Entity
@Table(name = "task_status")
public class UserStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    public UserStatus() {
    }

    public UserStatus(String name) {
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