package com.gamedev.equipment.infrastructure.input.rest;

import com.gamedev.equipment.domain.model.CharacterEquipment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharacterEquipmentRestMapper {
    CharacterEquipmentDto fromDomainToDto(CharacterEquipment characterEquipment);
    CharacterEquipment fromDtoToDomain(CharacterEquipmentDto characterEquipmentDto);
}
