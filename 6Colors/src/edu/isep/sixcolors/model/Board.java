package edu.isep.sixcolors.model;

/**
 * Board model : represents the board of the game.
 */
public class Board {
	
	/**
	 * Squares of the board
	 */
	private Tile[][] tiles;
	
	/**
	 * Get a tile of the board
	 * @param line
	 * @param row
	 */
	public Tile getTile(int line, int row) {
		return tiles[line][row];
	}

}
