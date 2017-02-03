package eu.vlaurin.connect4.exception;

/**
 * @author vlaurin
 * @since 0.0.0
 */
public class UnauthorizedPlayerException extends Connect4Exception {
    public UnauthorizedPlayerException() {
        super("Player unauthorized");
    }
}
