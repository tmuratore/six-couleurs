package edu.isep.sixcolors.model;


import java.io.Serializable;

/**
 * Enum of the different states of the game
 */
public enum GameState implements Serializable {
    Menu,
    GridConfig,
    CustomGrid,
    NameConfig,
    Game,
    End

}