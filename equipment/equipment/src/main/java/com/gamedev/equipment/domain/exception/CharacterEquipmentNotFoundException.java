package com.gamedev.equipment.domain.exception;

public class CharacterEquipmentNotFoundException extends RuntimeException {
    public CharacterEquipmentNotFoundException(Long characterId) {
        super("No equipment found for character with id: " + characterId);
    }
}
