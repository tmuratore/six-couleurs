package edu.isep.sixcolors.view.game;


import edu.isep.sixcolors.controller.Play;
import edu.isep.sixcolors.model.Game;
import edu.isep.sixcolors.model.entity.Players;
import edu.isep.sixcolors.model.entity.TileColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class ColorButtons extends JPanel {
    private Game game;

    // TODO get rid of all view.game --> put them in Window
    public ColorButtons(Game game, ActionListener outputInfo){
        this.game = game;
        Players players = game.getPlayers();

        this.setLayout(new FlowLayout());
        this.setBorder(BorderFactory.createTitledBorder("Color Buttons"));

        // TODO I've seen this a couple times, factorisation needed !
        ArrayList<TileColor> availableTileColors = game.getAvailableTileColors();
        /*for (int i = 0; i < players.getPlayerNumber(); i++ ){
            TileColor tileColor = players.getPlayer(i).getTileColor();
            if (availableTileColors.contains(tileColor)){
                availableTileColors.remove(tileColor);
            }
        }*/

        for (int i = 0; i < availableTileColors.size(); i++){
            JButton button = new JButton(availableTileColors.get(i).name());
            button.setBackground(availableTileColors.get(i).getColor());
            button.addActionListener(outputInfo);
            button.setOpaque(true);
            button.setBorderPainted(false);
            this.add(button);

        }

    }
}
