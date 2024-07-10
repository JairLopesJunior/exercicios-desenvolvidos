package main.java.com.telefonica.exception;

/**
 * Exception thrown when a number is found outside the valid range [0, 15].
 */
public class InvalidNumberRangeException extends RuntimeException {

    /**
     * Constructs a new InvalidNumberRangeException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method).
     */
    public InvalidNumberRangeException(String message) {
        super(message);
    }
}
