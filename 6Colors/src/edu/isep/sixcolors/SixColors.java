package edu.isep.sixcolors;
import javax.swing.JFrame;

import edu.isep.sixcolors.controller.Game;
import edu.isep.sixcolors.view.*;
import edu.isep.sixcolors.view.Output;
import edu.isep.sixcolors.view.graphic.*;

/**
 * Main class of the game : manages inputs and chooses the right controller to launch
 */
public class SixColors {

	/**
	 * Main method of the game (currently)
	 *
	 * @param args
	 */
	public static void main(String[] args) {


		// TODO make the output type be decided on the content of main's args
		// Creates the output
		// Output output = new Console();
		Output output = new Graphic();

		// Creating the game controller :
		Game game = new Game(output);

		game.init();

		game.play();

	}

}
