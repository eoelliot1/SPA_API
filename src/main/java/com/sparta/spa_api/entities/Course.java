package com.sparta.spa_api.entities;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id", nullable = false)
    private Integer course_id;

    @Column(name = "course_name", length = 45)
    private String course_name;

    @OneToMany(
            mappedBy = "course",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )

    private List<Student> students = new ArrayList<>();

    @OneToMany(
            mappedBy = "course",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Trainers> trainers = new ArrayList<>();

    public Course(String course_name, List<Student> students, List<Trainers> trainers, Integer course_id) {
        this.course_name = course_name;
        this.students = students;
        this.trainers = trainers;
        this.course_id = course_id;
    }

    public Course() {

    }


    public Integer getId() {
        return course_id;
    }

    public void setId(Integer course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Trainers> getTrainers() {
        return trainers;
    }

    public void setTrainers(List<Trainers> trainers) {
        this.trainers = trainers;
    }
}
