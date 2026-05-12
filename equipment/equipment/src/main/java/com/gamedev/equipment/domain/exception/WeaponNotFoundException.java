package com.gamedev.equipment.domain.exception;

public class WeaponNotFoundException extends RuntimeException {
    public WeaponNotFoundException(Long id) {
        super("No weapon were found with id: " + id);
    }
}
