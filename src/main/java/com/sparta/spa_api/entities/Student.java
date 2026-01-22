package com.sparta.spa_api.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "student_id")
  private Integer id;

  @Column(name = "student_name", length = 45, nullable = false)
  private String studentName;

  @Column(name = "has_graduated", nullable = false)
  private boolean hasGraduated;

  @ManyToOne
  @JoinColumn(name = "course_id", nullable = false)
  private Course course;

  @ManyToOne
  @JoinColumn(name = "trainer_id", nullable = true)
  private Trainers trainer;

  public Student() {}

  public Student(String studentName, boolean hasGraduated) {
    this.studentName = studentName;
    this.hasGraduated = hasGraduated;
  }

  // Getters & setters
  public Integer getId() {
    return id;
  }

  public String getStudentName() {
    return studentName;
  }

  public void setStudentName(String studentName) {
    this.studentName = studentName;
  }

  public boolean isHasGraduated() {
    return hasGraduated;
  }

  public void setHasGraduated(boolean hasGraduated) {
    this.hasGraduated = hasGraduated;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public Trainers getTrainer() {
    return trainer;
  }

  public void setTrainer(Trainers trainer) {
    this.trainer = trainer;
  }
}
