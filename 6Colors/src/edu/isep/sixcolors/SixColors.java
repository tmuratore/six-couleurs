package edu.isep.sixcolors;
import edu.isep.sixcolors.model.*;

/**
 * TODO annotate
 */
public class SixColors {
	
	/**
	 * Main method of the game (currently)
	 * @param args
	 */
	public static void main(String[] args) {
		
		int width = 4;
		
		Board board = new Board(width);
		
		// TODO let players choose their colors
		// TODO allow for more than 2 players
		// TODO enclose this code inside a loop to loop between the two players' turns
		// TODO? refactor the display of the board as an external method ?
		
		for(int i = 0; i<width; i++) {
			for(int j = 0; j<width; j++) {
				Tile tile = board.getTile(i, j);
				String initial = Character.toString(tile.getColor().getInitial());
				if(tile.getOwner() == null) {
					initial = initial.toLowerCase();
					initial.toLowerCase();
				}
				System.out.print(initial + " ");
			}
			System.out.println();
		}
		
		
	}

}
