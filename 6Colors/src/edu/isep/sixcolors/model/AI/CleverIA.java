package edu.isep.sixcolors.model.AI;

import edu.isep.sixcolors.model.Game;
import edu.isep.sixcolors.model.entity.TileColor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

/**
 * This AI makes a tradeoff between the gains prevented by the MachiavelicAI, and the best gain prpoposed by the GreedyAI.
 */
public class CleverIA implements AIInterface, Serializable {
    @Override
    public TileColor colorChoice(Game game) {
        TileColor chosenColor = null;
        Game guineaPig = null;
        GreedyAI gia = new GreedyAI();
        MachiavelicAI mia = new MachiavelicAI();


        // Making each AI propose a color choice :
        HashMap<TileColor, Integer> greedyCCWG = gia.colorChoiceWithGain(game);
        HashMap<TileColor, Integer> machiavelicCCWG = mia.colorChoiceWithGain(game);

        // Computing greedy color and associated gain :
        Set<TileColor> greedyTileColors = greedyCCWG.keySet();
        TileColor greedyTileColor = (TileColor) greedyTileColors.toArray()[0];
        int greedyGain = greedyCCWG.get(greedyTileColor);

        // Computing machiavelic color and associated gain :
        Set<TileColor> machiavelicTileColors = machiavelicCCWG.keySet();
        TileColor machiavelicTileColor = (TileColor) machiavelicTileColors.toArray()[0];
        int machiavelicGain = machiavelicCCWG.get(machiavelicTileColor);

        if(greedyGain >= machiavelicGain) {
            return greedyTileColor;
        }
        else {
            return machiavelicTileColor;
        }

    }
}
