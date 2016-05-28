package edu.isep.sixcolors.model.AI;

import edu.isep.sixcolors.model.Game;
import edu.isep.sixcolors.model.entity.TileColor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RandomAI implements AIInterface, Serializable {

    @Override
    public TileColor colorChoice(Game game) {
        Random random = new Random();
        ArrayList<TileColor> availableTileColors = new ArrayList<>(Arrays.asList(TileColor.values()));
        for (int i = 0; i < game.getPlayers().getPlayerNumber(); i++ ){
            TileColor tileColor = game.getPlayers().getPlayer(i).getTileColor();
            if (availableTileColors.contains(tileColor)){
                availableTileColors.remove(tileColor);
            }
        }
        return availableTileColors.get(random.nextInt(availableTileColors.size()));
    }
}
