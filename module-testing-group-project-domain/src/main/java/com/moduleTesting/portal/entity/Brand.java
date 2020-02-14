package com.moduleTesting.portal.entity;

import javax.persistence.*;

@Entity
@Table(name = "brand")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "carrying_capacity")
    private Float carryingCapacity;

    @Column(name = "model")
    private String model;

    public Brand() {
    }

    public Brand(String brand, Float carryingCapacity, String model) {
        this.brand = brand;
        this.carryingCapacity = carryingCapacity;
        this.model = model;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Float getCarryingCapacity() {
        return carryingCapacity;
    }

    public void setCarryingCapacity(Float carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Brand{" +
            "id=" + id +
            ", brand='" + brand + '\'' +
            ", carryingCapacity=" + carryingCapacity +
            ", model='" + model + '\'' +
            '}';
    }
}
