package com.moduleTesting.portal.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="task_id")
    private Integer taskId;

    @Column(name="departure")
    private Date departure;

    @Column(name="weight")
    private Float weight;

    @Column(name="distance")
    private Float distance;

    @Column(name="arrival")
    private Date arrival;

    public Report() {
    }

    public Report(Integer taskId, Date departure, Float weight, Float distance, Date arrival) {
        this.taskId = taskId;
        this.departure = departure;
        this.weight = weight;
        this.distance = distance;
        this.arrival = arrival;
    }

    public Integer getId() {
        return id;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public Date getDeparture() {
        return departure;
    }

    public Float getWeight() {
        return weight;
    }

    public Float getDistance() {
        return distance;
    }

    public Date getArrival() {
        return arrival;
    }

    @Override
    public String toString() {
        return "Report{" +
            "id=" + id +
            ", taskId=" + taskId +
            ", departure=" + departure +
            ", weight=" + weight +
            ", distance=" + distance +
            ", arrival=" + arrival +
            '}';
    }
}
