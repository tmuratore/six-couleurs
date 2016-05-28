package edu.isep.sixcolors;

import edu.isep.sixcolors.controller.Play;
import edu.isep.sixcolors.model.Game;
import edu.isep.sixcolors.view.Window;

import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class SixColors {

    public static void main(String[] args) {

        // Model
        Game game = new Game();

        // Controller
        Play play = new Play(game);

        // View
        Window window = new Window(play, game);

        /***************************************/
        /***/   game.addObserver(window);   /***/
        /***************************************/
    }
}
