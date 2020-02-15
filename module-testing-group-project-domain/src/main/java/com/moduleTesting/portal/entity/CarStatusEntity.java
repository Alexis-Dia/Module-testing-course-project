package com.moduleTesting.portal.entity;

import javax.persistence.*;

@Entity
@Table(name = "car_status")
public class CarStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    public CarStatusEntity() {
    }

    public CarStatusEntity(String name) {
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
        return "CarStatus{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}
