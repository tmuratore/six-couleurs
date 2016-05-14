package edu.isep.sixcolors.model;

/**
 * Player model : Represents a player of the current game
 * TODO abstract to be extended (local player OR AI OR distant player)  
 */
public class Player {
	
	private String name;
	private Color color;
	private Color previousColor;
	private int[] startingTileCoords = new int[2];
	private int points;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color controlledColor) {
		this.color = controlledColor;
	}

	public Color getPreviousColor() {
		return previousColor;
	}

	public void setPreviousColor(Color previousColor) {
		this.previousColor = previousColor;
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
