package com.gamedev.equipment.infrastructure.input.rest;

import com.gamedev.equipment.domain.exception.ArmorNotFoundException;
import com.gamedev.equipment.domain.exception.CharacterEquipmentNotFoundException;
import com.gamedev.equipment.domain.exception.DuplicateNameException;
import com.gamedev.equipment.domain.exception.EmptyListException;
import com.gamedev.equipment.domain.exception.HelmetNotFoundException;
import com.gamedev.equipment.domain.exception.WeaponNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ArmorNotFoundException.class)
    public ResponseEntity<String> handleArmorNotFound(ArmorNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND); // 404
    }

    @ExceptionHandler(HelmetNotFoundException.class)
    public ResponseEntity<String> handleHelmetNotFound(HelmetNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND); // 404
    }

    @ExceptionHandler(WeaponNotFoundException.class)
    public ResponseEntity<String> handleWeaponNotFound(WeaponNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND); // 404
    }

    @ExceptionHandler(CharacterEquipmentNotFoundException.class)
    public ResponseEntity<String> handleCharacterEquipmentNotFound(CharacterEquipmentNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND); // 404
    }

    @ExceptionHandler(DuplicateNameException.class)
    public ResponseEntity<String> handleDuplicate(DuplicateNameException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT); // 409
    }

    @ExceptionHandler(EmptyListException.class)
    public ResponseEntity<String> handleEmptyList(EmptyListException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NO_CONTENT); // 204
    }

    // Override the parent ResponseEntityExceptionHandler method
    // that handles the exception thrown by Spring when @Valid fails in the controller
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, // exception automatically thrown by Spring when @Valid detects errors
            HttpHeaders headers,                // HTTP headers of the request
            HttpStatusCode status,              // original HTTP status code
            WebRequest request) {               // request information

        // getBindingResult() contains all validation errors detected
        // getFieldErrors() returns the list of fields that failed
        // With the stream we map each error to its custom message (the one we put in @NotBlank, @NotNull...)
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        // Return the list of error messages with 400 Bad Request code
        // so the client knows exactly which fields are invalid
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // Method for when we leave an int field empty
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        return new ResponseEntity<>("The request body contains invalid values", HttpStatus.BAD_REQUEST);
    }

}
