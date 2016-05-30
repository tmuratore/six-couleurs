package edu.isep.sixcolors.model.entity;

import edu.isep.sixcolors.model.AI.AIInterface;

import java.io.Serializable;
import java.util.Observable;

/**
 * Player model : Represents a player of the game
 */
public class Player implements Serializable {

    private final String name;
    private TileColor tileColor;
    private TileColor previousTileColor;

    private Tile startingTile;
    private int points;

    private boolean ai = false;
    private AIInterface AIInstance;

    /**
     * Constructor
     * @param name the name of the player
     */
    public Player(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public TileColor getTileColor() {
        return tileColor;
    }

    public void setTileColor(TileColor controlledTileColor) {
        setPreviousTileColor(this.tileColor);
        this.tileColor = controlledTileColor;
    }

    public TileColor getPreviousTileColor() {
        return previousTileColor;
    }

    private void setPreviousTileColor(TileColor previousTileColor) {
        this.previousTileColor = previousTileColor;
    }

    public Tile getStartingTile() {
        return startingTile;
    }

    public void setStartingTile(Tile startingTile) {
        this.startingTile = startingTile;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addPoints() {
        this.points++;
    }

    public boolean isAi() {
        return ai;
    }

    public void setAi(boolean ai) {
        this.ai = ai;
    }

    public AIInterface getAIInstance() {
        return AIInstance;
    }

    public void setAIInstance(AIInterface AIInstance) {
        this.AIInstance = AIInstance;
    }


}
