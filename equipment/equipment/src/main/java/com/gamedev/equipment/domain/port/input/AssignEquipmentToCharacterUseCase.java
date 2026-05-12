package com.gamedev.equipment.domain.port.input;

import com.gamedev.equipment.domain.model.CharacterEquipment;

public interface AssignEquipmentToCharacterUseCase {

    CharacterEquipment assignEquipmentToCharacter(Long characterId, CharacterEquipment characterEquipment);
}
