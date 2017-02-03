package eu.vlaurin.connect4.model.exception;

import eu.vlaurin.connect4.exception.Connect4Exception;

/**
 * @author vlaurin
 * @since 0.0.0
 */
public class SameColourException extends Connect4Exception {
    public SameColourException() {
        super("Players must have different colours");
    }
}
