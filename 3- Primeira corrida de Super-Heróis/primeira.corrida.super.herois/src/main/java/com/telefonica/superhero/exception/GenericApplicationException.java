package com.telefonica.superhero.exception;

/**
 * Generic exception class for handling errors related to race data processing.
 */
public class GenericApplicationException extends RuntimeException {

    /**
     * Constructs a new GenericApplicationException with the specified detail
     * message and cause.
     *
     * @param message the detail message explaining the reason for the exception.
     * @param cause the cause of the exception (a throwable that caused this
     * exception to be thrown).
     */
    public GenericApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
