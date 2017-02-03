package eu.vlaurin.connect4.model;

import org.junit.Test;

import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.arrayWithSize;
import static org.junit.Assert.assertThat;

/**
 * @author vlaurin
 * @since 0.0.0
 */
public class CoordinateTest {

    @Test
    public void testVerticalConnectionCoordinates() {
        final Coordinate origin = new Coordinate(0,0);
        final Coordinate[] coords = Coordinate.verticalConnectionCoordinates(origin);

        assertThat(coords, arrayWithSize(4));
        assertThat(coords, arrayContaining(origin, new Coordinate(0, 1), new Coordinate(0, 2), new Coordinate(0, 3)));
    }

    @Test
    public void testHorizontalConnectionCoordinates() {
        final Coordinate origin = new Coordinate(0,0);
        final Coordinate[] coords = Coordinate.horizontalConnectionCoordinates(origin);

        assertThat(coords, arrayWithSize(4));
        assertThat(coords, arrayContaining(origin, new Coordinate(1, 0), new Coordinate(2, 0), new Coordinate(3, 0)));
    }

    @Test
    public void testForwardDiagonalConnectionCoordinates() {
        final Coordinate origin = new Coordinate(0,0);
        final Coordinate[] coords = Coordinate.forwardDiagonalConnectionCoordinates(origin);

        assertThat(coords, arrayWithSize(4));
        assertThat(coords, arrayContaining(origin, new Coordinate(1, 1), new Coordinate(2, 2), new Coordinate(3, 3)));
    }

    @Test
    public void testBackwardDiagonalConnectionCoordinates() {
        final Coordinate origin = new Coordinate(3,0);
        final Coordinate[] coords = Coordinate.backwardDiagonalConnectionCoordinates(origin);

        assertThat(coords, arrayWithSize(4));
        assertThat(coords, arrayContaining(origin, new Coordinate(2, 1), new Coordinate(1, 2), new Coordinate(0, 3)));
    }


}