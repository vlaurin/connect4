package eu.vlaurin.connect4.model;

import java.util.Objects;

/**
 * @author vlaurin
 * @since 0.0.0
 */
public class Player {

    private final String username;
    private final DiscColour colour;

    public Player(String username, DiscColour colour) {
        this.username = username;
        this.colour = colour;
    }

    public String getUsername() {
        return username;
    }

    public DiscColour getColour() {
        return colour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(username, player.username) &&
                colour == player.colour;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, colour);
    }
}
