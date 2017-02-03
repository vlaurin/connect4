package eu.vlaurin.connect4.model.exception;

import eu.vlaurin.connect4.exception.Connect4Exception;

/**
 * @author vlaurin
 * @since 0.0.0
 */
public class ColumnOutOfBoundsException extends Connect4Exception {
    public ColumnOutOfBoundsException() {
        super("Column is out of bound");
    }
}
