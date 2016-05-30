package edu.isep.sixcolors.view;


import javax.swing.*;

/**
 * Launches a warning popup window
 */
public class WarningPopup {

    /**
     * Constructor launching the warning popup
     *
     * @param message the message to show in the window
     * @param title   the title of the window
     */
    public WarningPopup(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
    }
}
