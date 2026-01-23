package com.sparta.spa_api.controller;

import com.sparta.spa_api.dtos.CourseDTO;
import com.sparta.spa_api.dtos.StudentDTO;
import com.sparta.spa_api.dtos.TrainersDTO;
import com.sparta.spa_api.entities.Course;
import com.sparta.spa_api.entities.Student;
import com.sparta.spa_api.services.CourseService;
import com.sparta.spa_api.services.TrainerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/trainers")
public class WebTrainerController {
    private final TrainerService trainerService;
    private final CourseService courseService;

    public WebTrainerController(TrainerService trainerService, CourseService courseService) {
        this.trainerService = trainerService;
        this.courseService = courseService;
    }



    // Spring creates the Model
    // Controller assigns values to attributes within that model
    // Thymeleaf reads these
    // Model is discarded after the response
    @GetMapping
    public String trainersHomePage(Model model){
        List<Student> students = courseService.getStudentsByCourseId(1);
        model.addAttribute("students", students);
        return "trainers"; // look for resources/templates/trainers.html
    }
}
