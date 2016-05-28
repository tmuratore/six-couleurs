package edu.isep.sixcolors.view.game;


import edu.isep.sixcolors.model.Game;

import javax.swing.*;
import java.awt.*;

public class PlayerList extends JPanel {

    public PlayerList(Game game){

        Font def = new Font("Roboto",Font.PLAIN,12);
        Font bold = new Font("Roboto", Font.BOLD,16);

        this.setLayout(new GridLayout(game.getPlayers().getPlayerNumber()+3, 1));
        this.setBorder(BorderFactory.createTitledBorder("Player List"));
        JLabel currentPlayerLabel = new JLabel("It's " + game.getCurrentPlayer().getName() + "'s turn !", SwingConstants.CENTER);
        JLabel currentPlayerColorLabel = new JLabel("Color : " + game.getCurrentPlayer().getTileColor().name(), SwingConstants.CENTER);
        JLabel playerPointsTitle = new JLabel("Players : ", SwingConstants.LEFT);

        currentPlayerLabel.setFont(bold);
        currentPlayerColorLabel.setFont(def);
        playerPointsTitle.setFont(bold);


        this.add(currentPlayerLabel);
        this.add(currentPlayerColorLabel);
        this.add(playerPointsTitle);
        for (int i = 0; i < game.getPlayers().getPlayerNumber(); i++){
            JLabel playerLabel = new JLabel(
                    "<html>" +
                    game.getPlayers().getPlayer(i).getName() + " has "
                    + game.getPlayers().getPlayer(i).getPoints()+ ((game.getPlayers().getPlayer(i).getPoints() > 1)? " points": " point")
                    + "<br> and is " + game.getPlayers().getPlayer(i).getTileColor().name()
                    + "</html>"
            , SwingConstants.LEFT);
            this.setFont(def);
            this.add(playerLabel);

        }
    }

}
