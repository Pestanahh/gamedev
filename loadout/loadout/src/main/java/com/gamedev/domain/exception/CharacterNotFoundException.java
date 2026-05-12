package com.gamedev.domain.exception;

public class CharacterNotFoundException extends RuntimeException {
    public CharacterNotFoundException(Long id) {
        super("No character were found with the id: " + id);
    }
}
