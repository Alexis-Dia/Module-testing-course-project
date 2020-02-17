package com.moduleTesting.portal.dto;

import java.util.Date;

public class ReportDto {

    private Integer id;

    private Date departure;

    private Float weight;

    private Float distance;

    private Date arrival;

    public ReportDto() {
    }

    public ReportDto(Integer id, Date departure, Float weight, Float distance, Date arrival) {
        this.id = id;
        this.departure = departure;
        this.weight = weight;
        this.distance = distance;
        this.arrival = arrival;
    }

    public Integer getId() {
        return id;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    @Override
    public String toString() {
        return "ReportDto{" +
            "id=" + id +
            ", departure=" + departure +
            ", weight=" + weight +
            ", distance=" + distance +
            ", arrival=" + arrival +
            '}';
    }
}
