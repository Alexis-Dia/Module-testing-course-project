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
    private User driver;

    @JoinColumn(name="car_id")
    @ManyToOne
    private Car car;

    @JoinColumn(name="status_id")
    @ManyToOne
    private TaskStatus status;

    @Column(name="reward")
    private Float reward;

    public Task() {
    }

    public Task(String name, Float summaryDistance, Float weight, User driver, Car car, TaskStatus status, Float reward) {
        this.name = name;
        this.summaryDistance = summaryDistance;
        this.weight = weight;
        this.driver = driver;
        this.car = car;
        this.status = status;
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

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
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
            ", driver=" + driver +
            ", car=" + car +
            ", status=" + status +
            ", reward=" + reward +
            '}';
    }
}
