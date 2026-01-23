package com.sparta.spa_api.controller;

import com.sparta.spa_api.dtos.StudentDTO;
import com.sparta.spa_api.services.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service){
        this.service = service;
    }

    @Operation(summary = "Get all Students")
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return ResponseEntity.ok(service.getAllStudents());
    }

    @Operation(summary = "Get Student by ID")
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Integer id){
        return ResponseEntity.ok(service.getStudentById(id));
    }

    @Operation(summary = "Create a Student")
    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@Valid @RequestBody StudentDTO dto){
        return ResponseEntity.status(201).body(service.saveStudent(dto));
    }

    @Operation(summary = "Update a Student")
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Integer id, @Valid @RequestBody StudentDTO dto){
        return ResponseEntity.ok(service.updateStudent(id, dto));
    }

    @Operation(summary = "Delete a Student")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id){
        service.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Check if Student has graduated")
    @GetMapping("/{id}/graduated")
    public ResponseEntity<Boolean> hasGraduated(@PathVariable Integer id){
        return ResponseEntity.ok(service.hasGraduated(id));
    }

}
