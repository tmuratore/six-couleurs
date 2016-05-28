package edu.isep.sixcolors.model.entity;

import edu.isep.sixcolors.model.AI.AIInterface;

import java.io.Serializable;
import java.util.Observable;

/**
 * Player model : Represents a player of the current game
 * TODO abstract to be extended (local player OR RandomAI OR distant player)
 */
public class Player extends Observable implements Serializable {

    private String name;
    private TileColor tileColor;
    private TileColor previousTileColor;
    private int[] startingTileCoords = new int[2];
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

    public void setName(String name) {
        this.name = name;
        setChanged();
        notifyObservers();
        clearChanged();
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

    public int[] getStartingTileCoords() {
        return startingTileCoords;
    }

    public void setStartingTileCoords(int[] startingTileCoords) {
        this.startingTileCoords = startingTileCoords;
    }

    public void setStartingTileCoords(int i, int j) {
        this.startingTileCoords = new int[]{i, j};
        setChanged();
        notifyObservers();
        clearChanged();
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        setChanged();
        notifyObservers();
        clearChanged();
        this.points = points;
    }

    public void addPoints() {

        this.points++;
        setChanged();
        notifyObservers();
        clearChanged();
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
