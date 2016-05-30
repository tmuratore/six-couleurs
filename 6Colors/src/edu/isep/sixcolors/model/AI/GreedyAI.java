package edu.isep.sixcolors.model.AI;


import edu.isep.sixcolors.model.Game;
import edu.isep.sixcolors.model.entity.Player;
import edu.isep.sixcolors.model.entity.TileColor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class GreedyAI implements AIInterface, Serializable {

    @Override
    public TileColor colorChoice(Game game) {
        HashMap<TileColor, Integer> ccwg = colorChoiceWithGain(game);

        Set<TileColor> tileColors = ccwg.keySet();
        TileColor tc = (TileColor) tileColors.toArray()[0];

        return tc;
    }

    public HashMap<TileColor, Integer> colorChoiceWithGain(Game game) {

        HashMap<TileColor, Integer> map = new HashMap<>();

        // 1. Get a list of all tileColors available :
        ArrayList<TileColor> availableTileColors = game.getAvailableTileColors();

        // 2. Evaluate the best tileColor to pick :
        TileColor chosenColor = availableTileColors.get(0);
        int bestGain = 0;

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

            int pointsBefore = me.getPoints();

            me.setTileColor(possibleChoice);
            guineaPig.updateBoard(
                    me.getStartingTile(),
                    me
            );

            int pointsAfter = me.getPoints();

            // Optimize gain :
            if(pointsAfter - pointsBefore >= bestGain) {
                chosenColor = possibleChoice;
                bestGain = pointsAfter - pointsBefore;
            }
        }

        // 3. Go live !
        map.put(chosenColor, bestGain);
        return map;
    }
}
