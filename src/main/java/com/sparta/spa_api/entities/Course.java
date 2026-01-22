package com.sparta.spa_api.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Integer id;

    @Column(name = "course_name", length = 45, nullable = false)
    private String courseName;

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

    public Course(String courseName) {
        this.courseName = courseName;
    }

    // Helper methods
    public void addStudent(Student student) {
        students.add(student);
        student.setCourse(this);
    }

    public void addTrainer(Trainers trainer) {
        trainers.add(trainer);
        trainer.setCourse(this);
    }

    // Getters & setters
    public Integer getId() {
        return id;
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
