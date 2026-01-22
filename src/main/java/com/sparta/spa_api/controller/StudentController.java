package com.sparta.spa_api.controller;

import com.sparta.spa_api.dtos.StudentDTO;
import com.sparta.spa_api.entities.Student;
import com.sparta.spa_api.services.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Trainee Details", description = "CRUD operations performed on trainee table")
@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service){
        this.service = service;
    }


    @Operation(summary = "Get all Students", description = "Retrieve a list of all students")
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents()  {
        return ResponseEntity.ok(service.getAllStudents());
    }



    @Operation(summary = "Get student by ID", description = "Retrieve student by ID")
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable  Integer id){
        StudentDTO student = service.getStudentByID(id);
        if(student != null){
            return ResponseEntity.ok(student);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Update a student", description = "Modify an existing student's details in the database")
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Integer id, @PathVariable String studentName, @Valid  @RequestBody Student student) {
        student.setStudentName(studentName);
        try {
            StudentDTO updatedStudent = service.updateStudent(student);
            return ResponseEntity.ok(updatedStudent);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Student", description = "delete Student in the database")
    public ResponseEntity<Void> deleteStudent(@PathVariable  Integer id){
        boolean deleted = service.deleteStudent(id);

        if(deleted){
            return  ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }


    }



    @Operation(summary = "Get graduation status", description = "Check graduation status")
    @GetMapping("/{id}/graduated")
    public boolean hasGraduated(@PathVariable Integer id) {
        return service.hasGraduated(id);
    }


}
