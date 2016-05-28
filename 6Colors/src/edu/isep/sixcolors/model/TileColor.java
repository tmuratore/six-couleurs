package edu.isep.sixcolors.model;

import java.awt.Color;
import java.io.Serializable;
import java.util.Random;

/**
 * Colors
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
    Green('G', Color.GREEN),
    Red('R', Color.RED),
    Yellow('Y', Color.YELLOW),
    Orange('O', Color.ORANGE),
    Magenta('M', Color.PINK);

    private static Random random = new Random();

    /**
     * First letter of the color :
     */
    private char initial;
    private Color color;

    TileColor(char initial, Color color) {
        this.setInitial(initial);
        this.setColor(color);
    }

    public static TileColor random() {
        return TileColor.values()[TileColor.random.nextInt(TileColor.values().length)];
    }


    /**
     * Get the first letter of the color
     * @return char : first letter
     */
    public char getInitial() {
        return initial;
    }

    private void setInitial(char initial) {
        this.initial = initial;
    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public static boolean contains(String test) {

        for (TileColor c : TileColor.values()) {
            if (c.name().equals(test)) {
                return true;
            }
        }

        return false;
    }

    public static TileColor parseTileColor(String name) throws Exception {
        for (TileColor c: TileColor.values()) {
            if (c.name().equals(name)) {
                return c;
            }
        }
        throw new Exception("Color not found");
    }
}
