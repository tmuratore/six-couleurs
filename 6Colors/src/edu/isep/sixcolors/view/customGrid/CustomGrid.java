package edu.isep.sixcolors.view.customGrid;

import edu.isep.sixcolors.controller.Play;
import edu.isep.sixcolors.model.Game;
import edu.isep.sixcolors.model.entity.Board;
import edu.isep.sixcolors.model.entity.Player;
import edu.isep.sixcolors.model.entity.TileColor;

import javax.swing.*;
import java.awt.*;

public class CustomGrid extends JPanel {
    Play play;

    public CustomGrid(Game game, Play play){
        this.play = play;

        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createTitledBorder("Grid"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(1,1,1,1);
        Board board = game.getBoard();
        int boardWidth = board.getWidth();

        for (int i = 0; i < boardWidth; i++){
            gbc.gridy = i;
            for (int j = 0; j < boardWidth; j++){
                gbc.gridx = j;

                boolean small = (game.getBoard().getWidth() > 13);

                if (game.getBoard().getTile(i,j).getOwner() == null) {
                    this.add(makeColorButton(game.getBoard().getTile(i, j).getTileColor(), i, j, small), gbc);
                }else{
                    Player owner = game.getBoard().getTile(i,j).getOwner();
                    if (owner.getStartingTileCoords()[0] == i && owner.getStartingTileCoords()[1] == j){
                        JPanel pan = new JPanel();
                        pan.setBackground(Color.BLACK);
                        if(small){
                        pan.setPreferredSize(new Dimension(20,20));
                        }else{
                        pan.setPreferredSize(new Dimension(40,40));
                        }
                        this.add(pan, gbc);
                    }else{
                        this.add(makeColorButton(game.getBoard().getTile(i, j).getTileColor(), i, j, small), gbc);
                        game.getBoard().getTile(i, j).setOwner(null);
                    }

                }
            }

        }


    }

    public JButton makeColorButton(TileColor color, int i, int j, boolean small){
        JButton tileButton = new JButton();


        tileButton.setOpaque(true);
        tileButton.setBorderPainted(false);

        if(small){
            tileButton.setPreferredSize(new Dimension(20,20));
        }else{
            tileButton.setPreferredSize(new Dimension(40,40));
        }

        tileButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        tileButton.setBackground(color.getColor());

        tileButton.setActionCommand(i + ":" + j);

        tileButton.addActionListener(play);

        return tileButton;
    }

}
