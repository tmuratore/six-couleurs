package edu.isep.sixcolors.controller;

import edu.isep.sixcolors.model.Config;
import edu.isep.sixcolors.model.Game;
import edu.isep.sixcolors.view.WarningPopup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GraphicInfo gets the necessary information for the main controller Play from the Graphic (swing) View and passes it onto Play.
 * It implements ActionListener so it's called on a button press.
 * It also implements OutputInfo to be able to be used in Play
 */

public class GraphicInfo implements ActionListener, OutputInfo {

    private final Game game;
    private final Play play;

    /**
     * Constructor creates an instance of Play to use.
     *
     * @param game the current game model
     */
    public GraphicInfo(Game game) {
        this.game = game;
        this.play = new Play(game);
    }

    /**
     * Called whenever a button linked to this class is pushed in the graphic view
     * It picks the necessary information off the view to pass it onto Play.
     *
     * @param e the ActionEvent triggering the method
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        // Fetch the text of the button :
        String sourceText = ((JButton) e.getSource()).getText();
        String sourceActionCommand = ((JButton) e.getSource()).getActionCommand();
        int boardSize = -1;
        int playerNb = -1;
        String[] playerName = new String[1];
        String[] playerType = new String[1];
        // Fetch the content pane of the window that triggered the
        JPanel contentPane = (JPanel) ((JFrame) SwingUtilities.getRoot((Component) e.getSource())).getContentPane();

        switch (game.getState()) {
            case GridConfig:
                try {
                    boardSize = Integer.parseInt(((JTextField) ((JPanel) contentPane.getComponent(0)).getComponent(1)).getText());
                    playerNb = Integer.parseInt(((JTextField) ((JPanel) contentPane.getComponent(0)).getComponent(3)).getText());

                    if (boardSize < Config.GRID_MIN || boardSize > Config.GRID_MAX || playerNb < Config.PLAYER_NB_MIN || playerNb > Config.PLAYER_NB_MAX) {
                        new WarningPopup(
                                Config.OUT_OF_BOUNDS_GRID_CONFIG_MESSAGE + Config.newLine + Config.OUT_OF_BOUNDS_PLAYER_NB_CONFIG_MESSAGE,
                                Config.INVALID_ENTRY_TITLE
                        );
                    }

                } catch (NumberFormatException x) {
                    new WarningPopup(
                            Config.NUMBER_FORMAT_CONFIG_MESSAGE,
                            Config.INVALID_ENTRY_TITLE
                    );
                }
                break;
            case NameConfig:
                playerNb = game.getPlayers().getPlayerNumber();
                playerName = new String[playerNb];
                playerType = new String[playerNb];
                for (int i = 0; i < playerNb; i++) {
                    playerName[i] = ((JTextField) ((JPanel) contentPane.getComponent(0)).getComponent(3 * i + 1)).getText();
                    playerType[i] = (String) ((JComboBox) ((JPanel) contentPane.getComponent(0)).getComponent(3 * i + 2)).getSelectedItem();
                    if (playerName[i] == null || playerName[i].equals("")) {
                        new WarningPopup(
                                Config.EMPTY_PLAYER_NAME_MESSAGE,
                                Config.INVALID_ENTRY_TITLE
                        );
                        break;
                    }
                }
        }
        returnGameParamSet(sourceText, boardSize, playerNb, playerName, playerType, sourceActionCommand);
    }

    /**
     * Function from the Interface OutputInfo, that sends the information from actionPerformed to the main controller
     * Necessary step to assure a generic input to Play
     *
     * @param sourceText          The text of the pressed button
     * @param boardSize           The size of the board
     * @param playerNb            The number of players
     * @param playerName          A table of the player's names
     * @param playerType          A table of the player' what the player is (what AI or human)
     * @param sourceActionCommand The text sent as an Action Command through the button, must not be mixed up with sourceText
     */
    @Override
    public void returnGameParamSet(String sourceText, int boardSize, int playerNb, String[] playerName, String[] playerType, String sourceActionCommand) {
        play.control(sourceText, boardSize, playerNb, playerName, playerType, sourceActionCommand);
    }

}
