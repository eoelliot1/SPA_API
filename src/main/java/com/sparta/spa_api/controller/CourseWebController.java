//package com.sparta.spa_api.controller;
//
//import com.sparta.spa_api.dtos.CourseDTO;
//import com.sparta.spa_api.entities.Course;
//import com.sparta.spa_api.services.CourseService;
//import com.sparta.spa_api.services.StudentService;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import org.thymeleaf.spring6.ISpringTemplateEngine;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/courses") // Base path for web endpoints
//public class CourseWebController {
//
//    private final CourseService courseService;
//    private final StudentService studentService;
//    private final ISpringTemplateEngine iSpringTemplateEngine;
//
//    @Autowired
//    public CourseWebController(CourseService courseService, StudentService studentService, ISpringTemplateEngine iSpringTemplateEngine) {
//        this.courseService = courseService;
//        this.studentService = studentService;
//        this.iSpringTemplateEngine = iSpringTemplateEngine;
//    }
//
//    // ===== List all courses =====
//    @GetMapping("/")
//    public String viewCourses(Model model) {
//
//        List<CourseDTO> courses = courseService.getAllCourses();
//        System.out.print(courses);
//        model.addAttribute("courses", courses);
//        return "courses/index";
//    }
//
//    @GetMapping("/{id}")
//    public String viewCourse(@PathVariable int id, Model model) {
//        model.addAttribute("course", courseService.getCourseById(id));
//        return "courses/view";
//    }
//
//    @GetMapping("/new")
//    public String newCourseForm(Model model) {
//        model.addAttribute("course", new CourseDTO());
//        return "courses/new";
//    }
//    @PostMapping
//    public String createCourse(
//            @Valid @ModelAttribute("course") CourseDTO course,
//            BindingResult result
//    ) {
//        if (result.hasErrors()) {
//            return "courses/new";
//        }
//        courseService.saveCourse(course);
//        return "redirect:/courses/";
//    }
//    @GetMapping("/{id}/edit")
//    public String editCourseForm(@PathVariable int id, Model model) {
//        model.addAttribute("course", courseService.getCourseById(id));
//        return "courses/update";
//    }
//
//
//
//}

package com.sparta.spa_api.controller;

import com.sparta.spa_api.dtos.CourseDTO;
import com.sparta.spa_api.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
//@RequestMapping("/courses")
//public class CourseWebController {
//
//    private final CourseService courseService;
//
//    public CourseWebController(CourseService courseService) {
//        this.courseService = courseService;
//    }
//
//
//    @GetMapping("/")
//    public String viewCourses(Model model) {
//        List<CourseDTO> courses = courseService.getAllCourses();
//        model.addAttribute("courses", courses);
//        return "courses/index";
//    }
//
//
//    @GetMapping("/{id}")
//    public String viewCourse(@PathVariable Integer id, Model model) {
//        CourseDTO course = courseService.getCourseById(id);
//        model.addAttribute("course", course);
//        return "courses/details";
//    }
//
//
//    @GetMapping("/new")
//    public String showCreateForm(Model model) {
//        model.addAttribute("course", new CourseDTO());
//        return "courses/new";
//    }
//
//
//    @PostMapping
//    public String createCourse(
//            @Valid @ModelAttribute("course") CourseDTO course,
//            BindingResult result
//    ) {
//        if (result.hasErrors()) {
//            return "courses/new";
//        }
//        courseService.saveCourse(course);
//        return "redirect:/courses/";
//    }
//
//
//    @GetMapping("/{id}/edit")
//    public String showUpdateForm(@PathVariable Integer id, Model model) {
//        CourseDTO course = courseService.getCourseById(id);
//        model.addAttribute("course", course);
//        return "courses/update"; // ✅ renamed file
//    }
//
//
//    @PostMapping("/{id}")
//    public String updateCourse(
//            @PathVariable Integer id,
//            @Valid @ModelAttribute("course") CourseDTO course,
//            BindingResult result
//    ) {
//        if (result.hasErrors()) {
//            return "courses/update";
//        }
//        courseService.updateCourse(id, course);
//        return "redirect:/courses/";
//    }
//
//
//    @PostMapping("/{id}/delete")
//    public String deleteCourse(@PathVariable Integer id) {
//        courseService.deleteCourse(id);
//        return "redirect:/courses/";
//    }
//}

//package com.sparta.spa_api.controller;

import com.sparta.spa_api.entities.Course;
import com.sparta.spa_api.repository.CourseRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
//@RequestMapping("/courses")
//public class CourseWebController {
//
//    private final CourseRepository courseRepository;
//
//    public CourseWebController(CourseRepository courseRepository) {
//        this.courseRepository = courseRepository;
//    }
//
//    // ===== LIST ALL COURSES =====
//    @GetMapping("/")
//    public String listCourses(Model model) {
//        model.addAttribute("courses", courseRepository.findAll());
//        return "courses/index";
//    }
//
//    // ===== VIEW SINGLE COURSE =====
//    @GetMapping("/{id}")
//    public String viewCourse(@PathVariable int id, Model model) {
//        Course course = courseRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid course ID: " + id));
//
//        model.addAttribute("course", course);
//        return "courses/details";
//    }
//
//    // ===== SHOW CREATE FORM =====
//    @GetMapping("/new")
//    public String showNewCourseForm(Model model) {
//        model.addAttribute("course", new Course());
//        return "courses/new";
//    }
//
//    // ===== CREATE COURSE =====
//    @PostMapping("/new")
//    public String createCourse(@ModelAttribute Course course) {
//        courseRepository.save(course);
//        return "redirect:/courses/";
//    }
//
//    // ===== SHOW UPDATE FORM =====
//    @GetMapping("/{id}/edit")
//    public String showEditCourseForm(@PathVariable int id, Model model) {
//        Course course = courseRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid course ID: " + id));
//
//        model.addAttribute("course", course);
//        return "courses/update";
//    }
//
//    // ===== UPDATE COURSE =====
//    @PostMapping("/{id}/update")
//    public String updateCourse(@PathVariable int id, @ModelAttribute Course updatedCourse) {
//
//        Course existingCourse = courseRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid course ID: " + id));
//
//        existingCourse.setCourseName(updatedCourse.getCourseName());
//
//        courseRepository.save(existingCourse);
//        return "redirect:/courses/";
//    }
//
//    // ===== DELETE COURSE =====
//    @PostMapping("/{id}/delete")
//    public String deleteCourse(@PathVariable int id) {
//        Course course = courseRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid course ID: " + id));
//
//        courseRepository.delete(course);
//        return "redirect:/courses/";
//    }
//
//    // ===== SEARCH COURSES =====
//    @GetMapping("/search")
//    public String searchCourses(@RequestParam("query") String query, Model model) {
//        List<Course> results = courseRepository.findByCourseNameContainingIgnoreCase(query);
//        model.addAttribute("courses", results);
//        return "courses/index";
//    }
//}
//
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
}

