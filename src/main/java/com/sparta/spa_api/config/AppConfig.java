package com.sparta.spa_api.config;

import com.sparta.spa_api.entities.Course;
import com.sparta.spa_api.entities.Spartan;
import com.sparta.spa_api.entities.Student;
import com.sparta.spa_api.entities.Trainers;
import com.sparta.spa_api.repository.CourseRepository;
import com.sparta.spa_api.repository.SpartanRepository;
import com.sparta.spa_api.repository.StudentRepository;
import com.sparta.spa_api.repository.TrainersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .httpBasic(basic -> {})
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .permitAll());
        return http.build();
    }

    @Bean
    @Transactional
    public CommandLineRunner loadData(
            CourseRepository courseRepository,
            TrainersRepository trainersRepository,
            StudentRepository studentRepository,
            SpartanRepository spartanRepo,
            PasswordEncoder encoder
    ) {
        return args -> {

            if (spartanRepo.count() > 0) {
                System.out.println("Spartans already seeded");
                return;
            }

            // ===== AUTH USERS (LOGIN ACCOUNTS) =====
            Spartan admin = new Spartan(
                    "admin@spartaglobal.com",
                    "admin",
                    encoder.encode("adminpass"),
                    "ADMIN"
            );

            Spartan trainerUser = new Spartan(
                    "sarah@spartaglobal.com",
                    "sarah",
                    encoder.encode("sarahpass"),
                    "TRAINER"
            );

            Spartan studentUser = new Spartan(
                    "alice@spartaglobal.com",
                    "alice",
                    encoder.encode("alicepass"),
                    "STUDENT"
            );

            spartanRepo.saveAll(List.of(admin, trainerUser, studentUser));
            System.out.println("Seeded Spartans for login");

            // ===== COURSES =====
            Course course1 = new Course("Software Testing");
            Course course2 = new Course("Data");
            Course course3 = new Course("Java Development");
            Course course4 = new Course("DevOps");

            course1.setStartDate(LocalDate.of(2024, 9, 1));
            course1.setEndDate(LocalDate.of(2026, 1, 1));

            course2.setStartDate(LocalDate.of(2025, 5, 7));
            course2.setEndDate(LocalDate.of(2026, 6, 1));

            course3.setStartDate(LocalDate.of(2024, 11, 1));
            course3.setEndDate(LocalDate.of(2025, 8, 1));

            course4.setStartDate(LocalDate.of(2025, 2, 1));
            course4.setEndDate(LocalDate.of(2025, 12, 1));

            courseRepository.saveAll(List.of(course1, course2, course3, course4));

            // ===== STUDENTS =====
            Student student1 = new Student();
            student1.setStudentName("Alice Johnson");
            student1.setHasGraduated(false);
            student1.setCourse(course1);
            student1.setSpartan(studentUser);

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

            Student student5 = new Student();
            student5.setStudentName("Ethan Wright");
            student5.setHasGraduated(false);
            student5.setCourse(course3);

            Student student6 = new Student();
            student6.setStudentName("Fiona Green");
            student6.setHasGraduated(false);
            student6.setCourse(course3);

            Student student7 = new Student();
            student7.setStudentName("George King");
            student7.setHasGraduated(true);
            student7.setCourse(course3);

            Student student8 = new Student();
            student8.setStudentName("Hannah Lee");
            student8.setHasGraduated(false);
            student8.setCourse(course4);

            Student student9 = new Student();
            student9.setStudentName("Ian Moore");
            student9.setHasGraduated(false);
            student9.setCourse(course4);

            Student student10 = new Student();
            student10.setStudentName("Julia Adams");
            student10.setHasGraduated(true);
            student10.setCourse(course4);

            Student student11 = new Student();
            student11.setStudentName("Kevin Turner");
            student11.setHasGraduated(false);
            student11.setCourse(course1);

            Student student12 = new Student();
            student12.setStudentName("Laura Scott");
            student12.setHasGraduated(true);
            student12.setCourse(course2);

            Student student13 = new Student();
            student13.setStudentName("Mark Phillips");
            student13.setHasGraduated(false);
            student13.setCourse(course3);

            Student student14 = new Student();
            student14.setStudentName("Nina Carter");
            student14.setHasGraduated(false);
            student14.setCourse(course4);

            studentRepository.saveAll(List.of(
                    student1, student2, student3, student4,
                    student5, student6, student7, student8,
                    student9, student10, student11, student12,
                    student13, student14
            ));

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

            Trainers trainer5 = new Trainers();
            trainer5.setTrainerName("Daniel Architect");
            trainer5.setCourse(course3);

            Trainers trainer6 = new Trainers();
            trainer6.setTrainerName("Olivia Dev");
            trainer6.setCourse(course3);

            Trainers trainer7 = new Trainers();
            trainer7.setTrainerName("Chris Ops");
            trainer7.setCourse(course4);

            trainersRepository.saveAll(List.of(
                    trainer1, trainer2, trainer3, trainer4,
                    trainer5, trainer6, trainer7
            ));

            System.out.println("Seed data added");
        };
    }
}
