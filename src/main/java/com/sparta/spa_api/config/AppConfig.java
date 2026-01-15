package com.sparta.spa_api.config;

import com.sparta.spa_api.entities.Course;
import com.sparta.spa_api.entities.Student;
import com.sparta.spa_api.entities.Trainers;
import com.sparta.spa_api.repository.CourseRepository;
import com.sparta.spa_api.repository.StudentRepository;
import com.sparta.spa_api.repository.TrainersRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    @Transactional
    public CommandLineRunner loadData(
            CourseRepository courseRepository,
            TrainersRepository trainersRepository,
            StudentRepository studentRepository
    ) {
        return args -> {
            System.out.println("DataLoader running...");

            if (studentRepository.count() > 0) {
                System.out.println("Seed skipped");
                return;
            }

            // ===== COURSES =====
            Course course1 = new Course("Software Testing");
            Course course2 = new Course("Data");

            courseRepository.save(course1);
            courseRepository.save(course2);

            // ===== STUDENTS ======
            Student student1 = new Student("Alice Johnson", false, course1);
            Student student2 = new Student("Bob Smith", true, course1);

            Student student3 = new Student("Charlie Brown", false, course2);
            Student student4 = new Student("Daisy Miller", true, course2);

            studentRepository.save(student1);
            studentRepository.save(student2);
            studentRepository.save(student3);
            studentRepository.save(student4);

            // ===== TRAINERS =====
            Trainers trainer1 = new Trainers("John Trainer", course1);
            Trainers trainer2 = new Trainers("Sarah Coach", course1);

            Trainers trainer3 = new Trainers("Mike Mentor", course2);
            Trainers trainer4 = new Trainers("Emma Instructor", course2);

            trainersRepository.save(trainer1);
            trainersRepository.save(trainer2);
            trainersRepository.save(trainer3);
            trainersRepository.save(trainer4);

            System.out.println("Seed data added");
        };
    }
}
