package main.java.com.telefonica.exception;

/**
 * Exception thrown when an invalid character is encountered in input data.
 */
public class InvalidCharacterException extends RuntimeException {

    /**
     * Constructs a new InvalidCharacterException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method).
     */
    public InvalidCharacterException(String message) {
        super(message);
    }
}
