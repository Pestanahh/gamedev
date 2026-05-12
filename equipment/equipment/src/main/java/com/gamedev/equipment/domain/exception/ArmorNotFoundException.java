package com.gamedev.equipment.domain.exception;

public class ArmorNotFoundException extends RuntimeException {
    public ArmorNotFoundException(Long id) {
        super("No armor were found with id: " + id);
    }
}
