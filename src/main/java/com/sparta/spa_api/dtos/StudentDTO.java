package com.sparta.spa_api.dtos;

public class StudentDTO {

    private Integer id;
    private String studentName;
    private Integer courseId;
    private Integer trainerId;
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

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
    }

    public boolean isGraduated() {
        return hasGraduated;
    }

    public void setGraduated(boolean hasGraduated) {
        this.hasGraduated = hasGraduated;
    }
}
