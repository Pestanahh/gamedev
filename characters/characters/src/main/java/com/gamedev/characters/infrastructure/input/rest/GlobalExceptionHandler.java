package com.gamedev.characters.infrastructure.input.rest;

import com.gamedev.characters.domain.exception.DuplicateNameException;
import com.gamedev.characters.domain.exception.EmptyListException;
import com.gamedev.characters.domain.exception.GameCharacterNotFoundException;
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

    @ExceptionHandler(GameCharacterNotFoundException.class)
    public ResponseEntity<String> handleNotFound(GameCharacterNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND); // 404
    }

    @ExceptionHandler(DuplicateNameException.class)
    public ResponseEntity<String> handleDuplicado(DuplicateNameException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT); // 409
    }

    @ExceptionHandler(EmptyListException.class)
    public ResponseEntity<String> handleListaVacia(EmptyListException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NO_CONTENT); // 204
    }

    // Sobreescribimos el metodo del padre ResponseEntityExceptionHandler
    // que maneja la excepción que lanza Spring cuando @Valid falla en el controlador
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, // excepción lanzada automáticamente por Spring cuando @Valid detecta errores
            HttpHeaders headers,                // cabeceras HTTP de la petición
            HttpStatusCode status,              // código de estado HTTP original
            WebRequest request) {               // información de la petición

        // getBindingResult() contiene todos los errores de validación detectados
        // getFieldErrors() devuelve la lista de campos que han fallado
        // Con el stream mapeamos cada error a su mensaje personalizado (el que pusimos en @NotBlank, @NotNull...)
        List<String> errores = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        // Devolvemos la lista de mensajes de error con código 400 Bad Request
        // para que el cliente sepa exactamente qué campos son inválidos
        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }

    // Metodo para cuando dejamos un campo int vacío
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        return new ResponseEntity<>("El cuerpo de la petición contiene valores inválidos", HttpStatus.BAD_REQUEST);
    }


}
