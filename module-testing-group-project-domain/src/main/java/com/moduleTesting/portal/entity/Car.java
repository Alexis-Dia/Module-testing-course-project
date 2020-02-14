package com.moduleTesting.portal.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "brand_id")
    private Integer brandId;

    @Column(name = "year")
    private Date year;

    @Column(name = "number")
    private String number;

    @Column(name = "date_of_receipt")
    private Date dateOfReceipt;

    @JoinColumn(name = "status_id")
    @ManyToOne
    private CarStatus carStatus;

    public Car() {
    }

    public Car(Integer brandId, Date year, String number, Date dateOfReceipt, CarStatus carStatus) {
        this.brandId = brandId;
        this.year = year;
        this.number = number;
        this.dateOfReceipt = dateOfReceipt;
        this.carStatus = carStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
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
        return "Car{" +
            "id=" + id +
            ", brandId=" + brandId +
            ", year=" + year +
            ", number='" + number + '\'' +
            ", yearOfReceipt=" + dateOfReceipt +
            ", carStatus=" + carStatus +
            '}';
    }
}
