package edu.isep.sixcolors.model.AI;

import edu.isep.sixcolors.model.entity.TileColor;

/**
 * Used by AIs to return a TileColor choice and the associated gain
 */
public class TileColorWithGain {

    private final TileColor tileColorChoice;
    private final int gain;

    public TileColorWithGain(TileColor tileColorChoice, int gain) {
        this.tileColorChoice = tileColorChoice;
        this.gain = gain;
    }

    public TileColor getTileColorChoice() {
        return tileColorChoice;
    }

    public int getGain() {
        return gain;
    }
}
