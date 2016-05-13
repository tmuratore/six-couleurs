package edu.isep.sixcolors.model;

import java.util.Random;

/**
 * Colors
 * Available colors :
 * <ul>
 * 	<li>Blue</li>
 * 	<li>Green</li>
 * 	<li>Yellow</li>
 * 	<li>Orange</li>
 * 	<li>Violet</li>
 * </ul>
 */
public enum Color {
	Blue('B'),
	Green('G'),
	Red('R'),
	Yellow('Y'),
	Orange('O'),
	Violet('V');
	
	private static Random random = new Random();
	
	/**
	 * First letter of the color :
	 */
	private char initial;
	
	private Color(char initial) {
		this.setInitial(initial);
	}
	
	public static Color random() {
		return Color.values()[Color.random.nextInt(Color.values().length)];
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
		
}
