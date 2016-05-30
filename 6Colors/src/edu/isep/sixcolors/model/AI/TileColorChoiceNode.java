package edu.isep.sixcolors.model.AI;

import edu.isep.sixcolors.model.entity.TileColor;

/**
 * TODO used by genius
 */
public class TileColorChoiceNode {

    private TileColor tileColorChoice;
    private int gain;
    private TileColorChoiceNode[] sons;
    private boolean maximize;

    public TileColor getTileColorChoice() {
        return tileColorChoice;
    }

    public void setTileColorChoice(TileColor tileColorChoice) {
        this.tileColorChoice = tileColorChoice;
    }

    public int getGain() {
        return gain;
    }

    public void setGain(int gain) {
        this.gain = gain;
    }

    public TileColorChoiceNode[] getSons() {
        return sons;
    }

    public void setSons(TileColorChoiceNode[] sons) {
        this.sons = sons;
    }

    public void setMaximize(boolean maximize) {
        this.maximize = maximize;
    }

    public boolean getMaximize() {
        return maximize;
    }
}
