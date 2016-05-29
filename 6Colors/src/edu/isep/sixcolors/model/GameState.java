package edu.isep.sixcolors.model;


import java.io.Serializable;

public enum GameState implements Serializable {
    Menu,
    GridConfig,
    CustomGrid,
    NameConfig,
    Game,
    End,
}