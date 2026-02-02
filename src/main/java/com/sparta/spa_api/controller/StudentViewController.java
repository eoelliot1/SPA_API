package com.sparta.spa_api.controller;//package com.sparta.spa_api.controller;
//
//import com.sparta.spa_api.dtos.CourseDTO;
//import com.sparta.spa_api.dtos.StudentDTO;
//import com.sparta.spa_api.entities.Spartan;
//import com.sparta.spa_api.entities.Student;
//import com.sparta.spa_api.repository.SpartanRepository;
//import com.sparta.spa_api.services.CourseService;
//import com.sparta.spa_api.services.StudentService;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.security.Principal;
//import java.util.List;
//
//@Controller
//@RequestMapping("/students")
//public class StudentViewController {
//
//    private final StudentService studentService;
//    private final CourseService courseService;
//    private final SpartanRepository spartanRepository; // add this
//
//
//    public StudentViewController(StudentService studentService, CourseService courseService, SpartanRepository spartanRepository) {
//        this.studentService = studentService;
//        this.courseService = courseService;
//        this.spartanRepository = spartanRepository;
//
//    }
//
//    // Show all students
//    @GetMapping("/")
//    public String viewStudents(Model model) {
//        List<Student> students = studentService.getAllStudentEntities();
//        model.addAttribute("students", students);
//        return "students";
//    }
//
//    // Show single student
//    @GetMapping("/student-details/{id}")
//    public String viewStudent(@PathVariable Integer id, Model model) {
//        Student student = studentService.getStudentEntityById(id);
//        model.addAttribute("student", student);
//        return "students/student-details";
//    }
//
//   @GetMapping("/new-student")
//   public String newStudentForm(Model model) {
//       // 1. Prepare the DTO for the form
//       model.addAttribute("newStudent", new StudentDTO());
//
//       // 2. Fetch the CourseDTOs from your service
//       List<CourseDTO> courses = courseService.getAllCourses();
//       model.addAttribute("courses", courses);
//
//       return "students/new-student";
//   }
//    @PostMapping("/save")
//    public String saveStudent(@ModelAttribute("newStudent") StudentDTO newStudent) {
//        studentService.saveStudent(newStudent);
//        return "redirect:/students";
//    }
//
//    @PostMapping("/{id}/delete")
//    public String deleteStudent(@PathVariable Integer id) {
//        studentService.deleteStudent(id);
//        return "redirect:/students";
//    }
//
//
//    @GetMapping("/update-student/{id}")
//    public String showUpdateForm(@PathVariable Integer id, Model model) {
//
//        StudentDTO studentDto = studentService.getStudentById(id);
//        model.addAttribute("updatedStudent", studentDto);
//        model.addAttribute("courses", courseService.getAllCourses());
//
//        return "students/update-student";
//    }
//
//
//    @PostMapping("/save-update/{id}")
//    public String updateStudent(@PathVariable Integer id, @ModelAttribute("updatedStudent") StudentDTO studentDto) {
//        studentService.updateStudent(id, studentDto);
//        return "redirect:/students";
//    }
//
//    @GetMapping("/courses")
//    public String viewMyCourses(Model model) {
//        List<CourseDTO> courses = courseService.getAllCourses();
//        model.addAttribute("courses", courses);
//        return "students/courses";
//    }
//
//    @GetMapping("/students/profile")
//    public String viewOwnProfile(Model model) {
//        StudentDTO student = studentService.getStudentById(1);
//
//        if (student == null) {
//            return "redirect:/students/courses";
//        }
//
//        model.addAttribute("student", student);
//        return "students/profile";
//    }
//
//
//}

import com.sparta.spa_api.dtos.CourseDTO;
import com.sparta.spa_api.dtos.StudentDTO;
import com.sparta.spa_api.entities.Course;
import com.sparta.spa_api.entities.Spartan;
import com.sparta.spa_api.entities.Student;
import com.sparta.spa_api.repository.SpartanRepository;
import com.sparta.spa_api.repository.StudentRepository;
import com.sparta.spa_api.services.CourseService;
import com.sparta.spa_api.services.StudentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentViewController {

    private final StudentService studentService;
    private final CourseService courseService;
    private final SpartanRepository spartanRepository;
    private final StudentRepository studentRepository;

    public StudentViewController(StudentService studentService,
                                 CourseService courseService,
                                 SpartanRepository spartanRepository, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.spartanRepository = spartanRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/")
    public String viewStudents(Model model) {
        List<Student> students = studentService.getAllStudentEntities();
        model.addAttribute("students", students);
        return "students/";
    }

    @GetMapping("/student-details/{id}")
    public String viewStudent(@PathVariable Integer id, Model model) {
        Student student = studentService.getStudentEntityById(id);
        model.addAttribute("student", student);
        return "students/student-details";
    }

    @GetMapping("/new-student")
    public String newStudentForm(Model model) {
        model.addAttribute("newStudent", new StudentDTO());
        model.addAttribute("courses", courseService.getAllCourses());
        return "students/new-student";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("newStudent") StudentDTO newStudent) {
        studentService.saveStudent(newStudent);
        return "redirect:/students/";
    }

    @PostMapping("/{id}/delete")
    public String deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return "redirect:/students/";
    }

    @GetMapping("/update-student/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        StudentDTO studentDto = studentService.getStudentById(id);
        model.addAttribute("updatedStudent", studentDto);
        model.addAttribute("courses", courseService.getAllCourses());
        return "students/update-student";
    }

    @PostMapping("/save-update/{id}")
    public String updateStudent(@PathVariable Integer id,
                                @ModelAttribute("updatedStudent") StudentDTO studentDto) {
        studentService.updateStudent(id, studentDto);
        return "redirect:/students/profile";
    }

    @GetMapping("/courses")
    public String viewMyCourses(Model model) {
        // Get student ID 1 for testing
        Student student = studentService.getStudentEntityById(1);

        // All courses
        List<Course> allCourses = courseService.getAllCourseEntities();

        // Enrolled courses (student only has one course)
        List<Course> enrolled = student.getCourse() != null
                ? List.of(student.getCourse())
                : List.of();

        // Courses student is NOT enrolled in
        List<Course> notEnrolled = new java.util.ArrayList<>();
        for (Course c : allCourses) {
            if (!enrolled.contains(c)) {
                notEnrolled.add(c);
            }
        }

        model.addAttribute("student", student);
        model.addAttribute("enrolledCourses", enrolled);
        model.addAttribute("availableCourses", notEnrolled);

        return "students/courses";
    }





    @GetMapping("/profile")
    public String viewProfile(Model model) {
        Student student = studentService.getStudentEntityById(1);

        if (student == null) {
            student = new Student();
            student.setStudentName("Test Student");
            student.setHasGraduated(false);
        }

        model.addAttribute("student", student);
        return "students/profile";
    }



}

