package edu.isep.sixcolors.model.entity;

import edu.isep.sixcolors.model.AI.AIInterface;

import java.io.Serializable;
import java.util.Observable;

/**
 * Player model : Represents a player of the current game
 * TODO abstract to be extended (local player OR DumbAI OR distant player)
 */
public class Player extends Observable implements Serializable {

    private String name;
    private TileColor tileColor;
    private TileColor previousTileColor;

    private Tile startingTile;
    private int points;

    private boolean ai = false;
    private AIInterface AIInstance;

    public Player(String name){
        this.name = name;
        setChanged();
        notifyObservers();
        clearChanged();
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
        setChanged();
        notifyObservers();
        clearChanged();
    }

    public TileColor getPreviousTileColor() {
        return previousTileColor;
    }

    private void setPreviousTileColor(TileColor previousTileColor) {
        this.previousTileColor = previousTileColor;
    }

    /*
    public int[] getStartingTileCoords() {
        return startingTileCoords;
    }

    public void setStartingTileCoords(int[] startingTileCoords) {
        this.startingTileCoords = startingTileCoords;
    }

    public void setStartingTileCoords(int i, int j) {
        this.startingTileCoords = new int[]{i, j};
    }
    */

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
