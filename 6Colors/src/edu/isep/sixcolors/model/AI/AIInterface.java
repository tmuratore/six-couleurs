package edu.isep.sixcolors.model.AI;


import edu.isep.sixcolors.model.Game;
import edu.isep.sixcolors.model.entity.TileColor;

public interface AIInterface {
    //TODO Apparently this Interface creates a cycle (see Dependency Matrix)
    TileColor colorChoice(Game game);
}
