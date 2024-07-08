package com.telefonica.superhero.exception;

/**
 * Custom exception class for handling errors related to race data processing.
 */
public class CustomRaceDataException extends RuntimeException {

    /**
     * Constructs a new CustomRaceDataException with the specified detail message and cause.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method).
     * @param cause   the cause (which is saved for later retrieval by the getCause() method).
     */
    public CustomRaceDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
