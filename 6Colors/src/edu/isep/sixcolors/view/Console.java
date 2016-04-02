package edu.isep.sixcolors.view;

import edu.isep.sixcolors.model.*;
import java.util.Scanner;

public class Console {
	
	static Scanner scan = new Scanner(System.in);
	
	public static void showBoard(Board board) {
		
		for(int i = 0; i< board.getTiles().length ; i++) {
			for(int j = 0; j<board.getTiles().length; j++) {
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
	
	/**
	 * Prompts the player to type in his/her name
	 * @param number The number of the player to be displayed in the message
	 * @return The name typed in by the player
	 */
	public static String promptPlayerName(int number)	{
		System.out.print("Player "+number+", choose your name : ");
		return scan.next();
	}
	
}
