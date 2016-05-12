package edu.isep.sixcolors;
import edu.isep.sixcolors.controller.*;
import edu.isep.sixcolors.model.*;
import edu.isep.sixcolors.view.*;

import java.util.Random;

// TODO factorize random color generation (duplicated in this and model.Board)

/**
 * Main class of the game : manages inputs and 
 */
public class SixColors {
	
	/**
	 * Main method of the game (currently)
	 * @param args
	 */
	public static void main(String[] args) {
		
		Random random = new Random();
		Game game = new Game();
		
		String name;
		for(int i = 0; i < game.getPlayers().length; i++) {
			name = Console.promptPlayerName(i + 1);
			game.getPlayer(i).setName(name);
		}
		
		// TODO store board width as an independant parameter
		
		Board board = new Board(4);
		
		board.getTile(0, 0).setOwner(game.getPlayer(0));
		
		while(board.getTile(3, 3).getColor() == board.getTile(0, 0).getColor()) {
			board.getTile(3, 3).setColor(Color.values()[random.nextInt(Color.values().length)]);
		}
		
		board.getTile(3, 3).setOwner(game.getPlayer(1));
		
		game.getPlayer(0).setColor(board.getTile(0, 0).getColor());
		game.getPlayer(0).setPreviousColor(board.getTile(0, 0).getColor());	
		game.getPlayer(1).setColor(board.getTile(3, 3).getColor());
		game.getPlayer(1).setPreviousColor(board.getTile(3, 3).getColor());
		
		
		while(true) {
			Player currentPlayer = game.getCurrentPlayer();
			System.out.println("It's " + currentPlayer.getName() + "'s turn !");
			System.out.println("Your current color : "+currentPlayer.getColor().name());
			
			Console.showBoard(board);
			
			Color chosenColor = Console.promptColorChoice();
			
			// TODO refactor those checks smartly :
			while (chosenColor == currentPlayer.getColor()) {
				System.out.println("You already control this color");
				chosenColor = Console.promptColorChoice();
			}
			
			for(Player player: game.getPlayers()) {
				if(player.getColor() == chosenColor) {
					System.out.println(player.getName() + " already controls this color. Choose another one.");
					chosenColor = Console.promptColorChoice();
				}
			}
			
			System.out.println("Chosen color : "+chosenColor.name());
			
			// updating previous and current colors :
			// TODO setPrevious private and called in setColor ?
			currentPlayer.setPreviousColor(currentPlayer.getColor());
			currentPlayer.setColor(chosenColor);
			
			// TODO Player.originTileCoordinates
			int tileX, tileY;
			
			if(game.getCurrentPlayerId() == 0) {
				tileX = 0;
				tileY = 0;
			}
			else {
				tileX = 3;
				tileY = 3;
			}
			
			board.update(tileY, tileX, currentPlayer);
			
			// set the current player to the next player :
			game.nextPlayer();
		}
		
	}

}
