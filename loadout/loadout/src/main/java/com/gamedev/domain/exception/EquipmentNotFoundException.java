package com.gamedev.domain.exception;

public class EquipmentNotFoundException extends RuntimeException {
    public EquipmentNotFoundException(Long id) {
        super("No equipment were found with the id: " + id);
    }
}
