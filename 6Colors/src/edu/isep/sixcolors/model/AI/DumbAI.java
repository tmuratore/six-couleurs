package edu.isep.sixcolors.model.AI;

import edu.isep.sixcolors.model.Game;
import edu.isep.sixcolors.model.entity.TileColor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class DumbAI implements AIInterface, Serializable {

    @Override
    public TileColor colorChoice(Game game) {
        Random random = new Random();
        ArrayList<TileColor> availableTileColors = game.getAvailableTileColors();
        return availableTileColors.get(random.nextInt(availableTileColors.size()));
    }
}
