package com.sparta.spa_api.dtos;

import com.sparta.spa_api.dtos.StudentDTO;
import com.sparta.spa_api.entities.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(source = "course.id", target = "courseId")
    @Mapping(source = "trainer.id", target = "trainerId") // ✅ ADD
    StudentDTO toDTO(Student student);

    @Mapping(target = "course", ignore = true)
    @Mapping(target = "trainer", ignore = true) // ✅ ADD
    Student toEntity(StudentDTO studentDTO);
}
