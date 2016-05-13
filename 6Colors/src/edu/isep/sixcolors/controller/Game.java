package edu.isep.sixcolors.controller;
import java.util.Random;

import edu.isep.sixcolors.model.*;
import edu.isep.sixcolors.view.Console;

/**
 * Game controller : Controller implementing the game engine
 * Manages inputs and outputs to and from the view
 */
public class Game {
	/**
	 * Array representing the players playing the current game
	 */
	private Player[] players;
	private int currentPlayerId;
	private Random random = new Random();
	private Board board;

	
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
	 * TODO annotate
	 */
	public void init() {
		String name;
		for(int i = 0; i < getPlayers().length; i++) {
			name = Console.promptPlayerName(i + 1);
			getPlayer(i).setName(name);
		}
		
		this.board = new Board(4);
		
		board.getTile(0, 0).setOwner(getPlayer(0));
		
		while(board.getTile(3, 3).getColor() == board.getTile(0, 0).getColor()) {
			board.getTile(3, 3).setColor(Color.values()[random.nextInt(Color.values().length)]);
		}
		
		board.getTile(3, 3).setOwner(getPlayer(1));
		
		// Setting current and previous colors of the players :
		getPlayer(0).setColor(board.getTile(0, 0).getColor());
		getPlayer(0).setPreviousColor(board.getTile(0, 0).getColor());	
		getPlayer(1).setColor(board.getTile(3, 3).getColor());
		getPlayer(1).setPreviousColor(board.getTile(3, 3).getColor());
		
		// Updating board to give the players ownership of the tiles of their colors next to their starting point.
		board.update(0, 0, getPlayer(0));
		board.update(3, 3, getPlayer(1));
	}
	
	public void start() {
		while(true) {
			Player currentPlayer = getCurrentPlayer();
			System.out.println("It's " + currentPlayer.getName() + "'s turn !");
			System.out.println("Your current color : "+currentPlayer.getColor().name());
			
			Console.showBoard(board);
			
			Color chosenColor = Console.promptColorChoice();
			
			// TODO refactor those checks smartly :
			while (chosenColor == currentPlayer.getColor()) {
				System.out.println("You already control this color");
				chosenColor = Console.promptColorChoice();
			}
			
			boolean err = true;
			while (err) {  
				err = false;
				for(Player player: getPlayers()) {
					if(player.getColor() == chosenColor) {
						err = true;
						System.out.println(player.getName() + " already controls this color. Choose another one.");
						chosenColor = Console.promptColorChoice();
					}
				}
			}
			
			System.out.println("Chosen color : "+chosenColor.name());
			
			// updating previous and current colors :
			// TODO setPrevious private and called in setColor ?
			currentPlayer.setPreviousColor(currentPlayer.getColor());
			currentPlayer.setColor(chosenColor);
			
			// TODO Player.originTileCoordinates
			int tileX, tileY;
			
			if(getCurrentPlayerId() == 0) {
				tileX = 0;
				tileY = 0;
			}
			else {
				tileX = 3;
				tileY = 3;
			}
			
			board.update(tileY, tileX, currentPlayer);
			
			// set the current player to the next player :
			nextPlayer();
		}
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
	
	public Player getCurrentPlayer() {
		return this.players[this.currentPlayerId];
	}
	
	public int getCurrentPlayerId() {
		return this.currentPlayerId;
	}
	
	
	/**
	 * Next player up !
	 */
	public void nextPlayer() {
		if(this.currentPlayerId == this.players.length - 1) {
			this.currentPlayerId = 0;
		}
		else {
			this.currentPlayerId += 1;
		}
	}
}
