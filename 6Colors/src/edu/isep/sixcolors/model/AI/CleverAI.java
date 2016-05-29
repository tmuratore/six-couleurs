package edu.isep.sixcolors.model.AI;

import edu.isep.sixcolors.model.Game;
import edu.isep.sixcolors.model.entity.TileColor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

/**
 * This AI makes a tradeoff between the gains prevented by the MachiavelicAI, and the best gain prpoposed by the GreedyAI.
 */
public class CleverAI implements AIInterface, Serializable {
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

        // factor defining how much the greedy choice will be privileged against the machiavelic choice :
        float fac = 0.7f;

        /*
        // debug messages to understand the AI decision process :
        System.out.println("Greedy color " + greedyTileColor.name());
        System.out.println("greedyGain " + greedyGain);
        System.out.println("Machiavelic color" + machiavelicTileColor.name());
        System.out.println("machiavelicGain " + machiavelicGain);
        System.out.println("0.7 * machiavelicGain " + (fac*machiavelicGain));
        System.out.println();
        */

        if(greedyGain >= fac * machiavelicGain) {
            // System.out.println("Picking " + greedyTileColor.name());
            return greedyTileColor;
        }
        else {
            // System.out.println("Picking " + machiavelicTileColor.name());
            return machiavelicTileColor;
        }

    }
}
