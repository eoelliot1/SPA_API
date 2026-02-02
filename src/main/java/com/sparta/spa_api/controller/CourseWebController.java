
package com.sparta.spa_api.controller;

import com.sparta.spa_api.dtos.CourseDTO;
import com.sparta.spa_api.dtos.CourseMapper;
import com.sparta.spa_api.dtos.StudentDTO;
import com.sparta.spa_api.dtos.TrainersDTO;
import com.sparta.spa_api.entities.Course;
import com.sparta.spa_api.repository.CourseRepository;
import com.sparta.spa_api.repository.TrainersRepository;
import com.sparta.spa_api.services.CourseService;
import com.sparta.spa_api.services.StudentService;
import com.sparta.spa_api.services.TrainerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@Controller
@RequestMapping("/courses")
public class CourseWebController {

    private final CourseService courseService;
    private final StudentService studentService;
    private final CourseRepository courseRepository;
    private final TrainerService trainerService;
    private final TrainersRepository trainersRepository;
    private final CourseMapper courseMapper;

    public CourseWebController(CourseService courseService, StudentService studentService, CourseRepository courseRepository, TrainerService trainerService, TrainersRepository trainersRepository, CourseMapper courseMapper) {
        this.courseService = courseService;
        this.studentService = studentService;
        this.courseRepository = courseRepository;
        this.trainerService = trainerService;
        this.trainersRepository = trainersRepository;
        this.courseMapper = courseMapper;
    }

    // List all courses
    @GetMapping("/")
    public String listCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "courses/index";
    }

    // Show course details
    @GetMapping("/{id}")
    public String viewCourse(@PathVariable Integer id, Model model) {
        model.addAttribute("course", courseService.getCourseById(id));
        return "courses/view"; // details.html
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("course", new Course()); // uses entity, no DTO needed
        return "courses/new";
    }

    // Handle Add Course form submit
    @PostMapping("/new")
    public String createCourse(@ModelAttribute CourseDTO course) {
        courseService.saveCourse(course);
        return "redirect:/courses/";
    }

    // Show "Edit Course" form
    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        model.addAttribute("course", courseService.getCourseById(id));
        return "courses/update"; // update.html
    }

    // Handle Edit form submit
    @PostMapping("/{id}/update")
    public String updateCourse(@PathVariable Integer id, @ModelAttribute CourseDTO course) {
        courseService.updateCourse(id, course);
        return "redirect:/courses/";
    }

    // Handle Delete
    @PostMapping("/{id}/delete")
    public String deleteCourse(@PathVariable Integer id) {
        courseService.deleteCourse(id);
        return "redirect:/courses/";
    }

    @GetMapping("/search")
    public String searchCourses(
            @RequestParam("keyword") String keyword,
            Model model
    ) {
        model.addAttribute("courses", courseService.searchCourses(keyword));
        model.addAttribute("keyword", keyword);
        return "courses/index";
    }

    @GetMapping("/enrollment")
    public String enrolCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("trainers", trainerService.getAllTrainers());

        return "courses/enrollment";
    }

    @PostMapping("/enrollment/{id}/{cid}/enrol")
    public String enrol(@PathVariable Integer id, @PathVariable Integer cid) {
        // 1. Fetch student
        StudentDTO updatedStudent = studentService.getStudentById(id);

        // 2. Assign course
        updatedStudent.setCourseId(cid);

        // 3. Assign trainer (if course has one)
        try{
            int a = courseService.getCourseTrainer(cid).getId();
            updatedStudent.setTrainerId(a);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("No trainer in associated course probably...");
        }


        // 4. Update student
        studentService.updateStudent(id, updatedStudent);

        // 5. Redirect (PRG pattern)
        return "redirect:/courses/enrollment";
    }

    @PostMapping("/enrollment/{id}/{cid}/enroltrainer")
    public String enrolTrainer(@PathVariable Integer id, @PathVariable Integer cid) {
        // 1. Fetch student
        TrainersDTO updatedTrainer = trainerService.getTrainerById(id);

        // 2. Assign course and update the trainerService
        updatedTrainer.setCourseId(cid);
        trainerService.updateTrainer(id, updatedTrainer);

        // 3. update course with trainer
        CourseDTO updatedCourse = courseService.setTrainer(updatedTrainer, courseService.getCourseById(cid));
        courseService.updateCourse(cid, updatedCourse);

        // 5. Redirect (PRG pattern)
        return "redirect:/courses/enrollment";
    }

}

