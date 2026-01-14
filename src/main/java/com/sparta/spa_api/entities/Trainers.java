package com.sparta.spa_api.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "trainers")
public class Trainers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "trainer_name", length = 45)
    private String trainer_name;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    public Trainers() {}

    public Trainers(String trainer_name, Course course) {
        this.trainer_name = trainer_name;
        this.course = course;
    }

    public Integer getId() {
        return id;
    }

    public String getTrainer_name() {
        return trainer_name;
    }

    public void setTrainer_name(String trainer_name) {
        this.trainer_name = trainer_name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
