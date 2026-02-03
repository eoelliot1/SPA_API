package com.sparta.spa_api.controller;

import com.sparta.spa_api.dtos.CourseDTO;
import com.sparta.spa_api.dtos.StudentMapper;
import com.sparta.spa_api.dtos.TrainersDTO;
import com.sparta.spa_api.entities.Course;
import com.sparta.spa_api.entities.Student;
import com.sparta.spa_api.entities.Trainers;
import com.sparta.spa_api.services.CourseService;
import com.sparta.spa_api.services.TrainerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping
    public String trainersHomePage(Model model){
        List<Student> students = courseService.getStudentsByCourseId(1);
        List<CourseDTO> courses = courseService.getAllCourses();
        TrainersDTO trainer = trainerService.getTrainerById(2); //Update me to implement id parameter
        List<TrainersDTO> trainerList = trainerService.getAllTrainers();

        model.addAttribute("students", students);
        model.addAttribute("courses", courses);
        model.addAttribute("trainers", trainer);
        model.addAttribute("trainersList", trainerList);

        return "trainers"; // look for resources/templates/trainers.html
    }

    @GetMapping("/search")
    public String longCourses(@RequestParam("query") long query, Model model) {
        List<Student> students = courseService.getStudentsByCourseId(1);
        TrainersDTO trainer = trainerService.getTrainerById(2); //Update me to implement id parameter
        List<TrainersDTO> trainerList = trainerService.getAllTrainers();


        model.addAttribute("students", students);
        model.addAttribute("courses", courseService.getCoursesLongerThan(query));
        model.addAttribute("trainers", trainer);
        model.addAttribute("trainersList", trainerList);
        return "trainers";
    }

    //  Show "Add Trainer" form
    @GetMapping("/new-trainer")
    public String showCreateFormModel(Model model) {
        // 1. Create new DTO
        model.addAttribute("newTrainer", new TrainersDTO());

        // 2. Fetch all the courses
        model.addAttribute("courses", courseService.getAllCourses());
        return "trainers/new-trainer";
    }

    // Handle Add Course form submit
    @PostMapping("/new-trainer")
    public String addNewTrainer(@ModelAttribute("newTrainer") TrainersDTO newTrainer) {
       trainerService.saveTrainer(newTrainer);
        return "redirect:/trainers";
    }

    @GetMapping("/{id}/update-trainer")
    public String showEditTrainerForm(@PathVariable Integer id, Model model) {

        TrainersDTO trainers = trainerService.getTrainerById(id);
        List<CourseDTO> courses = courseService.getAllCourses();

        model.addAttribute("trainer", trainers);
        model.addAttribute("courses", courses);

        return "trainers/update-trainer";
    }

    @PostMapping("/{id}/update-trainer")
    public String updateTrainer(@PathVariable Integer id, @ModelAttribute("trainer") TrainersDTO trainer) {

        trainerService.updateTrainer(id, trainer);
        return "redirect:/trainers";
    }

    // Handle Add Course form submit
    @GetMapping("/{id}/view-trainer")
    public String viewTrainer(@PathVariable Integer id, Model model) {
        model.addAttribute("trainer", trainerService.getTrainerById(id));
        CourseDTO course = trainerService.getTrainerCourse(id);
        String courseName = course.getCourseName();
        model.addAttribute("courseName", courseName);

        return "trainers/view-trainer";
    }





}
