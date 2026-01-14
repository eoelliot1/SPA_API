package com.sparta.spa_api.dtos;

import com.sparta.spa_api.entities.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class StudentMapper {
    Student toDTO(Student student);
    Student toEntity(Student student);
}
