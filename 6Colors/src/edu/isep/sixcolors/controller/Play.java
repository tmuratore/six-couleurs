package edu.isep.sixcolors.controller;

import edu.isep.sixcolors.model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Play implements ActionListener {
    Game game;

    public Play(Game game){
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel contentPane = ((JPanel) ((JButton) e.getSource()).getParent().getComponent(0));
        if (game.getState() == GameState.GridConfig) { // We are initialising the game

            initGrid(contentPane);

        }else if(game.getState() == GameState.NameConfig){ // We are setting the player names

            initPlayers(contentPane);
        }else if(game.getState() == GameState.Game){

        }

    }

    public void initGrid(JPanel contentPane){
        try {
            int boardSize = Integer.parseInt(((JTextField) contentPane.getComponent(1)).getText());
            int playerNb = Integer.parseInt(((JTextField) contentPane.getComponent(3)).getText());

            if (boardSize >= Config.GRID_MIN && boardSize <= Config.GRID_MAX && playerNb >= Config.PLAYER_NB_MIN && playerNb <= Config.PLAYER_NB_MAX){

                game.setBoard(new Board(boardSize));

                Players players = new Players(playerNb);
                players.setPlayerNumber(playerNb);
                game.setPlayers(players);

                game.setState(GameState.NameConfig);
            }
        }catch (NumberFormatException x) {
            //TODO send an error popup via the view
        }
    }

    public void initPlayers(JPanel contentPane){
        int playerNb = game.getPlayers().getPlayerNumber();
        Players players = game.getPlayers();
        for (int i = 0; i < playerNb; i++){
            String playerName = ((JTextField) contentPane.getComponent(2*i+1)).getText();
            System.out.println(playerName);
            // TODO Properly Construct PlayerS
            players.setPlayer(i, new Player(playerName));

        }
        game.setState(GameState.Game);
        game.initGame();
    }
}
