package edu.isep.sixcolors.view.game;

import edu.isep.sixcolors.model.Board;
import edu.isep.sixcolors.model.Game;
import edu.isep.sixcolors.model.TileColor;

import javax.swing.*;
import java.awt.*;

public class Grid extends JPanel {
    private Game game;

    public Grid(Game game){
        this.game = game;

        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        Board board = game.getBoard();
        int boardWidth = board.getWidth();

        for (int i = 0; i < boardWidth; i++){
            gbc.gridy = i;
            for (int j = 0; j < boardWidth; j++){
                gbc.gridx = j;
                this.add(makeColorPane(board.getTile(i,j).getTileColor()),gbc);
            }

        }


    }

    public JPanel makeColorPane(TileColor color){
        JPanel tilePane = new JPanel();
        tilePane.setBackground(color.getColor());
        tilePane.setPreferredSize(new Dimension(40,40));

        return tilePane;
    }

}
