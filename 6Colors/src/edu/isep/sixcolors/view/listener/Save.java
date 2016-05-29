package edu.isep.sixcolors.view.listener;


import edu.isep.sixcolors.model.Config;
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
        int returnVal = fc.showDialog(SwingUtilities.getRoot((Component) e.getSource()), Config.LOAD_SAVE_ACTION_NAME);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try {
                ObjectOutput out = new ObjectOutputStream(new FileOutputStream(file.getAbsolutePath()));
                out.writeObject(game);
                out.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            JOptionPane jop = new JOptionPane();
            int option = jop.showConfirmDialog(
                    null,
                    Config.EXIT_AFTER_SAVE_MESSAGE,
                    Config.EXIT_TITLE,
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (option == JOptionPane.YES_OPTION){
                System.exit(0);
            }

        }
    }
}
