package edu.isep.sixcolors.controller;
import edu.isep.sixcolors.model.*;

import java.util.ArrayList;

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
	
	/**
	 * Updates the board for a players turn
	 * DANGEROUS DO NOT USE POSSIBLE GAME CORRUPTION
	 * @param currentPlayer
	 * @param board
	 */
	private void updateBoard(Player currentPlayer, Board board){
		for(int i = 0; i< board.getTiles().length ; i++) {
			for(int j = 0; j<board.getTiles().length; j++) {
				//Tile by tile
				Tile tile = board.getTile(i, j);
				
				//Modifications are only made from tiles owned by currentPlayer
				if (tile.getOwner() == currentPlayer){
					
					//Updates the color of an owned tile if necessary
					if (tile.getColor() != currentPlayer.getControlledColor()) tile.setColor(currentPlayer.getControlledColor());
					
					//Updates the neighboring tiles of the previously owned tile
					
					// TODO Neighboring cells can i think be re-analyzed as tiles previously owned.
					if (i != 0) UpdateTile(currentPlayer, board.getTile(i, j-1)); //Left
					if (i != board.getTiles().length) UpdateTile(currentPlayer, board.getTile(i, j+1)); //Right
					if (j != 0) UpdateTile(currentPlayer, board.getTile(i-1, j)); //Up
					if (j != board.getTiles().length) UpdateTile(currentPlayer, board.getTile(i+1, j)); //Down
				}
			}
			
		}
	}
	
	/**
	 * Used in updateBoard to update the owner and color of a tile if it's not already owned and is the correct color
	 * @param currentPlayer
	 * @param tile
	 */
	
	private void UpdateTile(Player currentPlayer, Tile tile){
		if(tile.getOwner() == null && tile.getColor() == currentPlayer.getControlledColor()){ 
			tile.setOwner(currentPlayer);
			tile.setColor(currentPlayer.getControlledColor());
			}
	}
}
