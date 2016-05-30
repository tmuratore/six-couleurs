package edu.isep.sixcolors.controller;

import edu.isep.sixcolors.model.Config;
import edu.isep.sixcolors.model.Game;
import edu.isep.sixcolors.view.WarningPopup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicInfo implements ActionListener, OutputInfo {

    private final Game game;
    private final Play play;

    public GraphicInfo(Game game) {
        this.game = game;
        this.play = new Play(game);
    }

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

    @Override
    public void returnGameParamSet(String sourceText, int boardSize, int playerNb, String[] playerName, String[] playerType, String sourceActionCommand){
        play.control(sourceText, boardSize, playerNb, playerName, playerType, sourceActionCommand);
    }

}
