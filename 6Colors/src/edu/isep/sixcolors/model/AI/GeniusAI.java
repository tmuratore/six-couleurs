package edu.isep.sixcolors.model.AI;

import edu.isep.sixcolors.model.Game;
import edu.isep.sixcolors.model.entity.Player;
import edu.isep.sixcolors.model.entity.TileColor;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Genius AI : Based on the min / max algorithm
 */
public class GeniusAI implements AIInterface, Serializable {

    /**
     * Param defining the depth of the tree built by the algorithm
     */
    public static final int DEPTH = 4;

    /**
     * Pick a color !
     * @param game the game in progress
     * @return TileColor the chosen color
     */
    @Override
    public TileColor colorChoice(Game game) {
        TileColorChoiceNode tree = new TileColorChoiceNode();
        tree.setSons(computeSons(game, DEPTH, game.getCurrentPlayerId()));
        // hDisp(tree);
        // System.out.print("Reducing");
        reduce(tree.getSons());
        // hDisp(tree);

        int max = tree.getSons()[0].getGain();
        TileColor tc = tree.getSons()[0].getTileColorChoice();
        for(int i = 0; i < tree.getSons().length; i++) {
            if(tree.getSons()[i].getGain() > max) {
                tc = tree.getSons()[i].getTileColorChoice();
                max = tree.getSons()[i].getGain();
            }
        }
        // System.out.println("Picking "+tc.name()+"; estimated gain : "+max);
        return tc;
    }

    private void reduce(TileColorChoiceNode[] sons) {
        for(int i = 0; i < sons.length; i++) {
            if(sons[i].getSons()[0].getSons() == null) {
                sons[i].setGain(sons[i].getGain() + minMax(sons[i].getSons()));
                sons[i].setSons(null);
            }
            else {
                reduce(sons[i].getSons());
            }
        }
    }

    private void hDisp(TileColorChoiceNode tree) {
        if(tree.getSons() != null) {
            if(tree.getSons()[0].getMaximize()) {
                System.out.print("My possible choices : ");
            } else {
                System.out.print("Opponent's p choices: ");
            }
            for (TileColorChoiceNode son : tree.getSons()) {
                System.out.print(son.getTileColorChoice().name() + ";" + son.getGain() + " | ");
            }
            System.out.println();
            for (int i=0; i< tree.getSons().length; i++) {
                hDisp(tree.getSons()[i]);
            }
        }
    }


    public int minMax(TileColorChoiceNode[] sons) {
        int res = sons[0].getGain();
        boolean maximize = sons[0].getMaximize();

        for (TileColorChoiceNode son : sons) {
            if(maximize) { // We're trying to maximise :
                if(son.getGain() > res) {
                    res = son.getGain();
                }
            }
            else { // we're trying to minimize
                if (son.getGain() < res) {
                    res = son.getGain();
                }
            }
        }

        return res;
    }

    /**
     * Recursive function used build the possible moves tree
     *
     */
    private TileColorChoiceNode[] computeSons(Game game, int depth, int myId) {
        ArrayList<TileColor> availableTileColors = game.getAvailableTileColors();
        TileColorChoiceNode[] sons = new TileColorChoiceNode[availableTileColors.size()];

        for(int i = 0; i<availableTileColors.size(); i++) {
            sons[i] = new TileColorChoiceNode();

            Game gP = null;
            try {
                gP = game.deepCopy();
            } catch (Exception e) {
                e.printStackTrace();
            }

            int initialScore = gP.getCurrentPlayer().getPoints();

            gP.getCurrentPlayer().setTileColor(availableTileColors.get(i));
            gP.updateBoard(
                    gP.getCurrentPlayer().getStartingTile(),
                    gP.getCurrentPlayer()
            );

            // Store id-related information before simulating the next move :
            boolean maximize = (gP.getCurrentPlayerId() == myId);

            sons[i].setTileColorChoice(availableTileColors.get(i));

            // Compute gain :
            sons[i].setMaximize(maximize);
            if(maximize) {
                sons[i].setGain(gP.getCurrentPlayer().getPoints() - initialScore);
            }
            else {
                sons[i].setGain(initialScore - gP.getCurrentPlayer().getPoints());
            }

            // Recursive call :
            gP.nextPlayer();
            if(depth > 1) {
                sons[i].setSons(computeSons(gP, depth-1, myId));
            }
        }

        return sons;
    }



}
