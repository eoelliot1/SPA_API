package com.sparta.spa_api.restassured.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private static final Properties properties = new Properties();

    static {
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load config.properties");
        }
    }

    public static String getBaseUri() {
        return properties.getProperty("spa_api.base_uri");
    }
//PATHS
    public static String getCoursesBase() {
        return properties.getProperty("spa_api.courses.base");
    }

    public static String getCourseById() {
        return properties.getProperty("spa_api.courses.by_id");
    }

    public static String getCourseSearch() {
        return properties.getProperty("spa_api.courses.search");
    }

    public static String getTrainersBase() {
        return properties.getProperty("spa_api.trainers.base");
    }

    public static String getTrainerById() {
        return properties.getProperty("spa_api.trainers.by_id");
    }

    public static String getTrainerUpdateName() {
        return properties.getProperty("spa_api.trainers.update_name");
    }

    public static String getTrainerSetCourseName() {
        return properties.getProperty("spa_api.trainers.set_course_name");
    }

    public static String getTrainerCoursesByTrainer() {
        return properties.getProperty("spa_api.trainers.courses_by_trainer");
    }

    public static String getStudentsBase() {
        return properties.getProperty("spa_api.students.base");
    }

    public static String getStudentById() {
        return properties.getProperty("spa_api.students.by_id");
    }

    public static String getStudentGraduated() {
        return properties.getProperty("spa_api.students.graduated");
    }
}
