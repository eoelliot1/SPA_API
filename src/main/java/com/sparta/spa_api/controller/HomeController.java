package com.sparta.spa_api.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class HomeController {

    @GetMapping
    public String index(Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE d MMMM yyyy");
        String now = LocalDateTime.now().format(formatter);
        model.addAttribute("date", now);
        return "index"; // look for resources/templates/index.html
    }

    @GetMapping("/trainer/dashboard")
    @Secured("ROLE_TRAINER") // or use @PreAuthorize("hasRole('TRAINER')")
    public String trainerDashboard(Model model) {
        model.addAttribute("date", LocalDate.now());
        return "dashboard/trainer"; // Thymeleaf template for trainers
    }

    @GetMapping("/student/dashboard")
    @Secured("ROLE_STUDENT")
    public String studentDashboard(Model model) {
        model.addAttribute("date", LocalDate.now());
        return "dashboard/student"; // Thymeleaf template for students
    }
}
