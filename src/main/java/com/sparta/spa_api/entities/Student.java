package com.sparta.spa_api.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "student_name", length = 45)
  private String studentName;

  @Column(name = "has_graduated", nullable = false)
  private boolean hasGraduated;

  @Column(name = "course_id", nullable = false)
  private Integer courseId;

  public Student() {
  }

  public Student(String studentName, boolean hasGraduated, Integer courseId) {
    this.studentName = studentName;
    this.hasGraduated = hasGraduated;
    this.courseId = courseId;
  }

  public Integer getId() {
    return id;
  }

  public String getStudentName() {
    return studentName;
  }

  public boolean isHasGraduated() {
    return hasGraduated;
  }

  public Integer getCourseId() {
    return courseId;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setStudentName(String studentName) {
    this.studentName = studentName;
  }

  public void setHasGraduated(boolean hasGraduated) {
    this.hasGraduated = hasGraduated;
  }

  public void setCourseId(Integer courseId) {
    this.courseId = courseId;
  }
}