package com.moduleTesting.portal.dto;

public class BrandDto {

    private Integer id;

    private String brand;

    private Float carryingCapacity;

    private String model;

    public BrandDto() {
    }

    public BrandDto(Integer id, String brand, Float carryingCapacity, String model) {
        this.id = id;
        this.brand = brand;
        this.carryingCapacity = carryingCapacity;
        this.model = model;
    }

    public BrandDto(String brand, Float carryingCapacity, String model) {
        this.brand = brand;
        this.carryingCapacity = carryingCapacity;
        this.model = model;
    }

    public Integer getId() {
        return id;
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
        return "BrandDto{" +
            "id=" + id +
            ", brand='" + brand + '\'' +
            ", carryingCapacity=" + carryingCapacity +
            ", model='" + model + '\'' +
            '}';
    }
}
