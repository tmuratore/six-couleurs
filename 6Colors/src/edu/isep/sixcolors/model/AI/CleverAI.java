package edu.isep.sixcolors.model.AI;

import edu.isep.sixcolors.model.Game;
import edu.isep.sixcolors.model.entity.TileColor;

import java.io.Serializable;

/**
 * This AI computes a tradeoff between the gains prevented by the MachiavelicAI, and the best gain prpoposed by the GreedyAI.
 */
public class CleverAI implements AIInterface, Serializable {

    /**
     * Factor defining how much the machiavelic AI is privileged
     * Higher => more weight to machiavelic AI
     */
    public static final float FAC = 0.8f;


    @Override
    public TileColor colorChoice(Game game) {
        // factor defining how much the greedy choice will be privileged against the machiavelic choice :

        GreedyAI gia = new GreedyAI();
        MachiavelicAI mia = new MachiavelicAI();


        // Making each AI propose a color choice :
        TileColorWithGain greedyTcwg = gia.colorChoiceWithGain(game);

        TileColorWithGain machiavelicTcwg = mia.colorChoiceWithGain(game);

        // Computing greedy color and associated gain :
        TileColor greedyTileColor = greedyTcwg.getTileColorChoice();
        int greedyGain = greedyTcwg.getGain();

        // Computing machiavelic color and associated gain :
        TileColor machiavelicTileColor = machiavelicTcwg.getTileColorChoice();
        int machiavelicGain = machiavelicTcwg.getGain();


        /*
        // debug messages to understand the AI decision process :
        System.out.println("Greedy color " + greedyTileColor.name());
        System.out.println("greedyGain " + greedyGain);
        System.out.println("Machiavelic color" + machiavelicTileColor.name());
        System.out.println("machiavelicGain " + machiavelicGain);
        System.out.println("0.7 * machiavelicGain " + (FAC*machiavelicGain));
        System.out.println();
        */

        if(greedyGain >= FAC * machiavelicGain) {
            // System.out.println("Picking " + greedyTileColor.name());
            return greedyTileColor;
        }
        else {
            // System.out.println("Picking " + machiavelicTileColor.name());
            return machiavelicTileColor;
        }
    }
}
