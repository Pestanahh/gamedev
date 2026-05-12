package com.gamedev.equipment.infrastructure.output.persistence;

import com.gamedev.equipment.domain.model.Armor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArmorPersistenceMapper {

    ArmorJpa fromDomainToJpa (Armor armor);
    Armor fromJpaToDomain (ArmorJpa jpa);

}
