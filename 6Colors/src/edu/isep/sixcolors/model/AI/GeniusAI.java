package edu.isep.sixcolors.model.AI;

import edu.isep.sixcolors.model.Game;
import edu.isep.sixcolors.model.entity.Player;
import edu.isep.sixcolors.model.entity.TileColor;

import java.io.Serializable;
import java.util.ArrayList;

public class GeniusAI implements AIInterface,Serializable {

    @Override
    public TileColor colorChoice(Game game) {
        TileColorChoiceNode tree = new TileColorChoiceNode();
        tree.setSons(computeSons(game, 3, game.getCurrentPlayer()));

        return TileColor.Blue;
    }

    public TileColorChoiceNode[] computeSons(Game game, int depth, Player me) {
        // is passing "me" really useful ?

        ArrayList<TileColor> availableTileColors = game.getAvailableTileColors();
        TileColorChoiceNode tree = new TileColorChoiceNode();
        TileColorChoiceNode[] sons = new TileColorChoiceNode[availableTileColors.size()];

        TileColorChoiceNode son = new TileColorChoiceNode();

        for(int i = 0; i<availableTileColors.size(); i++) {
            Game gP = null;
            try {
                gP = game.deepCopy();
            } catch (Exception e) {
                e.printStackTrace();
            }

            int initialScore = gP.getCurrentPlayer().getPoints();

            gP.getCurrentPlayer().setTileColor(availableTileColors.get(i));
            gP.updateBoard(
                    gP.getCurrentPlayer().getStartingTileCoords()[0],
                    gP.getCurrentPlayer().getStartingTileCoords()[1],
                    gP.getCurrentPlayer()
            );

            son.setTileColorChoice(availableTileColors.get(i));
            // Compute gain :
            if(gP.getCurrentPlayer() == me) {
                son.setGain(gP.getCurrentPlayer().getPoints() - initialScore);
            }
            else {
                son.setGain(initialScore - gP.getCurrentPlayer().getPoints());
            }

            sons[i] = son;

            if(depth > 0) {
                sons[i].setSons(computeSons(gP, depth-1, me));
            }
        }

        return sons;
    }

}
