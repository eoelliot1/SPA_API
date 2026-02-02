package com.sparta.spa_api.controller;

import com.sparta.spa_api.entities.Course;
import com.sparta.spa_api.entities.Student;
import com.sparta.spa_api.repository.CourseRepository;
import com.sparta.spa_api.repository.StudentRepository;
import com.sparta.spa_api.repository.TrainersRepository;
import com.sparta.spa_api.repository.SpartanRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class DashboardController {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final TrainersRepository trainersRepository;
    private final SpartanRepository spartanRepository;

    public DashboardController(
            CourseRepository courseRepository,
            StudentRepository studentRepository,
            TrainersRepository trainersRepository,
            SpartanRepository spartanRepository
    ) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.trainersRepository = trainersRepository;
        this.spartanRepository = spartanRepository;
    }

    @GetMapping("/")
    public String dashboard(Model model, Authentication auth) {

        // ALWAYS add date
        model.addAttribute("date", LocalDate.now());

        // ALWAYS add user info
        if (auth != null) {
            model.addAttribute("username", auth.getName());
            String role = auth.getAuthorities().iterator().next().getAuthority();
            model.addAttribute("role", role);

            // Simple role-based data
            if (role.equals("ROLE_ADMIN")) {
                model.addAttribute("courseCount", courseRepository.count());
                model.addAttribute("trainerCount", trainersRepository.count());
                model.addAttribute("studentCount", studentRepository.count());
            } else if (role.equals("ROLE_TRAINER")) {
                List<Course> courses = courseRepository.findAll();
                model.addAttribute("trainerCourses", courses);
            } else if (role.equals("ROLE_STUDENT")) {
                Optional<Student> student = studentRepository.findBySpartan_Email(auth.getName());
                student.ifPresent(s -> model.addAttribute("student", s));
            }
        }

        return "index";
    }
}