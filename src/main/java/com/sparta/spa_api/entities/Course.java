package com.sparta.spa_api.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Integer id;

    @Column(name = "course_name", length = 45, nullable = false)
    private String courseName;

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate; // null = still enrolled

    // getters & setters

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

    public long getDurationInDays() {
        LocalDate end = (endDate != null) ? endDate : LocalDate.now();
        return ChronoUnit.DAYS.between(startDate, end);
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
