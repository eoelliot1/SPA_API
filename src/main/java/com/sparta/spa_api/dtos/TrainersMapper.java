package com.sparta.spa_api.dtos;

import com.sparta.spa_api.entities.Trainers;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrainersMapper {
    Trainers toDTO(Trainers trainers);
    Trainers toEntity(Trainers trainers);
}

