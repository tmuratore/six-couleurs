package edu.isep.sixcolors.model;

/**
 * Player model : Represents a player of the current game
 * TODO abstract to be extended (local player OR AI OR distant player)  
 */
public class Player {
	private String name;
	
	private Color controlledColor;
	
	/**
	 * Get the player's name
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
		this.name = name;
	}

	/**
	 * @return the controlled color
	 */
	public Color getControlledColor() {
		return controlledColor;
	}

	/**
	 * @param controlledColor the controlled color to set
	 */
	public void setControlledColor(Color controlledColor) {
		this.controlledColor = controlledColor;
	}
}
