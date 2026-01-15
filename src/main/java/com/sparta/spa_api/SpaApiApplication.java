package com.sparta.spa_api;

import com.sparta.spa_api.entities.Course;
import com.sparta.spa_api.entities.Student;
import com.sparta.spa_api.entities.Trainers;
import com.sparta.spa_api.repository.CourseRepository;
import com.sparta.spa_api.repository.StudentRepository;
import com.sparta.spa_api.repository.TrainersRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpaApiApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(SpaApiApplication.class, args);

        CourseRepository courseRepository = context.getBean(CourseRepository.class);
        TrainersRepository trainersRepository = context.getBean(TrainersRepository.class);
        StudentRepository studentRepository = context.getBean(StudentRepository.class);

        for(Course course : courseRepository.findAll()) {
            System.out.println(course);
        }

        for(Student student : studentRepository.findAll()) {
            System.out.println(student);
        }

        for(Trainers trainer : trainersRepository.findAll()) {
            System.out.println(trainer);
        }
    }

}
