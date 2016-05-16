package edu.isep.sixcolors.model;


/**
 * Tile model : Represents a square of the board
 */
public class Tile {

	public Tile() {
	}

	/**
	 * Constructor taking the initial color of the tile
	 *
	 * @param color element of Colors enum
	 */
	public Tile(Color color) {
		this.color = color;
	}

	private Player owner;

	/**
	 * Colors of the square
	 */
	private Color color;

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
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
