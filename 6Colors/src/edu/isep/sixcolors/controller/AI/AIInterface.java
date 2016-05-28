package edu.isep.sixcolors.controller.AI;


import edu.isep.sixcolors.model.Game;
import edu.isep.sixcolors.model.TileColor;

public interface AIInterface {
    //TODO Apparently this Interface creates a cycle (see Dependency Matrix)
    TileColor colorChoice(Game game);
}
