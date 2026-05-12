package com.gamedev.equipment.domain.exception;

public class HelmetNotFoundException extends RuntimeException {
    public HelmetNotFoundException(Long id) {
        super("No helmet were found with id: " + id);
    }
}
