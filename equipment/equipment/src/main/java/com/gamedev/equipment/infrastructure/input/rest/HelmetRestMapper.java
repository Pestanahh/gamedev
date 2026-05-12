package com.gamedev.equipment.infrastructure.input.rest;

import com.gamedev.equipment.domain.model.Helmet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HelmetRestMapper {
    HelmetDto fromDomainToDto (Helmet helmet);
    Helmet fromDtoToDomain (HelmetDto helmetDto);
}
