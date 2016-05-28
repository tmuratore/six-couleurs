package edu.isep.sixcolors.view.game;


import edu.isep.sixcolors.controller.Play;
import edu.isep.sixcolors.model.Game;
import edu.isep.sixcolors.model.Player;
import edu.isep.sixcolors.model.Players;
import edu.isep.sixcolors.model.TileColor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ColorButtons extends JPanel {
    private Game game;


    public ColorButtons(Game game, Play play){
        this.game = game;
        Players players = game.getPlayers();

        this.setLayout(new FlowLayout());

        ArrayList<TileColor> availableTileColors = new ArrayList<>(Arrays.asList(TileColor.values()));
        for (int i = 0; i < players.getPlayerNumber(); i++ ){
            TileColor tileColor = players.getPlayer(i).getTileColor();
            System.out.println(tileColor.toString());
            if (availableTileColors.contains(tileColor)){
                availableTileColors.remove(tileColor);
            }
        }

        for (int i = 0; i < availableTileColors.size(); i++){
            JButton button = new JButton(availableTileColors.get(i).name());
            button.addActionListener(play);
            this.add(button);

        }

    }





}
