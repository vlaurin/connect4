package eu.vlaurin.connect4.exception;

/**
 * @author vlaurin
 * @since 0.0.0
 */
public class ValidationException extends Connect4Exception {
    public ValidationException(String message) {
        super("Validation error: " + message);
    }
}
