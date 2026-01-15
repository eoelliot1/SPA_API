package com.sparta.spa_api.dtos;

import com.sparta.spa_api.entities.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseDTO toDTO(Course course);
    Course toEntity(CourseDTO courseDTO);
}
