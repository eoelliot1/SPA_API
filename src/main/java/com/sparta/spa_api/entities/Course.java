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
    private Integer id;

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

    public Course() {}

    public Course(String course_name) {
        this.course_name = course_name;
    }

    public Course(String softwareTesting, List<Student> course1Students, List<Trainers> course1Trainers, int i) {
    }

    public Integer getId() {
        return id;
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

    public List<Trainers> getTrainers() {
        return trainers;
    }
}
