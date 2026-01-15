package com.sparta.spa_api.dtos;

import com.sparta.spa_api.entities.Course;

public class TrainersDTO {

    private Integer id;
    private String trainer_name;
    private Course course_id;

    public Course getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Course course_id) {
        this.course_id = course_id;
    }

    public String getTrainer_name() {
        return trainer_name;
    }

    public void setTrainer_name(String trainer_name) {
        this.trainer_name = trainer_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



}
