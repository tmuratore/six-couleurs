package edu.isep.sixcolors.view;

import edu.isep.sixcolors.model.*;

import java.util.Scanner;

public class Console {
	
	static Scanner scan = new Scanner(System.in);

	/**
	 * Display board
	 * @param board
	 */
	public static void showBoard(Board board) {
		
		for(int i = 0; i< board.getTiles().length ; i++) {
			for(int j = 0; j<board.getTiles().length; j++) {
				Tile tile = board.getTile(i, j);
				String initial = Character.toString(tile.getColor().getInitial());
				
				if(tile.getOwner() == null) {
					initial = initial.toLowerCase();
				}
				System.out.print(initial + " ");
			}
			System.out.println();
		}
	}
	
	/**
	 * Prompts the player to type in his/her name
	 * @param number The number of the player to be displayed in the message
	 * @return The name typed in by the player
	 */
	public static String promptPlayerName(int number)	{
		System.out.format("Player %d, choose your name : ", number+1);
		return scan.next();
	}
	
	public static Color promptColorChoice() {
		
		System.out.print("Choose your color : ");
		
		// Get the first char of the input :
		char color = scan.next().toUpperCase().toCharArray()[0];
		
		while(true) {
			for(Color testColor: Color.values()) {
				if(testColor.getInitial() == color) {
					return testColor;
				}
			}
			System.out.print("Unrecognised color, try again : ");
			color = scan.next().toUpperCase().toCharArray()[0];
		}
	}

	/**
	 * Prompt for an int, will re prompt until an int is inputted
	 * @param whatToAsk The message explaining what must be entered
	 * @param invalidEntryMessage The message returned signaling an incorrect input
	 * @return the typed int
	 */

	public static int promptInt(String whatToAsk, String invalidEntryMessage) {
		System.out.println(whatToAsk);
		while(!scan.hasNextInt()) {
			System.out.println(invalidEntryMessage);
			System.out.println(whatToAsk);
			scan.next();
		}
		return scan.nextInt();
	}

	/**
	 * Prompt for an int, will re prompt until an int is inputted
	 * @param whatToAsk The message explaining what must be entered
	 * @return the typed int
     */

	public static int promptInt(String whatToAsk) {
		return promptInt(whatToAsk, "Invalid entry, please try again.");
	}


	/**
	 * Prompt for an int, will re prompt until an int is inputted and in between max and min
	 * @param whatToAsk The message explaining what must be entered
	 * @param invalidEntryMessage The message returned signaling an incorrect input
	 * @param max max int value wanted
	 * @param min min int value wanted
	 * @return the typed int
	 */

	public static int promptIntFramed(String whatToAsk, String invalidEntryMessage, int min, int max) {
		int output = promptInt(whatToAsk, invalidEntryMessage);
		while (output < min || output > max){
			System.out.format("The entered value is not in between %d and %d. %n", min, max);
			output = promptInt(whatToAsk, invalidEntryMessage);
		}
		return output;
	}

	/**
	 * Prompt for an int, will re prompt until an int is inputted and in between max and min
	 * @param whatToAsk The message explaining what must be entered
	 * @param max max int value wanted
	 * @param min min int value wanted
	 * @return the typed int
	 */

	public static int promptIntFramed(String whatToAsk, int min, int max) {
		return promptIntFramed(whatToAsk, "Invalid entry, please try again.", min, max);
	}
}
