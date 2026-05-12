package com.gamedev.equipment.application.service;

import com.gamedev.equipment.domain.exception.CharacterEquipmentNotFoundException;
import com.gamedev.equipment.domain.exception.DuplicateNameException;
import com.gamedev.equipment.domain.model.CharacterEquipment;
import com.gamedev.equipment.domain.port.input.AssignEquipmentToCharacterUseCase;
import com.gamedev.equipment.domain.port.output.CharacterEquipmentRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterEquipmentService implements AssignEquipmentToCharacterUseCase {

    private final CharacterEquipmentRepositoryPort characterEquipmentRepositoryPort;

    @Override
    public CharacterEquipment assignEquipmentToCharacter(Long characterId, CharacterEquipment characterEquipment) {

        if (characterEquipmentRepositoryPort.existsByCharacterId(characterId)) {
            throw new DuplicateNameException("Character with id " + characterId + " already has equipment assigned");
        }

        characterEquipment.setCharacterId(characterId);

        return characterEquipmentRepositoryPort.save(characterEquipment);
    }
}
