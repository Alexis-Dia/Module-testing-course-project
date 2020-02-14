package com.moduleTesting.portal.entity;

import javax.persistence.*;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="summary_distance")
    private Float summaryDistance;

    @Column(name="weight")
    private Float weight;

    @JoinColumn(name="driver_id")
    @ManyToOne
    private User driver_id;

    @JoinColumn(name="car_id")
    @ManyToOne
    private Car carId;

    @JoinColumn(name="status_id")
    @ManyToOne
    private TaskStatus statusId;

    @Column(name="reward")
    private Float reward;

    public Task() {
    }

    public Task(String name, Float summaryDistance, Float weight, User driver_id, Car carId, TaskStatus statusId, Float reward) {
        this.name = name;
        this.summaryDistance = summaryDistance;
        this.weight = weight;
        this.driver_id = driver_id;
        this.carId = carId;
        this.statusId = statusId;
        this.reward = reward;
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

    public User getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(User driver_id) {
        this.driver_id = driver_id;
    }

    public Car getCarId() {
        return carId;
    }

    public void setCarId(Car carId) {
        this.carId = carId;
    }

    public TaskStatus getStatusId() {
        return statusId;
    }

    public void setStatusId(TaskStatus statusId) {
        this.statusId = statusId;
    }

    public Float getReward() {
        return reward;
    }

    public void setReward(Float reward) {
        this.reward = reward;
    }

    @Override
    public String toString() {
        return "Task{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", summaryDistance=" + summaryDistance +
            ", weight=" + weight +
            ", driver_id=" + driver_id +
            ", carId=" + carId +
            ", statusId=" + statusId +
            ", reward=" + reward +
            '}';
    }
}
