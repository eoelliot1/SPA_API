package com.sparta.spa_api.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.spa_api.entities.Student;
import jakarta.validation.constraints.NotBlank;


import java.util.List;

public class CourseDTO {
    private Integer id;

    @NotBlank(message = "Course name is required")
    private String courseName;

    @JsonIgnore
    private List<Student> students;

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

    public void setStudents(List<Student> students) {
        this.students = students;
    }


}

