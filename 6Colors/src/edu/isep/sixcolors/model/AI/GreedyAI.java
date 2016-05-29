package edu.isep.sixcolors.model.AI;


import edu.isep.sixcolors.model.Game;
import edu.isep.sixcolors.model.entity.Player;
import edu.isep.sixcolors.model.entity.TileColor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class GreedyAI implements AIInterface, Serializable {

    @Override
    public TileColor colorChoice(Game game) {

        // 1. Get a list of all tileColors available :
        ArrayList<TileColor> availableTileColors = game.getAvailableTileColors();

        // 2. Evaluate the best tileColor to pick :
        TileColor chosenColor = availableTileColors.get(0);
        int bestPoints = 0;

        for(TileColor possibleChoice : availableTileColors) {
            // creating a guinea pig to test my moves :
            Game guineaPig = null;
            try {
                guineaPig = game.deepCopy();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Simulate a move on the guinea pig :
            Player me = guineaPig.getCurrentPlayer();

            me.setTileColor(possibleChoice);
            guineaPig.updateBoard(
                    me.getStartingTileCoords()[0],
                    me.getStartingTileCoords()[1],
                    me
            );

            // Optimize gain :
            if(me.getPoints() >= bestPoints) {
                chosenColor = possibleChoice;
                bestPoints = me.getPoints();
            }
        }

        // 3. Go live !
        return chosenColor;
    }
}
