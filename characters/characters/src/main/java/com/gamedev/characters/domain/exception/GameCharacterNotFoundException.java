package com.gamedev.characters.domain.exception;

public class GameCharacterNotFoundException extends RuntimeException {
    public GameCharacterNotFoundException(Long id) {
        super("No character were found with the id: " + id);
    }
}
