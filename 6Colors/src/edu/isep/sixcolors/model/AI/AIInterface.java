package edu.isep.sixcolors.model.AI;


import edu.isep.sixcolors.model.Game;
import edu.isep.sixcolors.model.entity.TileColor;

/**
 * AI Interface, implemented by the AI's
 */
public interface AIInterface {
    TileColor colorChoice(Game game);
}
