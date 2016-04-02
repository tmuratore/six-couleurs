package edu.isep.sixcolors;
import edu.isep.sixcolors.controller.*;
import edu.isep.sixcolors.model.*;
import edu.isep.sixcolors.view.*;

/**
 * TODO annotate
 */
public class SixColors {
	
	/**
	 * Main method of the game (currently)
	 * @param args
	 */
	public static void main(String[] args) {
		
		Game game = new Game();
		
		String name;
		for(int i = 0; i < game.getPlayers().length; i++) {
			name = Console.promptPlayerName(i + 1);
			game.getPlayer(i).setName(name);
		}
		
		Board board = new Board(4);
		
		board.getTile(0, 0).setOwner(game.getPlayer(0));
		board.getTile(3, 3).setOwner(game.getPlayer(1));
		
		// TODO let players choose their colors
		// TODO allow for more than 2 players
		// TODO enclose this code inside a loop to loop between the two players' turns
		
		Console.showBoard(board);
		
		
	}

}
