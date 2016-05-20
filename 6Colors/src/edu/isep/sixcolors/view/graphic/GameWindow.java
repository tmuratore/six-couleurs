package edu.isep.sixcolors.view.graphic;


import edu.isep.sixcolors.SixColors;
import edu.isep.sixcolors.model.Board;
import edu.isep.sixcolors.model.Tile;
import edu.isep.sixcolors.model.TileColor;

import java.awt.*;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class GameWindow extends JFrame{
    private JPanel container = new JPanel();
    private JPanel game = new JPanel();
    private JPanel playerList = new JPanel();
    private JPanel colorButtons = new JPanel();
    private JLabel title = new JLabel();

    private MouseListener listener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    };


    public GameWindow(){
        this.setTitle("Six Colors Game");
        this.setSize(800, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.setContentPane(container);
        container.setLayout(new BorderLayout());

        game.setBackground(Color.BLUE);
        playerList.setBackground(Color.RED);
        colorButtons.setLayout(new FlowLayout());
        updateColorButtons();
        this.getContentPane().add(game, BorderLayout.CENTER);
        this.getContentPane().add(playerList, BorderLayout.EAST);
        this.getContentPane().add(colorButtons, BorderLayout.SOUTH);

        title.setText("Six Colors");
        Font police = new Font("Roboto", Font.BOLD, 16);
        title.setFont(police);
        title.setForeground(Color.BLACK);
        title.setHorizontalAlignment(JLabel.CENTER);
        this.getContentPane().add(title, BorderLayout.NORTH);

        this.setVisible(true);
    }

    private void updateColorButtons(){

        JButton[] buttons = new JButton[6];

        TileColor[] tileColors = TileColor.values();

        for(int i = 0; i < tileColors.length; i++) {
            buttons[i] = new JButton();
            buttons[i].setText(tileColors[i].name());
            buttons[i].setBackground(tileColors[i].getColor());
            buttons[i].addMouseListener(listener);
            colorButtons.add(buttons[i]);
        }
    }

    private void updateBoard(Board board){
        for (int i = 0; i < board.getTiles().length; i++) {
            for (int j = 0; j < board.getTiles().length; j++) {
                Tile tile = board.getTile(i, j);
                String initial = Character.toString(tile.getTileColor().getInitial());

                if (tile.getOwner() == null) {
                    initial = initial.toLowerCase();
                }
                System.out.print(initial + " ");
            }
            System.out.println();
        }
    }
}
