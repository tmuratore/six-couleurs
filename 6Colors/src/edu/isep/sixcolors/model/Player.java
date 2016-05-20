package edu.isep.sixcolors.model;

/**
 * Player model : Represents a player of the current game
 * TODO abstract to be extended (local player OR AI OR distant player)  
 */
public class Player {

	private String name;
	private TileColor tileColor;
	private TileColor previousTileColor;
	private int[] startingTileCoords = new int[2];
	private int points;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TileColor getTileColor() {
		return tileColor;
	}

	public void setTileColor(TileColor controlledTileColor) {
		setPreviousTileColor(this.tileColor);
		this.tileColor = controlledTileColor;
	}

	public TileColor getPreviousTileColor() {
		return previousTileColor;
	}

	private void setPreviousTileColor(TileColor previousTileColor) {
		this.previousTileColor = previousTileColor;
	}

	public int[] getStartingTileCoords() {
		return startingTileCoords;
	}

	public void setStartingTileCoords(int[] startingTileCoords) {
		this.startingTileCoords = startingTileCoords;
	}

	public void setStartingTileCoords(int i, int j) {
		this.startingTileCoords = new int[]{i, j};
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public void addPoints() {
		this.points++;
	}

}
