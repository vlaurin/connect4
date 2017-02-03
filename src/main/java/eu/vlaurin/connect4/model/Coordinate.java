package eu.vlaurin.connect4.model;

import java.util.Objects;

/**
 * @author vlaurin
 * @since 0.0.0
 */
public class Coordinate {
    private final Integer x;
    private final Integer y;

    public Coordinate(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return Objects.equals(x, that.x) &&
                Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public static Coordinate[] verticalConnectionCoordinates(Coordinate origin) {
        final Coordinate[] coords = new Coordinate[4];

        for (int i = 0; i < 4; i++) {
            coords[i] = new Coordinate(origin.getX(), origin.getY() + i);
        }

        return coords;
    }

    public static Coordinate[] horizontalConnectionCoordinates(Coordinate origin) {
        final Coordinate[] coords = new Coordinate[4];

        for (int i = 0; i < 4; i++) {
            coords[i] = new Coordinate(origin.getX() + i, origin.getY());
        }

        return coords;
    }

    public static Coordinate[] forwardDiagonalConnectionCoordinates(Coordinate origin) {
        final Coordinate[] coords = new Coordinate[4];

        for (int i = 0; i < 4; i++) {
            coords[i] = new Coordinate(origin.getX() + i, origin.getY() + i);
        }

        return coords;
    }

    public static Coordinate[] backwardDiagonalConnectionCoordinates(Coordinate origin) {
        final Coordinate[] coords = new Coordinate[4];

        for (int i = 0; i < 4; i++) {
            coords[i] = new Coordinate(origin.getX() - i, origin.getY() + i);
        }

        return coords;
    }
}
