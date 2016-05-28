package edu.isep.sixcolors.model.entity;

import java.io.Serializable;
import java.util.Observable;

public class Players extends Observable implements Serializable {
    private Player[] playerList;
    private int playerNumber = 0;

    public Players(int number){
        this.playerNumber = number;
        playerList = new Player[number];
        setChanged();
        notifyObservers();
        clearChanged();
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayer(int i, Player player) {

        this.playerList[i] = player;
        setChanged();
        notifyObservers();
        clearChanged();
    }

    public Player getPlayer(int i) {
        return this.playerList[i];
    }

    public Player[] getPlayerList() {
        return playerList;
    }


}
