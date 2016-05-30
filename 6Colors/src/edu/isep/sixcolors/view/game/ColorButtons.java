package edu.isep.sixcolors.view.game;


import edu.isep.sixcolors.model.Game;
import edu.isep.sixcolors.model.entity.TileColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * A pane of registered buttons, representing all the currently available colors to choose from in the current configuration
 */
public class ColorButtons extends JPanel {

    public ColorButtons(Game game, ActionListener outputInfo) {

        this.setLayout(new FlowLayout());
        this.setBorder(BorderFactory.createTitledBorder("Color Buttons"));

        ArrayList<TileColor> availableTileColors = game.getAvailableTileColors();

        for (TileColor availableTileColor : availableTileColors) {
            JButton button = new JButton(availableTileColor.name());
            button.setBackground(availableTileColor.getColor());
            button.addActionListener(outputInfo);
            button.setOpaque(true);
            button.setBorderPainted(false);
            this.add(button);

        }

    }
}
