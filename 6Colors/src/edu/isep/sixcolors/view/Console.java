package edu.isep.sixcolors.view;

import edu.isep.sixcolors.model.*;

import java.util.InputMismatchException;
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
		System.out.print("Player "+number+", choose your name : ");
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
	
	public static int promptBoardWidth() {
		System.out.println("Size of the board : ");
		while(!scan.hasNextInt()) {
			System.out.println("Invalid entry, please try again.");
			System.out.println("Size of the board : ");
			scan.next();
		}
		return scan.nextInt();
	}
	
	public static int promptNumberPlayers() {
		System.out.println("Number of players : ");
		while(!scan.hasNextInt()) {
			System.out.println("Invalid entry, please try again.");
			System.out.println("Number of players : ");
			scan.next();
		}
		return scan.nextInt();
	}
	
}
