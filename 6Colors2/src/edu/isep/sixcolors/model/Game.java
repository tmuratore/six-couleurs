package edu.isep.sixcolors.model;

import java.util.Observable;

public class Game extends Observable{
    private Players players;
    private Board board;
    private GameState State = GameState.GridConfig;

    public Players getPlayers() {
        return this.players;
    }

    public void setPlayers(Players players) {

        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {

        this.board = board;
    }

    public GameState getState() {
        return State;
    }

    public void setState(GameState state) {
        State = state;
        setChanged();
        notifyObservers();
        clearChanged();
    }
}
