package edu.isep.sixcolors.model;


import java.io.Serializable;

public enum GameState implements Serializable {

    Menu,

    GridConfig,
    NameConfig,
    CustomGrid,
    Game,
    End,
}