package edu.isep.sixcolors.controller;

import edu.isep.sixcolors.model.AI.AIInterface;
import edu.isep.sixcolors.model.AI.RandomAI;
import edu.isep.sixcolors.model.*;
import edu.isep.sixcolors.model.entity.Board;
import edu.isep.sixcolors.model.entity.Player;
import edu.isep.sixcolors.model.entity.Players;
import edu.isep.sixcolors.model.entity.TileColor;
import edu.isep.sixcolors.view.listener.WarningPopup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Play implements ActionListener {

    Game game;

    public Play(Game game){
        this.game = game;
    }

    public void actionPerformed(ActionEvent e) {
        JPanel contentPane;
        if (game.getState() == GameState.GridConfig) { // We are initialising the game
            // 1. Fetch the text of the button :
            String sourceText = ((JButton) e.getSource()).getText();

            // 2. Fetch the content pane :
            contentPane = ((JPanel) ((JPanel) ((JButton) e.getSource()).getParent()).getParent().getComponent(0));

            // 3. Guess which button was clicked :
            if (sourceText == Config.RANDOM_BOARD_BUTTON_TEXT){ // Random board
                initGrid(contentPane, (JButton) e.getSource());
            }else if (sourceText == Config.CUSTOM_BOARD_BUTTON_TEXT) { // custom board
                // TODO initiateCustomGrid
            }
        }
        else if(game.getState() == GameState.NameConfig){ // We are setting the player names
            contentPane = ((JPanel) ((JPanel) ((JButton) e.getSource()).getParent()).getParent().getComponent(0));
            initPlayers(contentPane);
        }
        else if(game.getState() == GameState.Game) { // The game is in progress


            // TODO Not 100% necessary if
            if(e.getSource() instanceof JButton){
                colorButtonPressed(e);
            }

        }

    }

    public void initGrid(JPanel contentPane, JButton button){
        // Try parsing the number input
        try {
            int boardSize = Integer.parseInt(((JTextField) contentPane.getComponent(1)).getText());
            int playerNb = Integer.parseInt(((JTextField) contentPane.getComponent(3)).getText());

            // Check if the inputs are within boundaries :
            if (boardSize >= Config.GRID_MIN && boardSize <= Config.GRID_MAX && playerNb >= Config.PLAYER_NB_MIN && playerNb <= Config.PLAYER_NB_MAX){

                //if (button.getText() == )
                game.setBoard(new Board(boardSize));

                Players players = new Players(playerNb);
                players.setPlayerNumber(playerNb);
                game.setPlayers(players);

                game.setState(GameState.NameConfig);
            }
            else { // input out of bounds
                WarningPopup pop = new WarningPopup(
                        Config.OUT_OF_BOUNDS_GRID_CONFIG_MESSAGE + Config.newLine + Config.OUT_OF_BOUNDS_PLAYER_NB_CONFIG_MESSAGE,
                        Config.OUT_OF_BOUNDS_CONFIG_TITLE
                );
            }
        } catch (NumberFormatException x) {
            WarningPopup pop = new WarningPopup (
                    Config.NUMBER_FORMAT_CONFIG_MESSAGE,
                    Config.OUT_OF_BOUNDS_CONFIG_TITLE
            );
        }
    }

    public void initPlayers(JPanel contentPane){
        int playerNb = game.getPlayers().getPlayerNumber();
        Players players = game.getPlayers();
        for (int i = 0; i < playerNb; i++){
            // TODO Check if String is not null (no name entered)
            String playerName = ((JTextField) contentPane.getComponent(4*i+1)).getText();
            boolean playersAi = ((JCheckBox) contentPane.getComponent(4*i+3)).isSelected();


            players.setPlayer(i, new Player(playerName));
            players.getPlayer(i).setAi(playersAi);
            if (playersAi){
                AIInterface AI = new RandomAI();
                players.getPlayer(i).setAIInstance(AI);
            }

        }
        game.initGame();
        game.setState(GameState.Game);
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

        if(game.getCurrentPlayer().isAi()){
            // If it's an AI, then we wait for the ai to send back a choice
            chosenColor = currentPlayer.getAIInstance().colorChoice(game);

        }else{
            // If it's not an AI, then we wait for the physical player to make a choice in the view
            String buttonText = ((JButton) e.getSource()).getText();

            // 2. Parse the color choice of the player :

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

    public void saveButtonPressed(){
        final JFileChooser fc = new JFileChooser();

        //int returnVal = fc.showOpenDialog(aComponent);
    }
}
