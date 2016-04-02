package edu.isep.sixcolors.view;

import edu.isep.sixcolors.model.*;

public class Console {
	
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
	
}
