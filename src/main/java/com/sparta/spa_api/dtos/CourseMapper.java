package com.sparta.spa_api.dtos;

import com.sparta.spa_api.entities.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(target = "durationInDays", expression = "java(course.getDurationInDays())")
    CourseDTO toDTO(Course course);

    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "endDate", source = "endDate")
    Course toEntity(CourseDTO courseDTO);
}
