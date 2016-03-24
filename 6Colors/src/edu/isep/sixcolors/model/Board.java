package edu.isep.sixcolors.model;

/**
 * Board model : represents the board of the game.
 */
public class Board {
	
	/**
	 * Initialises a square board
	 * TODO decide if the board is square or if it can be rectangular
	 * @param line
	 */
	public Board(int width) {
		this.tiles = new Tile[width][width];
		
		//TODO conceive and develop map generation
		
		for(int i = 0; i<width; i++) {
			for(int j = 0; j<width; j++) {
				tiles[i][j] = new Tile(Colors.Blue);
			}
		}
	}
	
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
