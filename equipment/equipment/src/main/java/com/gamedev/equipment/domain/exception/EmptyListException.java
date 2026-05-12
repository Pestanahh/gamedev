package com.gamedev.equipment.domain.exception;

public class EmptyListException extends RuntimeException {
    public EmptyListException() {
        super("Empty list: no elements were found");
    }
}
