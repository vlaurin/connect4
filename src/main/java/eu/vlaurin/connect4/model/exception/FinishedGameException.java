package eu.vlaurin.connect4.model.exception;

/**
 * @author vlaurin
 * @since 0.0.0
 */
public class FinishedGameException extends RuntimeException {
    public FinishedGameException() {
        super("Cannot play in finished game");
    }
}
