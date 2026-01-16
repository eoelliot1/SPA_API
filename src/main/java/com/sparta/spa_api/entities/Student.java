package com.sparta.spa_api.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "student_name", length = 45)
  private String studentName;

  @Column(name = "has_graduated", nullable = false)
  private boolean hasGraduated;

  @ManyToOne
  @JoinColumn(name = "course_id", nullable = false)
  private Course course;

  public Student() {}

  public Student(String studentName, boolean hasGraduated, Course course) {
    this.studentName = studentName;
    this.hasGraduated = hasGraduated;
    this.course = course;
  }

  public Integer getId() {
    return id;
  }

  public String getStudent_name() {
    return studentName;
  }

  public void setStudent_name(String student_name) {
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
}
