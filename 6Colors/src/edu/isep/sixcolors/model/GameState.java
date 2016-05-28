package edu.isep.sixcolors.model;


import java.io.Serializable;

public enum GameState implements Serializable {

    GridConfig,
    NameConfig,
    CustomGrid,
    Game,
    End;
}
