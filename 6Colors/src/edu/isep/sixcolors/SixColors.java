package edu.isep.sixcolors;
import edu.isep.sixcolors.model.*;

/**
 * TODO annotate
 */
public class SixColors {
	
	public static void main(String[] args) {
		Tile tile = new Tile();
		
		tile.setColor(Colors.Red);
		
		System.out.println(tile.getColor().getInitial());
		
	}

}
