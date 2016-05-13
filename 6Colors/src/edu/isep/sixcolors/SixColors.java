package edu.isep.sixcolors;
import edu.isep.sixcolors.controller.*;

// TODO factorize random color generation (duplicated in this and model.Board)

/**
 * Main class of the game : manages inputs and chooses the right controller to launch
 */
public class SixColors {
	
	/**
	 * Main method of the game (currently)
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Creating the game controller :
		Game game = new Game();
		
		game.init();
		
		game.start();
		
	}

}
