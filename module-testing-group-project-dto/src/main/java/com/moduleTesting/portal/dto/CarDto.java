package com.moduleTesting.portal.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CarDto {

    private Integer id;

    private BrandDto brand;

    private Date year;

    private String number;

    private Date dateOfReceipt;

    private CarStatus carStatus;

    public CarDto() {
    }

    @JsonCreator
    public CarDto(Integer id, BrandDto brand, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date year,
                  String number, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date dateOfReceipt, CarStatus carStatus) {
        this.id = id;
        this.brand = brand;
        this.year = year;
        this.number = number;
        this.dateOfReceipt = dateOfReceipt;
        this.carStatus = carStatus;
    }

    public CarDto(BrandDto brand, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date year,
                  String number, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date dateOfReceipt, CarStatus carStatus) {
        this.brand = brand;
        this.year = year;
        this.number = number;
        this.dateOfReceipt = dateOfReceipt;
        this.carStatus = carStatus;
    }

    public Integer getId() {
        return id;
    }

    public BrandDto getBrand() {
        return brand;
    }

    public void setBrand(BrandDto brand) {
        this.brand = brand;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDateOfReceipt() {
        return dateOfReceipt;
    }

    public void setDateOfReceipt(Date dateOfReceipt) {
        this.dateOfReceipt = dateOfReceipt;
    }

    public CarStatus getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(CarStatus carStatus) {
        this.carStatus = carStatus;
    }

    @Override
    public String toString() {
        return "CarDto{" +
            "id=" + id +
            ", brand=" + brand +
            ", year=" + year +
            ", number='" + number + '\'' +
            ", dateOfReceipt=" + dateOfReceipt +
            ", carStatus=" + carStatus +
            '}';
    }
}
