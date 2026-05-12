package com.gamedev.equipment.infrastructure.output.persistence;

import com.gamedev.equipment.domain.model.CharacterEquipment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CharacterEquipmentPersistenceMapper {

    @Mapping(source = "characterId", target = "id")
    @Mapping(source = "characterId", target = "characterId")
    CharacterEquipment fromJpaToDomain(CharacterEquipmentJpa jpa);

    @Mapping(source = "characterId", target = "characterId")
    @Mapping(target = "armorId", source = "armorId")
    @Mapping(target = "helmetId", source = "helmetId")
    @Mapping(target = "weaponId", source = "weaponId")
    CharacterEquipmentJpa fromDomainToJpa(CharacterEquipment domain);
}
