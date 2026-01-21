package com.sparta.spa_api.dtos;

import com.sparta.spa_api.entities.Student;

public class StudentDTO {
    private Integer id;
    private String studentName;
    private Integer courseId;
    private boolean hasGraduated;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getCourseId() {  // fixed name
        return courseId;
    }


    public void setCourseId(Integer courseId) {  // fixed typo
        this.courseId = courseId;
    }

    public boolean isGraduated() {  // standard boolean getter
        return hasGraduated;
    }

    public void setGraduated(boolean hasGraduated) {
        this.hasGraduated = hasGraduated;
    }
}
