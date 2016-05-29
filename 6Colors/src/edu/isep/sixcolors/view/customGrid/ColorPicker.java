package edu.isep.sixcolors.view.customGrid;

import edu.isep.sixcolors.controller.Play;
import edu.isep.sixcolors.model.entity.TileColor;

import javax.swing.*;
import java.awt.*;

public class ColorPicker extends JPanel {

    public ColorPicker(Play play){

        this.setLayout(new FlowLayout());
        this.setBorder(BorderFactory.createTitledBorder("Game Color Picker"));

        for (TileColor color : TileColor.values()){
            JButton button = new JButton(color.name());
            button.setBackground(color.getColor());
            button.addActionListener(play);
            button.setOpaque(true);
            button.setBorderPainted(false);
            this.add(button);
        }
    }


}
