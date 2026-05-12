package com.gamedev.equipment.infrastructure.input.rest;

import com.gamedev.equipment.domain.model.Armor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArmorRestMapper {
    ArmorDto fromDomainToDto (Armor armor);
    Armor fromDtoToDomain (ArmorDto armorDto);
}
