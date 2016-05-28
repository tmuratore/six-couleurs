package edu.isep.sixcolors.view;


import edu.isep.sixcolors.model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Save implements ActionListener {

    final JFileChooser fc = new JFileChooser();
    private Game game;

    public Save(Game game){
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int returnVal = fc.showDialog(SwingUtilities.getRoot((Component) e.getSource()), "Select");

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try {
                ObjectOutput out = new ObjectOutputStream(new FileOutputStream(file.getAbsolutePath()));
                out.writeObject(game);
                out.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }
}
