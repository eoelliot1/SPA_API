package com.sparta.spa_api.dtos;

import com.sparta.spa_api.entities.Trainers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TrainersMapper {

    @Mapping(source = "course.id", target = "courseId")
    TrainersDTO toDTO(Trainers trainer);

    @Mapping(target = "course", ignore = true)
    @Mapping(target = "students", ignore = true)
    Trainers toEntity(TrainersDTO dto);
}

