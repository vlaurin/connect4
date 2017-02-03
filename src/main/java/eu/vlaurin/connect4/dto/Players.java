package eu.vlaurin.connect4.dto;

import eu.vlaurin.connect4.model.DiscColour;

import java.io.Serializable;

/**
 * @author vlaurin
 * @since 0.0.0
 */
public class Players implements Serializable {
    private String username1;
    private DiscColour colour1;
    private String username2;
    private DiscColour colour2;

    public Players() {
    }

    public String getUsername1() {
        return username1;
    }

    public void setUsername1(String username1) {
        this.username1 = username1;
    }

    public DiscColour getColour1() {
        return colour1;
    }

    public void setColour1(DiscColour colour1) {
        this.colour1 = colour1;
    }

    public String getUsername2() {
        return username2;
    }

    public void setUsername2(String username2) {
        this.username2 = username2;
    }

    public DiscColour getColour2() {
        return colour2;
    }

    public void setColour2(DiscColour colour2) {
        this.colour2 = colour2;
    }
}
