package com.moduleTesting.portal.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @JsonCreator
    public ReportDto(@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") @JsonProperty("departure") Date departure, @JsonProperty("weight") Float weight,
                     @JsonProperty("distance") Float distance, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") @JsonProperty("arrival") Date arrival) {
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
