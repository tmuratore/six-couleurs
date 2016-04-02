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
	private int currentPlayerId;
	
	/**
	 * Create a new game with 2 players
	 * 
	 * @param players Number of players
	 */
	public Game() {
		
		this.players = new Player[2];
		
		for(int i = 0; i < this.players.length; i++) {
			this.players[i] = new Player();
		}
		
		this.currentPlayerId = 0;
		
	}
	
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
	
	/**
	 * Get the current player
	 * @return Player Current player
	 */
	public Player getCurrentPlayer() {
		return this.players[this.currentPlayerId];
	}
	
	public void nextPlayer() {
		if(this.currentPlayerId == this.players.length - 1) {
			this.currentPlayerId = 0;
		}
		else {
			this.currentPlayerId += 1;
		}
	}
}
