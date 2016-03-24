package edu.isep.sixcolors;
import edu.isep.sixcolors.model.*;

/**
 * TODO annotate
 */
public class SixColors {
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		int width = 4;
		
		Board board = new Board(width);
		
		for(int i = 0; i<width; i++) {
			for(int j = 0; j<width; j++) {
				System.out.print(board.getTile(i, j).getColor().getInitial() + " ");
			}
			System.out.println();
		}
		
		
		
	}

}
