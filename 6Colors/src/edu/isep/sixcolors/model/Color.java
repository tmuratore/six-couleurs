package edu.isep.sixcolors.model;

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
	
	/**
	 * First letter of the color :
	 */
	private char initial;
	
	private Color(char initial) {
		this.setInitial(initial);
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
