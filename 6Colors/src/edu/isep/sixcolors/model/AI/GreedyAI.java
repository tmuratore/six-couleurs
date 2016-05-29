package edu.isep.sixcolors.model.AI;


import edu.isep.sixcolors.model.Game;
import edu.isep.sixcolors.model.entity.TileColor;

import java.util.ArrayList;
import java.util.Arrays;

public class GreedyAI implements AIInterface {

    @Override
    public TileColor colorChoice(Game game) {
        ArrayList<TileColor> availableTileColors = new ArrayList<>(Arrays.asList(TileColor.values()));
        for (int i = 0; i < game.getPlayers().getPlayerNumber(); i++ ){
            TileColor tileColor = game.getPlayers().getPlayer(i).getTileColor();
            if (availableTileColors.contains(tileColor)){
                availableTileColors.remove(tileColor);
            }
        }
        return null;
    }
}
