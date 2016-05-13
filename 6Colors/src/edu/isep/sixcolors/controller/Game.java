package edu.isep.sixcolors.controller;

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
	private Board board;

	
	/**
	 * Create a new game with 2 players
	 * 
	 * @param players Number of players
	 */
	public Game() {
		
		int width = Console.promptBoardWidth();
		int nbPlayers = Console.promptNumberPlayers();
		
		// Creating board and players :
		this.board = new Board(width);
		this.players = new Player[nbPlayers];
		
		for(int i = 0; i < this.players.length; i++) {
			this.players[i] = new Player();
		}
		
		// Setting the players' starting tiles coordinates according to the number of players :
		if(players.length == 2) {
			players[0].setStartingTileCoords(0,0);
			players[1].setStartingTileCoords(board.getWidth()-1,board.getWidth()-1);
		}
		else {
			for(int i = 0; i < this.players.length; i++) {
				// Computing starting tile abscissa and ordinate using the player's id :
				int x = i%2 == 0 ? 0 : board.getWidth() - 1;
				int y = (i>0 && i<3) ? board.getWidth() - 1 : 0;
				players[i].setStartingTileCoords(x, y);
			}
		}
		
		Color[] ownedColors = new Color[players.length];
		
		// Preventing two players from getting the same initial color
		// TODO : correct bug (GitHub issue)
		// other method : set the colors of their starting tiles instead of changing until all different ?
		for(int i = 0; i<players.length; i++) {
		
			boolean colorAlreadyOwned = false;
			
			Color color = board.getTile(getPlayer(i).getStartingTileCoords()).getColor();
			
			for(int j=0; j<i; j++) {
				if(color == ownedColors[j]) {
					colorAlreadyOwned = true;
				}
			}
			
			if(colorAlreadyOwned) {
				board.getTile(players[i].getStartingTileCoords())
					 .setColor(Color.random());
			}
			
			if(i == players.length - 1 && !colorAlreadyOwned) {
			}
		}
		
		//Setting ownership of the starting tiles and their neighbors of the same color :
		for(Player player: players) {
			int[] startingTile = player.getStartingTileCoords();
			board.getTile(startingTile).setOwner(player);
			board.update(startingTile[0], startingTile[1], player);
		}
		
		// First to play is player #0 :
		this.currentPlayerId = 0;
		
	}
	
	/**
	 * Initialises the game
	 * 
	 */
	public void init() {
		String name;
		for(int i = 0; i < getPlayers().length; i++) {
			name = Console.promptPlayerName(i + 1);
			getPlayer(i).setName(name);
		}
		
		// Setting current and previous colors of the players :
		for(Player player: getPlayers()) {
			int[] startingTile = player.getStartingTileCoords();
			Color color = board.getTile(startingTile[0], startingTile[1]).getColor();
			player.setColor(color);
			player.setPreviousColor(color);	
			
			// Updating board to give the players ownership of the tiles of their colors next to their starting point.
			board.update(startingTile[0], startingTile[1], player);
		}				
	}
	
	public void start() {
		while(true) {
			Player currentPlayer = getCurrentPlayer();
			System.out.println("It's " + currentPlayer.getName() + "'s turn !");
			System.out.println("Your current color : "+currentPlayer.getColor().name());
			
			Console.showBoard(board);
			
			Color chosenColor = Console.promptColorChoice();
			
			boolean err = true;
			while (err) {
				err = false;
				for(Player player: getPlayers()) {
					if(player.getColor() == chosenColor) {
						err = true;
						if(player == currentPlayer) {
							System.out.println("You already control this color");
						}
						else {
							System.out.println(player.getName() + " already controls this color. Choose another one.");
						}
						chosenColor = Console.promptColorChoice();
					}
				}
			}
			
			System.out.println("Chosen color : "+chosenColor.name());
			
			// updating previous and current colors :
			// TODO setPrevious private and called in setColor ?
			currentPlayer.setPreviousColor(currentPlayer.getColor());
			currentPlayer.setColor(chosenColor);
			
			// updating the board
			int[] startingTile = currentPlayer.getStartingTileCoords();
			board.update(startingTile[0], startingTile[1], currentPlayer);
			
			// Next player up !
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
