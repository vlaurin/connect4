package eu.vlaurin.connect4.model;

import eu.vlaurin.connect4.model.exception.ColumnFullException;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * @author vlaurin
 * @since 0.0.0
 */
public class GameBoardColumnTest {

    private GameBoardColumn column;

    @Before
    public void setUp() {
        column = new GameBoardColumn();
    }

    @Test
    public void allRowsShouldBeNullWhenCreated() {
        assertThat(column.getRow(0), is(nullValue()));
        assertThat(column.getRow(1), is(nullValue()));
        assertThat(column.getRow(2), is(nullValue()));
        assertThat(column.getRow(3), is(nullValue()));
        assertThat(column.getRow(4), is(nullValue()));
        assertThat(column.getRow(5), is(nullValue()));
    }

    @Test
    public void gettingRowOutsideOfColumnShouldReturnNull() {
        assertThat(column.getRow(-1), is(nullValue()));
        assertThat(column.getRow(6), is(nullValue()));
    }

    @Test
    public void droppingAColourShouldAddItToTheBottom() {
        column.drop(DiscColour.RED);
        column.drop(DiscColour.YELLOW);

        assertThat(column.getRow(0), is(DiscColour.RED));
        assertThat(column.getRow(1), is(DiscColour.YELLOW));
        assertThat(column.getRow(2), is(nullValue()));
    }

    @Test(expected = ColumnFullException.class)
    public void droppingColourInFullColumnShouldBeForbidden() {

        for (int i = 0; i < GameBoardColumn.ROW_COUNT; i++) {
            column.drop(DiscColour.RED);
        }

        column.drop(DiscColour.YELLOW);
    }

}