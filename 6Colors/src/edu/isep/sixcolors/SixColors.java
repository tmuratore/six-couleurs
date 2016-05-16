package edu.isep.sixcolors;
import edu.isep.sixcolors.controller.*;
import edu.isep.sixcolors.view.*;

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

		// TODO make the output type be decided on the content of main's args
		// Creates the output
		Output output = new Console();
		
		// Creating the game controller :
		Game game = new Game(output);

		game.init();
		
		game.play();

	}

}
