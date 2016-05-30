package edu.isep.sixcolors.view.customGrid;

import edu.isep.sixcolors.model.entity.TileColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ColorPicker extends JPanel {

    public ColorPicker(ActionListener outputInfo){

        this.setLayout(new FlowLayout());
        this.setBorder(BorderFactory.createTitledBorder("Game Color Picker"));

        for (TileColor color : TileColor.values()){
            JButton button = new JButton(color.name());
            button.setBackground(color.getColor());
            button.addActionListener(outputInfo);
            button.setOpaque(true);
            button.setBorderPainted(false);
            this.add(button);
        }
    }


}
