package eu.vlaurin.connect4.model.exception;

import eu.vlaurin.connect4.exception.Connect4Exception;

/**
 * @author vlaurin
 * @since 0.0.0
 */
public class NotPlayerTurnException extends Connect4Exception {
    public NotPlayerTurnException() {
        super("Player must wait for next turn");
    }
}
