package edu.isep.sixcolors.model;

/**
 * Player model : Represents a player of the current game
 * Contains data about the player : name, current color...
 */
public class Player {
	private String name;
	
	/**
	 * Get the name of the player
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set player's name
	 * 
	 * @param name
	 * @throws 
	 */
	public void setName(String name) {
		// TODO check if a string has been given, throw exception otherwise
		this.name = name;
	}
}
