package edu.isep.sixcolors.view;


import javax.swing.*;

public class WarningPopup {

    public WarningPopup(String message, String title) {
        JOptionPane jop = new JOptionPane();
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
    }
}
