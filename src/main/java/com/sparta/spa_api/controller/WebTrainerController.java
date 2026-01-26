package com.sparta.spa_api.controller;

import com.sparta.spa_api.dtos.CourseDTO;
import com.sparta.spa_api.dtos.StudentMapper;
import com.sparta.spa_api.entities.Student;
import com.sparta.spa_api.services.CourseService;
import com.sparta.spa_api.services.TrainerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;


@Controller
@RequestMapping("/trainers")
public class WebTrainerController {
    private final TrainerService trainerService;
    private final CourseService courseService;
    private final StudentMapper studentMapper;
    public WebTrainerController(TrainerService trainerService, CourseService courseService, StudentMapper studentMapper) {
        this.trainerService = trainerService;
        this.courseService = courseService;
        this.studentMapper = studentMapper;
    }

    // Spring creates the Model
    // Controller assigns values to attributes within that model
    // Thymeleaf reads these
    // Model is discarded after the response
    @GetMapping("/")
    public String trainersHomePage(Model model){
        List<Student> students = courseService.getStudentsByCourseId(1);
        List<CourseDTO> courses = courseService.getAllCourses();
        model.addAttribute("students", students);
        model.addAttribute("courses", courses);
        return "trainers"; // look for resources/templates/trainers.html
    }

    @GetMapping("/search")
    public String longCourses(@RequestParam("query") long query, Model model) {
        List<Student> students = courseService.getStudentsByCourseId(1);
        model.addAttribute("students", students);
        model.addAttribute("courses", courseService.getCoursesLongerThan(query));
        return "trainers";
    }

}
