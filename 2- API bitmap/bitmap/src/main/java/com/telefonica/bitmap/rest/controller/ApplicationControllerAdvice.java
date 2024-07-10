package com.telefonica.bitmap.rest.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.telefonica.bitmap.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller advice to handle exceptions globally for all REST controllers.
 */
@RestControllerAdvice
public class ApplicationControllerAdvice {

    /**
     * Handles validation exceptions thrown during method parameter validation.
     * Maps validation errors to a list of error messages and returns an ApiErrors object with HTTP status 400 (Bad Request).
     * @param ex HandlerMethodValidationException instance containing validation errors.
     * @return ApiErrors object containing a list of error messages.
     */
    @ExceptionHandler(HandlerMethodValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handlerMethodValidationException(HandlerMethodValidationException ex ){
        List<String> errors = ex.getAllErrors()
                .stream()
                .map(erro -> erro.getDefaultMessage())
                .collect(Collectors.toList());
        return new ApiErrors(errors);
    }

    /**
     * Handles JSON parse exceptions thrown during HTTP message conversion.
     * Returns a ResponseEntity with HTTP status 400 (Bad Request) and an error message.
     * @param ex JsonParseException instance thrown when JSON parsing fails.
     * @return ResponseEntity with a formatted error message.
     */
    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<String> handleJsonParseException(JsonParseException ex) {
        String errorMessage = "Invalid JSON format: " + ex.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    /**
     * Handles JSON mapping exceptions thrown during HTTP message conversion.
     * Returns a ResponseEntity with HTTP status 400 (Bad Request) and an error message.
     * @param ex JsonMappingException instance thrown when JSON mapping fails.
     * @return ResponseEntity with a formatted error message.
     */
    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<String> handleJsonMappingException(JsonMappingException ex) {
        String errorMessage = "Error mapping JSON: " + ex.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}
