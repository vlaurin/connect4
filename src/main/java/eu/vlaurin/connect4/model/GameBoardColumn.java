package eu.vlaurin.connect4.model;

import eu.vlaurin.connect4.model.exception.ColumnFullException;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author vlaurin
 * @since 0.0.0
 */
public class GameBoardColumn {
    public static final Integer ROW_COUNT = 6;

    private final DiscColour[] rows;
    private Integer length;

    public GameBoardColumn() {
        rows = new DiscColour[6];
        length = 0;
    }

    public GameBoardColumn(DiscColour[] rows, Integer length) {
        this.rows = rows;
        this.length = length;
    }

    public void drop(DiscColour colour) {
        if (ROW_COUNT.equals(length)) {
            throw new ColumnFullException();
        }

        rows[length++] = colour;
    }

    public DiscColour getRow(Integer row) {
        if (row < 0 || row >= ROW_COUNT) {
            return null;
        }

        return rows[row];
    }

    public DiscColour[] getRows() {
        return rows;
    }

    public Integer getLength() {
        return length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameBoardColumn that = (GameBoardColumn) o;
        return Arrays.equals(rows, that.rows) &&
                Objects.equals(length, that.length);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rows, length);
    }
}
