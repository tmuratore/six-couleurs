package edu.isep.sixcolors.model;

import java.awt.Color;
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
public enum GameColor {
	Blue('B', Color.BLUE),
	Green('G', Color.GREEN),
	Red('R', Color.RED),
	Yellow('Y', Color.YELLOW),
	Orange('O', Color.ORANGE),
	Magenta('M', Color.MAGENTA);
	
	private static Random random = new Random();
	
	/**
	 * First letter of the color :
	 */
	private char initial;
	private Color color;
	
	GameColor(char initial, Color blue) {
		this.setInitial(initial);
	}
	
	public static GameColor random() {
		return GameColor.values()[GameColor.random.nextInt(GameColor.values().length)];
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
}
