package com.sparta.spa_api.config;

import com.sparta.spa_api.entities.Course;
import com.sparta.spa_api.entities.Student;
import com.sparta.spa_api.entities.Trainers;
import com.sparta.spa_api.repository.CourseRepository;
import com.sparta.spa_api.repository.StudentRepository;
import com.sparta.spa_api.repository.TrainersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

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

            // ===== STUDENTS =====
            Student student1 = new Student();
            student1.setStudentName("Alice Johnson");
            student1.setHasGraduated(false);
            student1.setCourse(course1);

            Student student2 = new Student();
            student2.setStudentName("Bob Smith");
            student2.setHasGraduated(true);
            student2.setCourse(course1);

            Student student3 = new Student();
            student3.setStudentName("Charlie Brown");
            student3.setHasGraduated(false);
            student3.setCourse(course2);

            Student student4 = new Student();
            student4.setStudentName("Daisy Miller");
            student4.setHasGraduated(true);
            student4.setCourse(course2);

            studentRepository.save(student1);
            studentRepository.save(student2);
            studentRepository.save(student3);
            studentRepository.save(student4);

            // ===== TRAINERS =====
            Trainers trainer1 = new Trainers();
            trainer1.setTrainerName("John Trainer");
            trainer1.setCourse(course1);

            Trainers trainer2 = new Trainers();
            trainer2.setTrainerName("Sarah Coach");
            trainer2.setCourse(course1);

            Trainers trainer3 = new Trainers();
            trainer3.setTrainerName("Mike Mentor");
            trainer3.setCourse(course2);

            Trainers trainer4 = new Trainers();
            trainer4.setTrainerName("Emma Instructor");
            trainer4.setCourse(course2);

            trainersRepository.save(trainer1);
            trainersRepository.save(trainer2);
            trainersRepository.save(trainer3);
            trainersRepository.save(trainer4);

            System.out.println("Seed data added");
        };
    }
}
