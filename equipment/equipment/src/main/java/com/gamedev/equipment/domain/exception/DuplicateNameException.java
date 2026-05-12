package com.gamedev.equipment.domain.exception;

public class DuplicateNameException extends RuntimeException {
    public DuplicateNameException(String name) {
        super("The name " + name + " it's already being used");
    }
}
