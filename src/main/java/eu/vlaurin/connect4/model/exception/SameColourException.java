package eu.vlaurin.connect4.model.exception;

/**
 * @author vlaurin
 * @since 0.0.0
 */
public class SameColourException extends RuntimeException {
    public SameColourException() {
        super("Players must have different colours");
    }
}
