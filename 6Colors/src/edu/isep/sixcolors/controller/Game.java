package edu.isep.sixcolors.controller;
import edu.isep.sixcolors.model.*;

/**
 * Game controller : Main controller of the game
 * Manages inputs and outputs to and from the view
 */
public class Game {
	/**
	 * Array representing the players playing the current game
	 */
	private Player[] players;
	
	/**
	 * Get a specific player from its id
	 * @param id
	 * @return Player
	 */
	public Player getPlayer(int id) {
		// TODO check if the given id belongs to the players array keys range
		return players[id];
	}
	
	/**
	 * Get all the players of the current game
	 * @return Player[]
	 */
	public Player[] getPlayers() {
		return this.players;
	}
}
