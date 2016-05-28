package edu.isep.sixcolors.controller;

import edu.isep.sixcolors.model.*;
import edu.isep.sixcolors.view.Window;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;

public class Play implements ActionListener {

    Game game;

    public Play(Game game){
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel contentPane;
        if (game.getState() == GameState.GridConfig) { // We are initialising the game
            contentPane = ((JPanel) ((JButton) e.getSource()).getParent().getComponent(0));
            initGrid(contentPane);

        }
        else if(game.getState() == GameState.NameConfig){ // We are setting the player names
            contentPane = ((JPanel) ((JButton) e.getSource()).getParent().getComponent(0));
            initPlayers(contentPane);
        }
        else if(game.getState() == GameState.Game) { // The game is in progress

            String buttonText = ((JButton) e.getSource()).getText();

            if (TileColor.contains(buttonText)) {



                // 1. Fetch the current player :
                Player currentPlayer = game.getCurrentPlayer();

                // 2. Parse the color choice of the player :
                TileColor chosenColor = null;
                try {
                    chosenColor = TileColor.parseTileColor(buttonText);

                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                // 3. Set the current player's color :
                currentPlayer.setTileColor(chosenColor);


                // 4. Update the board to apply the color choice :
                game.updateBoard(
                        currentPlayer.getStartingTileCoords()[0],
                        currentPlayer.getStartingTileCoords()[1],
                        currentPlayer
                );

                //5. Checks if a player has won :
                Player winner = checkForWinner();
                if (winner != null) {

                    System.out.println("The game is over, the winner is " + winner.getName() + " !");
                    game.setWinner(winner);
                    game.setState(GameState.End);
                }

                game.nextPlayer();


            }

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
        } catch (NumberFormatException x) {
            //TODO send an error popup via the view
        }
    }

    public void initPlayers(JPanel contentPane){
        int playerNb = game.getPlayers().getPlayerNumber();
        Players players = game.getPlayers();
        for (int i = 0; i < playerNb; i++){
            // TODO Check if String is not null (no name entered)
            String playerName = ((JTextField) contentPane.getComponent(2*i+1)).getText();


            players.setPlayer(i, new Player(playerName));

        }
        game.initGame();
        game.setState(GameState.Game);
    }

    /**
     * Returns the winner or null if there is none yet
     *
     * @return Player winner or null;
     */

    @Nullable
    private Player checkForWinner() {
        int winPoints = (int) Math.floor(Math.pow(game.getBoard().getWidth(), 2) / game.getPlayers().getPlayerNumber());
        int totalPoints = 0;
        int maxPoints = 0;
        int playerPoints;
        Player winner = game.getCurrentPlayer();
        for (int i = 0; i < game.getPlayers().getPlayerNumber(); i++) {
            playerPoints = game.getPlayers().getPlayer(i).getPoints();
            if (playerPoints > maxPoints) {
                maxPoints = playerPoints;
                winner = game.getPlayers().getPlayer(i);
            }
            totalPoints += game.getPlayers().getPlayer(i).getPoints();
        }
        if (game.getCurrentPlayer().getPoints() > winPoints || totalPoints == Math.pow(game.getBoard().getWidth(), 2)) {
            return winner;
        }
        return null;

    }
}
