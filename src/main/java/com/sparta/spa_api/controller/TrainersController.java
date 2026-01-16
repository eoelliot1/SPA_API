package com.sparta.spa_api.controller;

import com.sparta.spa_api.dtos.CourseDTO;
import com.sparta.spa_api.dtos.TrainersDTO;
import com.sparta.spa_api.entities.Course;
import com.sparta.spa_api.services.TrainerService;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class TrainersController {

    private final TrainerService service;

    public TrainersController(TrainerService service) {
        this.service = service;
    }

    @Operation(summary = "Get course", description = "Retrieve the trainer's course.")
    @GetMapping(value = "/trainers/{trainers}/courses")
    //It is supposed to be "trainers/{id}/courses" but trainers don't have an ID.
    public ResponseEntity<CourseDTO> getTraineresCourse(@PathVariable TrainersDTO trainer) {
        CourseDTO Course = service.getCourse(trainer);

        if(Course != null || trainer != null) {
            return ResponseEntity.status(201).body(Course);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Update my course name", description = "Retrieve the trainer's course and update's the name")
    @GetMapping(value = "/trainers/{trainers}/courses/setCourse")
    public ResponseEntity<CourseDTO> updateCourseName(@RequestBody TrainersDTO trainerDTO,@PathVariable String newCourseName) {
        //Course courseDTO = trainerDTO.getCourse_id();
        //courseDTO.setCourse_name(newCourseName);
        try {
             CourseDTO updatedCourse = service.setCourseName(trainerDTO, newCourseName);
             return ResponseEntity.status(201).body(updatedCourse);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Update my trainer name", description = "Update's the trainers name.")
    @GetMapping(value = "/trainers/{name}")
    public ResponseEntity<TrainersDTO> updateTrainerName(@RequestBody TrainersDTO trainerDTO, @PathVariable String newTrainerName) {
        try {
            TrainersDTO updatedTrainer = service.setTrainerName(trainerDTO, newTrainerName);
            return ResponseEntity.status(201).body(updatedTrainer);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Update my trainer id", description = "Update's the trainers id.")
    @GetMapping(value = "/trainers/{id}")
    public ResponseEntity<TrainersDTO> updateTrainerId(@RequestBody TrainersDTO trainerDTO, @PathVariable Integer newID) {
        try {
            TrainersDTO updatedTrainer = service.setTrainerId(trainerDTO, newID);
            return ResponseEntity.status(201).body(updatedTrainer);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

}

