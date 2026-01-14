package com.sparta.spa_api.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trainers")
public class Trainers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "trainer_name", length = 45)
    private String trainer_name;

    @JoinColumn(name = "course_id", nullable = false)
    private Integer course_id;

    public Trainers(Integer course_id, String trainer_name) {
        this.course_id = course_id;
        this.trainer_name = trainer_name;
    }

    public Trainers() {

    }

    public Integer getCourseID() {
        return course_id;
    }

    public void setCoursesID(Integer course_id) {
        this.course_id = course_id;
    }

    public String getTrainer_name() {
        return trainer_name;
    }

    public void setTrainer_name(String trainer_name) {
        this.trainer_name = trainer_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
