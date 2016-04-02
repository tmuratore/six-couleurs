package edu.isep.sixcolors;
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
		
		Board board = new Board(4);
		
		// TODO let players choose their colors
		// TODO allow for more than 2 players
		// TODO enclose this code inside a loop to loop between the two players' turns
		
		Console.showBoard(board);
		
		
	}

}
