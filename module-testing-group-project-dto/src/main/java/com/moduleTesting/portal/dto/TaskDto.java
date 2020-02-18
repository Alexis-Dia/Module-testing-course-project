package com.moduleTesting.portal.dto;

import java.util.Set;

public class TaskDto {

    private Integer id;

    private Float summaryDistance;

    private Float weight;

    private UserDto driver;

    private CarDto car;

    private TaskStatus taskStatus;

    private String name;

    private Set<ReportDto> reports;

    private Float reward;

    public TaskDto() {
    }

    public TaskDto(Integer id, Float summaryDistance, Float weight, UserDto driver, CarDto car, TaskStatus taskStatus,
                   String name, Set<ReportDto> reports, Float reward) {
        this.id = id;
        this.summaryDistance = summaryDistance;
        this.weight = weight;
        this.driver = driver;
        this.car = car;
        this.taskStatus = taskStatus;
        this.name = name;
        this.reports = reports;
        this.reward = reward;
    }

    public Integer getId() {
        return id;
    }

    public Float getSummaryDistance() {
        return summaryDistance;
    }

    public void setSummaryDistance(Float summaryDistance) {
        this.summaryDistance = summaryDistance;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public UserDto getDriver() {
        return driver;
    }

    public void setDriver(UserDto driver) {
        this.driver = driver;
    }

    public CarDto getCar() {
        return car;
    }

    public void setCar(CarDto car) {
        this.car = car;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ReportDto> getReports() {
        return reports;
    }

    public void setReports(Set<ReportDto> reports) {
        this.reports = reports;
    }

    public Float getReward() {
        return reward;
    }

    public void setReward(Float reward) {
        this.reward = reward;
    }

    @Override
    public String toString() {
        return "TaskDto{" +
            "id=" + id +
            ", summaryDistance=" + summaryDistance +
            ", weight=" + weight +
            ", driver=" + driver +
            ", car=" + car +
            ", taskStatus=" + taskStatus +
            ", name='" + name + '\'' +
            ", reports=" + reports +
            ", reward=" + reward +
            '}';
    }
}
