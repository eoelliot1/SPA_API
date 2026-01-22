package com.sparta.spa_api.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trainers")
public class Trainers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainer_id")
    private Integer id;

    @Column(name = "trainer_name", length = 45, nullable = false)
    private String trainerName;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @OneToMany(
            mappedBy = "trainer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Student> students = new ArrayList<>();

    public Trainers() {}

    public Trainers(String trainerName) {
        this.trainerName = trainerName;
    }

    // Helper method
    public void addStudent(Student student) {
        students.add(student);
        student.setTrainer(this);
    }

    // Getters & setters
    public Integer getId() {
        return id;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Student> getStudents() {
        return students;
    }
}
