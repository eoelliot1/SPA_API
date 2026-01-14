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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class AppConfig {

    @Bean
    @Qualifier
    @Transactional
    public CommandLineRunner loadData(CourseRepository courseRepository, TrainersRepository trainersRepository, StudentRepository studentRepository) {
        return args -> {
            System.out.println("DataLoader running...");
            if (studentRepository.count() == 0) {
                // ===== STUDENTS =====
                Student student1 = new Student("Alice Johnson", 1, false, 1);
                Student student2 = new Student("Bob Smith", 2, true, 1);
                Student student3 = new Student("Charlie Brown", 3, false, 2);
                Student student4 = new Student("Daisy Miller", 4, true, 2);

                List<Student> course1Students = new ArrayList<>();
                course1Students.add(student1);
                course1Students.add(student2);

                List<Student> course2Students = new ArrayList<>();
                course2Students.add(student3);
                course2Students.add(student4);

                studentRepository.save(student1);
                studentRepository.save(student2);
                studentRepository.save(student3);
                studentRepository.save(student4);


                // ===== TRAINERS =====
                Trainers trainer1 = new Trainers(1, "John Trainer");
                Trainers trainer2 = new Trainers(1, "Sarah Coach");

                Trainers trainer3 = new Trainers(2, "Mike Mentor");
                Trainers trainer4 = new Trainers(2, "Emma Instructor");

                List<Trainers> course1Trainers = new ArrayList<>();
                course1Trainers.add(trainer1);
                course1Trainers.add(trainer2);

                List<Trainers> course2Trainers = new ArrayList<>();
                course2Trainers.add(trainer3);
                course2Trainers.add(trainer4);

                trainersRepository.save(trainer1);
                trainersRepository.save(trainer2);
                trainersRepository.save(trainer3);
                trainersRepository.save(trainer4);


                // ===== COURSES =====
                Course course1 = new Course("Software Testing", course1Students, course1Trainers, 1);
                Course course2 = new Course("Data", course2Students, course2Trainers, 2);

                courseRepository.save(course1);
                courseRepository.save(course2);
                System.out.println("Seed data added");
            } else {
                System.out.println("Seed skipped");
            }
        };
    }
}
