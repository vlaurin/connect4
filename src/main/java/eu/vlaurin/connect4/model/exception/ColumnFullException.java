package eu.vlaurin.connect4.model.exception;

/**
 * @author vlaurin
 * @since 0.0.0
 */
public class ColumnFullException extends RuntimeException {
    public ColumnFullException() {
        super("Cannot play in full column");
    }
}
