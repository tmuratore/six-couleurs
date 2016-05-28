package edu.isep.sixcolors.controller.AI;


import edu.isep.sixcolors.model.Game;
import edu.isep.sixcolors.model.TileColor;

public interface AIInterface {

    TileColor colorChoice(Game game);
}
