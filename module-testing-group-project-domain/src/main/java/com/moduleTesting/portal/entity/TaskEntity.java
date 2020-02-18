package com.moduleTesting.portal.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "task")
public class TaskEntity {

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
    private UserEntity driver;

    @JoinColumn(name="car_id")
    @ManyToOne
    private CarEntity car;

    @JoinColumn(name="status_id")
    @ManyToOne
    private TaskStatusEntity status;

    @Column(name="reward")
    private Float reward;

    @OneToMany
    private Set<ReportEntity> reports;

    public TaskEntity() {
    }

    public TaskEntity(String name, Float summaryDistance, Float weight, UserEntity driver, CarEntity car, TaskStatusEntity status, Float reward) {
        this.name = name;
        this.summaryDistance = summaryDistance;
        this.weight = weight;
        this.driver = driver;
        this.car = car;
        this.status = status;
        this.reward = reward;
    }

    public TaskEntity(String name, Float summaryDistance, Float weight, TaskStatusEntity status, Float reward) {
        this.name = name;
        this.summaryDistance = summaryDistance;
        this.weight = weight;
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

    public UserEntity getDriver() {
        return driver;
    }

    public void setDriver(UserEntity driver) {
        this.driver = driver;
    }

    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }

    public TaskStatusEntity getStatus() {
        return status;
    }

    public void setStatus(TaskStatusEntity status) {
        this.status = status;
    }

    public Float getReward() {
        return reward;
    }

    public void setReward(Float reward) {
        this.reward = reward;
    }

    public Set<ReportEntity> getReports() {
        if (reports != null) {
            return reports;
        }
        return new HashSet<ReportEntity>();
    }

    public void setReports(Set<ReportEntity> reports) {
        this.reports = reports;
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
