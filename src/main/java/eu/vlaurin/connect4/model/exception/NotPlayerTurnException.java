package eu.vlaurin.connect4.model.exception;

/**
 * @author vlaurin
 * @since 0.0.0
 */
public class NotPlayerTurnException extends RuntimeException {
    public NotPlayerTurnException() {
        super("Player must wait for next turn");
    }
}
