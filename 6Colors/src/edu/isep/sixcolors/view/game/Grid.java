package edu.isep.sixcolors.view.game;

import edu.isep.sixcolors.model.entity.Board;
import edu.isep.sixcolors.model.Game;
import edu.isep.sixcolors.model.entity.TileColor;

import javax.swing.*;
import java.awt.*;

public class Grid extends JPanel {

    public Grid(Game game) {


        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createTitledBorder("Grid"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(1,1,1,1);
        Board board = game.getBoard();
        int boardWidth = board.getWidth();

        boolean small = (boardWidth > 13);

        for (int i = 0; i < boardWidth; i++){
            gbc.gridy = i;
            for (int j = 0; j < boardWidth; j++){
                gbc.gridx = j;
                this.add(
                        makeColorPane(board.getTile(i,j).getTileColor(), (board.getTile(i,j).getOwner()!=null), small),
                        gbc
                );
            }

        }


    }

    private JPanel makeColorPane(TileColor color, boolean owned, boolean small){
        JPanel tilePane = new JPanel();
        tilePane.setBackground(color.getColor());
        if (small) {
            tilePane.setPreferredSize(new Dimension(20, 20));
        } else {
            tilePane.setPreferredSize(new Dimension(40, 40));
        }

        if(owned) {
            tilePane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }

        return tilePane;
    }

}
