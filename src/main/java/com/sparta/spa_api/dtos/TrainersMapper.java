package com.sparta.spa_api.dtos;

import com.sparta.spa_api.entities.Trainers;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrainersMapper {
    TrainersDTO toDTO(Trainers trainers);
    Trainers toEntity(TrainersDTO trainersDTO);
}

