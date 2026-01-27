
package com.sparta.spa_api.controller;

import com.sparta.spa_api.dtos.CourseDTO;
import com.sparta.spa_api.services.CourseService;
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

    public CourseWebController(CourseService courseService) {
        this.courseService = courseService;
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

    // Show "Add Course" form
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("course", new CourseDTO());
        return "courses/new"; // new.html
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
}

 