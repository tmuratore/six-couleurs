package edu.isep.sixcolors.view.listener;

import edu.isep.sixcolors.model.Config;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A popup window asking if the player want's to exit Six Colors
 */
public class Exit implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        int option = JOptionPane.showConfirmDialog(
                null,
                Config.EXIT_MESSAGE,
                Config.EXIT_TITLE,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (option == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }
}
