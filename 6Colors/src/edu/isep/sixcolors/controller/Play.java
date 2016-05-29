package edu.isep.sixcolors.controller;

import edu.isep.sixcolors.model.*;
import edu.isep.sixcolors.model.AI.AIInterface;
import edu.isep.sixcolors.model.AI.DumbAI;
import edu.isep.sixcolors.model.entity.Board;
import edu.isep.sixcolors.model.entity.Player;
import edu.isep.sixcolors.model.entity.Players;
import edu.isep.sixcolors.model.entity.TileColor;
import edu.isep.sixcolors.view.WarningPopup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Play implements ActionListener {

    Game game;

    public Play(Game game){
        this.game = game;
    }

    public void actionPerformed(ActionEvent e) {
        // Fetch the text of the button :
        String sourceText = ((JButton) e.getSource()).getText();

        // Fetch the content pane of the window that triggered the
        JPanel contentPane = (JPanel) ((JFrame) SwingUtilities.getRoot((Component) e.getSource())).getContentPane();

        switch(game.getState()) {

            case Menu:
                // TODO
                break;
            case GridConfig:
                switch(sourceText) {
                    case Config.RANDOM_BOARD_BUTTON_TEXT:
                        // check inputs, init the grid and set game state to NameConfig
                        initGrid(contentPane);
                        break;
                    case Config.CUSTOM_BOARD_BUTTON_TEXT:
                        break;
                }
                break;
            case NameConfig:
                initPlayers(contentPane);
                break;
            case CustomGrid:
                break;
            case Game:
                colorButtonPressed(e);
                break;
            case End:
                break;
        }
    }

    public void initGrid(JPanel contentPane){
        // Try parsing the number input
        try {
            // If the order of the texts fields is changed, this could break :
            int boardSize = Integer.parseInt(((JTextField) ((JPanel) contentPane.getComponent(0)).getComponent(1)).getText());
            int playerNb = Integer.parseInt(((JTextField) ((JPanel) contentPane.getComponent(0)).getComponent(3)).getText());

            // Check if the inputs are within boundaries :
            if (boardSize >= Config.GRID_MIN && boardSize <= Config.GRID_MAX && playerNb >= Config.PLAYER_NB_MIN && playerNb <= Config.PLAYER_NB_MAX){

                game.setBoard(new Board(boardSize));

                Players players = new Players(playerNb);
                players.setPlayerNumber(playerNb);
                game.setPlayers(players);

                // Set game state :
                game.setState(GameState.NameConfig);
            }
            else { // input out of bounds
                new WarningPopup(
                        Config.OUT_OF_BOUNDS_GRID_CONFIG_MESSAGE + Config.newLine + Config.OUT_OF_BOUNDS_PLAYER_NB_CONFIG_MESSAGE,
                        Config.INVALID_ENTRY_TITLE
                );
            }
        } catch (NumberFormatException x) {
            new WarningPopup (
                    Config.NUMBER_FORMAT_CONFIG_MESSAGE,
                    Config.INVALID_ENTRY_TITLE
            );
        }
    }

    public void initPlayers(JPanel contentPane){
        int playerNb = game.getPlayers().getPlayerNumber();
        Players players = game.getPlayers();
        boolean emptyName = false;
        for (int i = 0; i < playerNb; i++) {

            String playerName = ((JTextField) ((JPanel) contentPane.getComponent(0)).getComponent(4 * i + 1)).getText();
            boolean playersAi = ((JCheckBox) ((JPanel) contentPane.getComponent(0)).getComponent(4 * i + 2)).isSelected();
            if (playerName == null || playerName.equals("")) {
                emptyName = true;
                new WarningPopup(Config.EMPTY_PLAYER_NAME_MESSAGE, Config.INVALID_ENTRY_TITLE);
                break;
            } else {

                players.setPlayer(i, new Player(playerName));
                players.getPlayer(i).setAi(playersAi);
                if (playersAi) {
                    AIInterface AI = new DumbAI();
                    players.getPlayer(i).setAIInstance(AI);
                }
            }
        }
        if (!emptyName){
                game.initGame();
                game.setState(GameState.Game);
        }
    }

    /**
     * Returns the winner or null if there is none yet
     *
     * @return Player winner or null;
     */

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

    public void colorButtonPressed(ActionEvent e){
        // 1. Fetch the current player & declare choice to catch :
        Player currentPlayer = game.getCurrentPlayer();
        TileColor chosenColor = null;

        // 2. Get what color the player has chosen :
        if(game.getCurrentPlayer().isAi()) {
            // If it's an AI, then we wait for the ai to send back a choice
            chosenColor = currentPlayer.getAIInstance().colorChoice(game);

        } else {
            // If it's not an AI, then we wait for the physical player to make a choice in the view
            String buttonText = ((JButton) e.getSource()).getText();


            //Parse the color choice of the player :
            try {
                chosenColor = TileColor.parseTileColor(buttonText);

            } catch (Exception e1) {
                e1.printStackTrace();
            }
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

            game.setWinner(winner);
            game.setState(GameState.End);
        }

        game.nextPlayer();
    }

}
