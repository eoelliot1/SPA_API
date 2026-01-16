package com.sparta.spa_api.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String courseName;

    @OneToMany(
            mappedBy = "course",
            cascade = CascadeType.MERGE,
            orphanRemoval = true
    )
    private List<Student> students = new ArrayList<>();

    @OneToMany(
            mappedBy = "course",
            cascade = CascadeType.MERGE,
            orphanRemoval = true
    )
    private List<Trainers> trainers = new ArrayList<>();

    public Course() {}

    public Course(String courseName) {
        this.courseName = courseName;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Trainers> getTrainers() {
        return trainers;
    }
}
