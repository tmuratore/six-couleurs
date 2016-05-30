package edu.isep.sixcolors.model.entity;

import java.awt.Color;
import java.io.Serializable;
import java.util.Random;

/**
 * TileColor, the enum. of the available colors for the board.
 * Implements Serializable to the game can be saved
 * Available colors :
 * <ul>
 * 	<li>Blue</li>
 * 	<li>Green</li>
 * 	<li>Red</li>
 * 	<li>Yellow</li>
 * 	<li>Orange</li>
 * 	<li>Violet</li>
 * </ul>
 */
public enum TileColor implements Serializable {
    Blue('B', Color.BLUE),
    Green('G', new Color(0,128,0)),
    Red('R', Color.RED),
    Yellow('Y', Color.YELLOW),
    Orange('O', Color.ORANGE),
    Violet('V', new Color(128,0,128));

    private static final Random random = new Random();

    private char initial;
    private Color color;

    TileColor(char initial, Color color) {
        this.setInitial(initial);
        this.setColor(color);
    }

    /**
     * Returns a random TileColor from the enum
     * @return a random TileColor
     */
    public static TileColor random() {
        return TileColor.values()[TileColor.random.nextInt(TileColor.values().length)];
    }


    /**
     * Get the first letter of the color
     * Necessary for Console support
     * @return char : first letter
     */
    public char getInitial() {
        return initial;
    }

    /**
     * Set the first letter of the color
     * Necessary for Console support
     */
    private void setInitial(char initial) {
        this.initial = initial;
    }

    /**
     * Get the Color of the TileColor, for view purposes
     * @return the TileColors Color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Set the Color of the TileColor, for view purposes
     */
    private void setColor(Color color) {
        this.color = color;
    }

    /**
     * Allow to test from a partial input if the TileColor exists
     * Useful for Console Support
     * @param test the String to be tested against the possible colors
     * @return true if it's recognized, false otherwise
     */
    public static boolean contains(String test) {

        for (TileColor c : TileColor.values()) {
            if (c.name().equals(test)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Parses an input String to a TileColor's name or sends an Exception
     * @param name the name to parse
     * @return the parsed TileColor
     * @throws Exception when the Color is not found
     */
    public static TileColor parseTileColor(String name) throws Exception {
        for (TileColor c: TileColor.values()) {
            if (c.name().equals(name)) {
                return c;
            }
        }
        throw new Exception("Color not found");
    }
}
