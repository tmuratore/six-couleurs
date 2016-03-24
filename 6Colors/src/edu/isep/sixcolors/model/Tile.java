package edu.isep.sixcolors.model;


/**
 * Tile model : Represents a square of the board
 */
public class Tile {
	
	public Tile(){
	}
	
	/**
	 * Constructor taking the initial color of the tile
	 * @param color element of Colors enum
	 */
	public Tile(Colors color) {
		this.color = color;
	}
	
	private Player owner;
	
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

	/**
	 * @return the tile owner
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	
}
