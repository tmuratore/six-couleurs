package edu.isep.sixcolors.model;


/**
 * Tile model : Represents a square of the board
 */
public class Tile {
	
	/**
	 * Colors of the square
	 * TODO import java colors and document the colors in CLI display
	 */
	private Colors color;

	/**
	 * @return the color
	 */
	public Colors getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Colors color) {
		this.color = color;
	}
	
}
