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
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .permitAll())
                .csrf(csrf -> csrf.disable());
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
                    encoder.encode("adminpass"),
                    "ADMIN"
            );

            Spartan trainerUser = new Spartan(
                    "4",
                    encoder.encode("trainerpass"),
                    "TRAINER"
            );

            Spartan studentUser = new Spartan(
                    "student@spartaglobal.com",
                    encoder.encode("studentpass"),
                    "STUDENT"
            );

            Spartan cathy = new Spartan(
                    "cathy@spartaglobal.com",
                    encoder.encode("cathypass"),
                    "TRAINER"
            );

            spartanRepo.saveAll(
                    List.of(admin, trainerUser, studentUser, cathy)
            );

            System.out.println("Seeded Spartans for login");

            // Courses details
            Course softwareTesting = new Course("Software Testing");
            Course data = new Course("Data");
            Course webDevelopment = new Course("Web Development");
            Course cyberSecurity = new Course("Cyber Security");
            Course mobileAppDev = new Course("Mobile App Development");

            LocalDate startDate = LocalDate.of(2024, 9, 1);
            LocalDate startDate2 = LocalDate.of(2025, 5, 7);
            LocalDate startDate3 = LocalDate.of(2024, 11, 15);
            LocalDate startDate4 = LocalDate.of(2025, 2, 10);
            LocalDate startDate5 = LocalDate.of(2025, 8, 20);

            LocalDate endDate = LocalDate.of(2026, 1, 1);
            LocalDate endDate2 = LocalDate.of(2026, 4, 1);
            LocalDate endDate3 = LocalDate.of(2025, 11, 15);
            LocalDate endDate4 = LocalDate.of(2025, 8, 10);
            LocalDate endDate5 = LocalDate.of(2026, 6, 20);

            softwareTesting.setStartDate(startDate);
            softwareTesting.setEndDate(endDate);

            data.setStartDate(startDate2);
            data.setEndDate(endDate2);

            webDevelopment.setStartDate(startDate3);
            webDevelopment.setEndDate(endDate3);

            cyberSecurity.setStartDate(startDate4);
            cyberSecurity.setEndDate(endDate4);

            mobileAppDev.setStartDate(startDate5);
            mobileAppDev.setEndDate(endDate5);


            courseRepository.save(softwareTesting);
            courseRepository.save(data);
            courseRepository.save(webDevelopment);
            courseRepository.save(cyberSecurity);
            courseRepository.save(mobileAppDev);

            // Trainers' details
            Trainers john = new Trainers();
            john.setTrainerName("John Trainer");
            john.setCourse(softwareTesting);

            Trainers sarah = new Trainers();
            sarah.setTrainerName("Sarah Coach");
            sarah.setCourse(data);

            Trainers mike = new Trainers();
            mike.setTrainerName("Mike Mentor");
            mike.setCourse(softwareTesting);

            Trainers emma = new Trainers();
            emma.setTrainerName("Emma Instructor");
            emma.setCourse(data);

            Trainers david = new Trainers();
            david.setTrainerName("David Guide");
            david.setCourse(webDevelopment);

            Trainers lisa = new Trainers();
            lisa.setTrainerName("Lisa Tutor");
            lisa.setCourse(cyberSecurity);

            Trainers robert = new Trainers();
            robert.setTrainerName("Robert Advisor");
            robert.setCourse(mobileAppDev);



            trainersRepository.save(john);
            trainersRepository.save(sarah);
            trainersRepository.save(mike);
            trainersRepository.save(emma);
            trainersRepository.save(david);
            trainersRepository.save(lisa);
            trainersRepository.save(robert);


            // Students' details
            Student alice = new Student();
            alice.setStudentName("Alice Johnson");
            alice.setHasGraduated(false);
            alice.setCourse(softwareTesting);
            alice.setTrainer(trainersRepository.findById(1).get());

            Student bob = new Student();
            bob.setStudentName("Bob Smith");
            bob.setHasGraduated(true);
            bob.setCourse(softwareTesting);
            bob.setTrainer(trainersRepository.findById(2).get());


            Student charlie = new Student();
            charlie.setStudentName("Charlie Brown");
            charlie.setHasGraduated(false);
            charlie.setCourse(webDevelopment);
            charlie.setTrainer(trainersRepository.findById(3).get());


            Student daisy = new Student();
            daisy.setStudentName("Daisy Miller");
            daisy.setHasGraduated(true);
            daisy.setCourse(data);
            daisy.setTrainer(trainersRepository.findById(3).get());

            Student ethan = new Student();
            ethan.setStudentName("Ethan Wilson");
            ethan.setHasGraduated(false);
            ethan.setCourse(webDevelopment);
            ethan.setTrainer(trainersRepository.findById(4).get());

            Student sophia = new Student();
            sophia.setStudentName("Sophia Garcia");
            sophia.setHasGraduated(true);
            sophia.setCourse(cyberSecurity);
            sophia.setTrainer(trainersRepository.findById(2).get());

            Student mason = new Student();
            mason.setStudentName("Mason Taylor");
            mason.setHasGraduated(false);
            mason.setCourse(mobileAppDev);
            mason.setTrainer(trainersRepository.findById(1).get());

            Student ava = new Student();
            ava.setStudentName("Ava Martinez");
            ava.setHasGraduated(true);
            ava.setCourse(webDevelopment);
            ava.setTrainer(trainersRepository.findById(2).get());

            Student noah = new Student();
            noah.setStudentName("Noah Anderson");
            noah.setHasGraduated(false);
            noah.setCourse(cyberSecurity);


            studentRepository.save(alice);
            studentRepository.save(bob);
            studentRepository.save(charlie);
            studentRepository.save(daisy);
            studentRepository.save(ethan);
            studentRepository.save(sophia);
            studentRepository.save(mason);
            studentRepository.save(ava);
            studentRepository.save(noah);


            System.out.println("Seed data added");
        };
    }
}
