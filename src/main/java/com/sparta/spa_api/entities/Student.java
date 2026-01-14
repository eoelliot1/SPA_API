package com.sparta.spa_api.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {

  @Column(name = "hasGraduated", nullable = false)
  private boolean hasGraduated;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "student_name", length = 45)
  private String student_name;

  @JoinColumn(name = "course_id", nullable = false)
  private Integer course_id;

  public Student(String student_name, Integer id, boolean hasGraduated, Integer course_id) {
    this.student_name = student_name;
    this.id = id;
    this.hasGraduated = hasGraduated;
    this.course_id = course_id;
  }

  public Student() {

  }

  public Integer getCoursesId() {
    return this.course_id;
  }

  public void setCoursesId(Integer course_id) {
    this.course_id = course_id;
  }

  public String getStudent_name() {
    return student_name;
  }

  public void setStudent_name(String student_name) {
    this.student_name = student_name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
  }
