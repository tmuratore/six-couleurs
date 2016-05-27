package edu.isep.sixcolors.model;


public enum GameState {
    GridConfig(0),
    NameConfig(10),
    Game(20);


    private int gameStateCode;

    public int getGameStateCode() {
        return gameStateCode;
    }

    public void setGameStateCode(int gameStateCode) {
        this.gameStateCode = gameStateCode;
    }

    GameState(int gameStateCode) {
        this.gameStateCode = gameStateCode;
    }
}
