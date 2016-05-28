package edu.isep.sixcolors.view.listeners;


import javax.swing.*;

public class WarningPopup {
    private String message;
    private String title;

    public WarningPopup(String message, String title){
        this.message = message;
        this.title = title;
        JOptionPane jop = new JOptionPane();
        jop.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
    }
}
