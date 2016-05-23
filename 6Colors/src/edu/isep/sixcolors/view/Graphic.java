package edu.isep.sixcolors.view;

import edu.isep.sixcolors.controller.Game;
import edu.isep.sixcolors.model.Board;
import edu.isep.sixcolors.model.Player;
import edu.isep.sixcolors.model.TileColor;
import edu.isep.sixcolors.view.graphic.GameWindow;

import javax.swing.*;
import java.awt.*;

public class Graphic implements Output {

    private JFrame frame;

    public Graphic() {
        this.frame = new Window();
    }

    /*
    public (Board board, Game game){

        this.game = game;
        this.setTitle("Six Colors Game");
        this.setSize(800, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.setContentPane(container);
        container.setLayout(new BorderLayout());

        gamePanel.setBackground(Color.BLACK);
        gamePanel.setLayout(new GridBagLayout());

        updateBoard(board);
        playerList.setBackground(Color.RED);

        colorButtons.setLayout(new FlowLayout());
        updateColorButtons();

        this.getContentPane().add(gamePanel, BorderLayout.CENTER);
        this.getContentPane().add(playerList, BorderLayout.EAST);
        this.getContentPane().add(colorButtons, BorderLayout.SOUTH);

        title.setText("Six Colors");
        Font police = new Font("Roboto", Font.BOLD, 16);
        title.setFont(police);
        title.setForeground(Color.BLACK);
        title.setHorizontalAlignment(JLabel.CENTER);
        this.getContentPane().add(title, BorderLayout.NORTH);

        this.setVisible(true);
    } */

    public void printGameStatus(Board board, Player currentPlayer) {
        window.updateBoard(board);
    }


    @Override
    public int promptFramedInt(String whatToAsk, String invalidEntryMessage, int min, int max) {
        // arrière plan noir
        // champ texte
        // parse int
        // test frame
        // OU select dans une liste (on verra)
        return 0;
    }

    @Override
    public int promptFramedInt(String whatToAsk, int min, int max) {
        // idem avec custom error message
        return 0;
    }

    @Override
    public String promptString(String whatToAsk) {
        return null;
    }

    @Override
    public TileColor promptColorChoice() {
        // merge with update
        return null;
    }

    @Override
    public void printGameErrorMessage(String message) {
        // wtf ?
    }

    @Override
    public void printInfoMessage(String message) {
        // wtf ?
    }
}
