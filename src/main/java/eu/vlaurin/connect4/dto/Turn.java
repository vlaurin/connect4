package eu.vlaurin.connect4.dto;

import java.io.Serializable;

/**
 * @author vlaurin
 * @since 0.0.0
 */
public class Turn implements Serializable {

    private Integer column;

    public Turn() {
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }
}
