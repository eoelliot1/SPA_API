package com.sparta.spa_api.dtos;
import com.sparta.spa_api.entities.Spartan;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpartanMapper {
    SpartanDTO toDto(Spartan spartan);
    Spartan toEntity(SpartanDTO spartanDTO);
}
