package edu.isep.sixcolors.controller;

import edu.isep.sixcolors.model.*;
import edu.isep.sixcolors.view.Console;
import edu.isep.sixcolors.view.Output;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

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
	private Output output;

	/**
	 * Creates a new game
	 *
	 * @param output the chosen output
	 */
	public Game(Output output) {

		this.output = output;

		// TODO : Find a real max size for the Board
		int width = output.promptFramedInt("Size of the board : ", 5, 50);
		// TODO : Modify the max value of players depending on the shape of the Tiles/Board ?
		int nbPlayers = output.promptFramedInt("Number of players : ", 2, 4);

		// Creating board and players :
		this.board = new Board(width);
		this.players = new Player[nbPlayers];

		for (int i = 0; i < this.players.length; i++) {
			this.players[i] = new Player();
		}

		// Setting the players' starting tiles coordinates according to the number of players :
		if (players.length == 2) {
			players[0].setStartingTileCoords(0, 0);
			players[1].setStartingTileCoords(board.getWidth() - 1, board.getWidth() - 1);
		} else {
			for (int i = 0; i < this.players.length; i++) {
				// Computing starting tile abscissa and ordinate using the player's id :
				int x = i % 2 == 0 ? 0 : board.getWidth() - 1;
				int y = (i > 0 && i < 3) ? board.getWidth() - 1 : 0;
				players[i].setStartingTileCoords(x, y);
			}
		}

		ArrayList<Color> availableColors = new ArrayList<Color>(Arrays.asList(Color.values()));
		Random randomGen = new Random();


		// Preventing two players from getting the same initial color
		for (int i = 0; i < players.length; i++) {

			Color color = board.getTile(getPlayer(i).getStartingTileCoords()).getColor();

			if (availableColors.contains(color)) {
				// If the color is available, keep it and remove it from the available colors.
				availableColors.remove(color);
			} else {
				// If current color is not available, pick a random one from the available, set it then remove this color from the available.
				int randomIndex = randomGen.nextInt(availableColors.size());
				Color randomColor = availableColors.get(randomIndex);
				board.getTile(players[i].getStartingTileCoords()).setColor(randomColor);
				availableColors.remove(randomColor);
			}

		}

		//Setting ownership of the starting tiles and their neighbors of the same color :
		for (Player player : players) {
			int[] startingTile = player.getStartingTileCoords();
			board.getTile(startingTile).setOwner(player);
			board.update(startingTile[0], startingTile[1], player);
		}

		// First to play is player #0 :
		this.currentPlayerId = 0;

	}

	/**
	 * Initialises the game
	 */
	public void init() {
		String name;
		for (int i = 0; i < getPlayers().length; i++) {
			name = output.promptString("Player " + (i + 1) + ", choose your name : ");
			getPlayer(i).setName(name);
		}

		// Setting current, previous colors and initial points of the players :
		for (Player player : getPlayers()) {
			int[] startingTile = player.getStartingTileCoords();
			Color color = board.getTile(startingTile[0], startingTile[1]).getColor();
			player.setColor(color);
			player.setPreviousColor(color);
			player.setPoints(1);

			// Updating board to give the players ownership of the tiles of their colors next to their starting point.
			board.update(startingTile[0], startingTile[1], player);
		}
	}

	/**
	 * Starts the game
	 */
	// TODO Make start() generic of any interface, it does not (really) respect the MVC pattern
	public void play() {
		while (true) {
			Player currentPlayer = getCurrentPlayer();

			// Print game status
			output.printGameStatus(board, currentPlayer);


			// Prompt Color Choice
			Color chosenColor = output.promptColorChoice();

			boolean err = true;
			while (err) {
				err = false;
				for (Player player : getPlayers()) {
					if (player.getColor() == chosenColor) {
						err = true;
						if (player == currentPlayer) {
							output.printGameErrorMessage("You already control this color");
						} else {
							output.printGameErrorMessage(player.getName() + " already controls this color. Choose another one.");
						}
						chosenColor = output.promptColorChoice();
					}
				}
			}

			output.printInfoMessage("Chosen color : " + chosenColor.name());

			// updating previous and current colors :
			// TODO setPrevious private and called in setColor ?
			currentPlayer.setPreviousColor(currentPlayer.getColor());
			currentPlayer.setColor(chosenColor);

			// updating the board
			int[] startingTile = currentPlayer.getStartingTileCoords();
			board.update(startingTile[0], startingTile[1], currentPlayer);

			//Test for end game (a winner)
			Player winner = checkForWinner();
			if (winner != null) {
				output.printInfoMessage("The game is over, the winner is " + winner.getName() + " !");
				break;
			}

			// Next player up !
			nextPlayer();
		}
	}

	/**
	 * Get a specific player from its id
	 *
	 * @param id
	 * @return Player
	 */
	public Player getPlayer(int id) {
		// TODO check if the given id belongs to the players array keys range
		return players[id];
	}

	/**
	 * Get all the players of the current game
	 *
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
		if (this.currentPlayerId == this.players.length - 1) {
			this.currentPlayerId = 0;
		} else {
			this.currentPlayerId += 1;
		}
	}

	/**
	 * Returns the winner or null if there is none yet
	 *
	 * @return Player winner or null;
	 */

	@Nullable
	private Player checkForWinner() {
		int winPoints = (int) Math.ceil(Math.pow(board.getWidth(), 2) / 2);
		int totalPoints = 0;
		int maxPoints = 0;
		int playerPoints;
		Player winner = this.getCurrentPlayer();
		for (Player player : getPlayers()) {
			playerPoints = player.getPoints();
			if (playerPoints > maxPoints) {
				maxPoints = playerPoints;
				winner = player;
			}
			totalPoints += player.getPoints();
		}
		if (this.getCurrentPlayer().getPoints() > winPoints || totalPoints == Math.pow(board.getWidth(), 2)) {
			return winner;
		}
		return null;

	}
}
