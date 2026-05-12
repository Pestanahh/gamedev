package com.gamedev.characters.domain.exception;

public class EmptyListException extends RuntimeException {
    public EmptyListException() {
        super("Empty list: no game characters were found");
    }
}
