package com.sparta.spa_api.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/courses")
public class AdminCourseController {

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminCourses() {
        return "courses/adminindex";
    }
}
