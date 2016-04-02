package edu.isep.sixcolors.model;

import java.util.Random;

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
		Random random = new Random();
		
		//Random Map Generation from Colors enum values
		
		for(int i = 0; i<width; i++) {
			for(int j = 0; j<width; j++) {
				tiles[i][j] = new Tile(Colors.values()[random.nextInt(Colors.values().length)]);
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
		return this.tiles[line][row];
	}
	
	public Tile[][] getTiles() {
		return this.tiles;
	}

}
