package eu.vlaurin.connect4.model.exception;

/**
 * @author vlaurin
 * @since 0.0.0
 */
public class ColumnOutOfBoundsException extends RuntimeException {
    public ColumnOutOfBoundsException() {
        super("Column is out of bound");
    }
}
