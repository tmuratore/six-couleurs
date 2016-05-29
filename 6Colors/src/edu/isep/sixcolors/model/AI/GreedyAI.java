package edu.isep.sixcolors.model.AI;


import edu.isep.sixcolors.model.Game;
import edu.isep.sixcolors.model.entity.TileColor;

import java.util.ArrayList;
import java.util.Arrays;

public class GreedyAI implements AIInterface {

    @Override
    public TileColor colorChoice(Game game) {

        // 1. Build a list of all tileColors available :
        ArrayList<TileColor> availableTileColors = new ArrayList<>(Arrays.asList(TileColor.values()));
        for (int i = 0; i < game.getPlayers().getPlayerNumber(); i++ ) {
            TileColor tileColor = game.getPlayers().getPlayer(i).getTileColor();
            if (availableTileColors.contains(tileColor)){
                availableTileColors.remove(tileColor);
            }
            else {
                // there could be an exception thrown here
            }
        }

        // 2. Evaluate the best tileColor to pick :
        TileColor chosenColor = availableTileColors.get(0);
        // 3. Go live !
        return chosenColor;
    }
}
