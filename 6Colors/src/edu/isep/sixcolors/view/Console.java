package edu.isep.sixcolors.view;

import edu.isep.sixcolors.model.*;

import java.util.Scanner;

public class Console implements Output {

	static Scanner scan = new Scanner(System.in);

	public void printGameErrorMessage(String message) {
		System.err.println(message);
	}

	public void printInfoMessage(String message) {
		System.out.println(message);
	}

	/**
	 * Display board
	 *
	 * @param board
	 */
	private static void showBoard(Board board) {

		for (int i = 0; i < board.getTiles().length; i++) {
			for (int j = 0; j < board.getTiles().length; j++) {
				Tile tile = board.getTile(i, j);
				String initial = Character.toString(tile.getColor().getInitial());

				if (tile.getOwner() == null) {
					initial = initial.toLowerCase();
				}
				System.out.print(initial + " ");
			}
			System.out.println();
		}
	}


	public void printGameStatus(Board board, Player currentPlayer) {
		System.out.println("It's " + currentPlayer.getName() + "'s turn !");
		System.out.println("You have " + currentPlayer.getPoints() + " points.");
		System.out.println("Your current color : " + currentPlayer.getColor().name());

		Console.showBoard(board);
	}

	/**
	 * Prompts the player to type in a string
	 *
	 * @param whatToAsk The message explaining what must be entered
	 * @return The name typed in by the player
	 */

	public String promptString(String whatToAsk) {
		System.out.println(whatToAsk);
		return scan.next();
	}

	public Color promptColorChoice() {

		System.out.print("Choose your color : ");

		// Get the first char of the input :
		char color = scan.next().toUpperCase().toCharArray()[0];

		while (true) {
			for (Color testColor : Color.values()) {
				if (testColor.getInitial() == color) {
					return testColor;
				}
			}
			System.err.print("Unrecognised color, try again : ");
			color = scan.next().toUpperCase().toCharArray()[0];
		}
	}

	/**
	 * Prompt for an int, will re prompt until an int is inputted
	 *
	 * @param whatToAsk           The message explaining what must be entered
	 * @param invalidEntryMessage The message returned signaling an incorrect input
	 * @return the typed int
	 */

	private static int promptInt(String whatToAsk, String invalidEntryMessage) {
		System.out.println(whatToAsk);
		while (!scan.hasNextInt()) {
			System.err.println(invalidEntryMessage);
			System.out.println(whatToAsk);
			scan.next();
		}
		return scan.nextInt();
	}

	/**
	 * Prompt for an int, will re prompt until an int is inputted and in between max and min
	 *
	 * @param whatToAsk           The message explaining what must be entered
	 * @param invalidEntryMessage The message returned signaling an incorrect input
	 * @param max                 max int value wanted
	 * @param min                 min int value wanted
	 * @return the typed int
	 */

	public int promptFramedInt(String whatToAsk, String invalidEntryMessage, int min, int max) {
		int output = promptInt(whatToAsk, invalidEntryMessage);
		while (output < min || output > max) {
			System.err.format("The entered value is not in between %d and %d. %n", min, max);
			output = promptInt(whatToAsk, invalidEntryMessage);
		}
		return output;
	}

	/**
	 * Prompt for an int, will re prompt until an int is inputted and in between max and min
	 *
	 * @param whatToAsk The message explaining what must be entered
	 * @param max       max int value wanted
	 * @param min       min int value wanted
	 * @return the typed int
	 */

	public int promptFramedInt(String whatToAsk, int min, int max) {
		return promptFramedInt(whatToAsk, "Invalid entry, please try again.", min, max);
	}

}

