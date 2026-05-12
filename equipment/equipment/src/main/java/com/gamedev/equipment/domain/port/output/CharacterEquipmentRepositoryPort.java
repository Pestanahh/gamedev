package com.gamedev.equipment.domain.port.output;

import com.gamedev.equipment.domain.model.CharacterEquipment;

import java.util.Optional;

public interface CharacterEquipmentRepositoryPort {

    CharacterEquipment save(CharacterEquipment characterEquipment);
    Optional<CharacterEquipment> findByCharacterId(Long characterId);
    boolean existsByCharacterId(Long characterId);
}
