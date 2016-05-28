package edu.isep.sixcolors.view.game;


import edu.isep.sixcolors.model.Game;

import javax.swing.*;
import java.awt.*;

public class PlayerList extends JPanel {

    public PlayerList(Game game){
        this.setLayout(new GridLayout(game.getPlayers().getPlayerNumber()+3, 1));
        JLabel currentPlayerLabel = new JLabel("It's " + game.getCurrentPlayer().getName() + "'s turn !");
        JLabel currentPlayerIdLabel = new JLabel("Id : " + game.getCurrentPlayerId());
        JLabel currentPlayerColorLabel = new JLabel("Color : " + game.getCurrentPlayer().getTileColor().name());

        this.add(currentPlayerLabel);
        this.add(currentPlayerIdLabel);
        this.add(currentPlayerColorLabel);
        for (int i = 0; i < game.getPlayers().getPlayerNumber(); i++){
            JLabel playerLabel = new JLabel(game.getPlayers().getPlayer(i).getName() + " " + game.getPlayers().getPlayer(i).getPoints());
            this.add(playerLabel);
        }
    }

}
