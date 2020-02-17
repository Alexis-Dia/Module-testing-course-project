package com.moduleTesting.portal.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "car")
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "brand_id")
    @ManyToOne
    private BrandEntity brandEntity;

    @Column(name = "year")
    private Date year;

    @Column(name = "number")
    private String number;

    @Column(name = "date_of_receipt")
    private Date dateOfReceipt;

    @JoinColumn(name = "status_id")
    @ManyToOne
    private CarStatusEntity carStatusEntity;

    public CarEntity() {
    }

    public CarEntity(BrandEntity brandEntity, Date year, String number, Date dateOfReceipt, CarStatusEntity carStatusEntity) {
        this.brandEntity = brandEntity;
        this.year = year;
        this.number = number;
        this.dateOfReceipt = dateOfReceipt;
        this.carStatusEntity = carStatusEntity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BrandEntity getBrandEntity() {
        return brandEntity;
    }

    public void setBrandEntity(BrandEntity brandEntity) {
        this.brandEntity = brandEntity;
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

    public CarStatusEntity getCarStatusEntity() {
        return carStatusEntity;
    }

    public void setCarStatusEntity(CarStatusEntity carStatusEntity) {
        this.carStatusEntity = carStatusEntity;
    }

    @Override
    public String toString() {
        return "Car{" +
            "id=" + id +
            ", brandEntity=" + brandEntity +
            ", year=" + year +
            ", number='" + number + '\'' +
            ", yearOfReceipt=" + dateOfReceipt +
            ", carStatus=" + carStatusEntity +
            '}';
    }
}
