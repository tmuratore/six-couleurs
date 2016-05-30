package edu.isep.sixcolors.model.AI;

import edu.isep.sixcolors.model.Game;
import edu.isep.sixcolors.model.entity.TileColor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Dumb AI : This AI picks a color at random
 */
public class DumbAI implements AIInterface, Serializable {

    /**
     * Pick a color !
     * @param game the game in progress
     * @return TileColor the chosen color
     */
    @Override
    public TileColor colorChoice(Game game) {
        Random random = new Random();
        ArrayList<TileColor> availableTileColors = game.getAvailableTileColors();
        return availableTileColors.get(random.nextInt(availableTileColors.size()));
    }
}
