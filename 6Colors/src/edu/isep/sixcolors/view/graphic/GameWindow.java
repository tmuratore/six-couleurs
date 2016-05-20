package edu.isep.sixcolors.view.graphic;

import edu.isep.sixcolors.model.*;

import java.awt.*;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class GameWindow extends JFrame{
    private JPanel pan = new JPanel();

    private JButton bouton1 = new ColorButton(GameColor.Red.name(), GameColor.Red.getColor());
    private JButton bouton2 = new ColorButton(GameColor.Green.name(), GameColor.Green.getColor());
    private JButton bouton3 = new ColorButton(GameColor.Blue.name(), GameColor.Blue.getColor());
    private JButton bouton4 = new ColorButton(GameColor.Magenta.name(), GameColor.Magenta.getColor());
    private JButton bouton5 = new ColorButton(GameColor.Yellow.name(), GameColor.Yellow.getColor());
    private JButton bouton6 = new ColorButton(GameColor.Orange.name(), GameColor.Orange.getColor());

    private JPanel container = new JPanel();

    private JLabel label = new JLabel("Six Colors");



    public GameWindow(){
        this.setTitle("Six Colors game");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        pan.setBackground(Color.green);

        container.setBackground(Color.white);
        container.setLayout(new BorderLayout());
        container.add(pan, BorderLayout.CENTER);

        JPanel south = new JPanel(new GridLayout(1,6));
        south.add(bouton1);
        south.add(bouton2);
        south.add(bouton3);
        south.add(bouton4);
        south.add(bouton5);
        south.add(bouton6);
        south.setBackground(Color.lightGray);
        container.add(south, BorderLayout.SOUTH);

        Font police = new Font("Tahoma", Font.BOLD, 16);
        label.setFont(police);
        label.setForeground(Color.blue);
        label.setHorizontalAlignment(JLabel.CENTER);
        container.add(label, BorderLayout.NORTH);

        JPanel playerList = new JPanel();
        playerList.setBackground(Color.pink);
        container.add(playerList, BorderLayout.EAST);

        this.setContentPane(container);
        this.setVisible(true);
    }
}
