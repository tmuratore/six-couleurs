package edu.isep.sixcolors.model;


/**
 * Tile model : Represents a square of the board
 */
public class Tile {

	public Tile() {
	}

	/**
	 * Constructor taking the initial gameColor of the tile
	 *
	 * @param gameColor element of Colors enum
	 */
	public Tile(GameColor gameColor) {
		this.gameColor = gameColor;
	}

	private Player owner;

	/**
	 * Colors of the square
	 */
	private GameColor gameColor;

	/**
	 * @return the gameColor
	 */
	public GameColor getGameColor() {
		return gameColor;
	}

	/**
	 * @param gameColor the gameColor to set
	 */
	public void setGameColor(GameColor gameColor) {
		this.gameColor = gameColor;
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
