package edu.isep.sixcolors.view;

import edu.isep.sixcolors.controller.Play;
import edu.isep.sixcolors.model.Board;
import edu.isep.sixcolors.model.Config;
import edu.isep.sixcolors.model.Game;
import edu.isep.sixcolors.model.GameState;
import edu.isep.sixcolors.view.game.Grid;
import edu.isep.sixcolors.view.game.ColorButtons;
import edu.isep.sixcolors.view.game.PlayerList;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class Window extends JFrame implements Observer {
    private JPanel container;
    private JButton validate;
    private Play play;
    private Game game;

    public Window(Play play, Game game) {
        this.play = play;
        this.game = game;


        this.setTitle("Six Colors Game");
        this.setSize(800, 500);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        validate = new JButton("Validate");
        validate.addActionListener(play);
        container = initContainer();
        this.add(container, BorderLayout.NORTH);
        this.add(validate, BorderLayout.SOUTH);

        this.pack();
        this.setVisible(true);
    }


    // this method is triggered when the model changes :
    @Override
    public void update(Observable o, Object arg) {

        if(game.getState() == GameState.NameConfig){ // We are configuring player names
            changeContainer(playerNameContainer());
        } else if (game.getState() == GameState.Game) { // game is in progress :
            if (game.getCurrentPlayer().isAi()){
                validate.doClick();
            }else{

            // removing the "validate" button b/c not removed by panel replacement :
            this.remove(validate);
            changeContainer(gameContainer());

            }
        } else if (game.getState() ==  GameState.End){
            changeContainer(endContainer());
        }
    }

    private void changeContainer(JPanel newContainer){
        this.remove(container);
        this.container = newContainer;
        this.container.invalidate();
        this.add(container,0);
        this.revalidate();
        this.pack();
        this.repaint();
    }

    private JPanel initContainer(){
        JPanel pan = new JPanel();
        JTextField gridInput = new JTextField(Config.GRID_MIN);
        JTextField playerInput = new JTextField(Config.PLAYER_NB_MIN);
        JLabel gridLabel = new JLabel(Config.GRID_PROMPT_MESSAGE);
        JLabel playerLabel = new JLabel(Config.PLAYER_NB_PROMPT_MESSAGE);

        pan.setLayout( new GridLayout(2,2));
        pan.setBorder(BorderFactory.createTitledBorder("Game parameters"));
        pan.add(gridLabel);
        pan.add(gridInput);
        pan.add(playerLabel);
        pan.add(playerInput);

        return pan;
    }

    private JPanel playerNameContainer(){
        JPanel pan = new JPanel();
        pan.setLayout( new GridLayout(game.getPlayers().getPlayerNumber(),4));
        pan.setBorder(BorderFactory.createTitledBorder("Player Names"));
        for (int i = 0; i < game.getPlayers().getPlayerNumber(); i++){
            pan.add(new JLabel(Config.PLAYER_NAME_PROMPT_MESSAGE(i)));
            pan.add(new JTextField());
            pan.add(new JLabel("RandomAI : "));
            pan.add(new JCheckBox());
        }

        return pan;
    }

    private JPanel gameContainer() {
        JPanel pan = new JPanel();
        JPanel grid = new Grid(game);

        JPanel playerList = new PlayerList(game);
        JPanel colorButtons = new ColorButtons(game, play);

        pan.setLayout(new BorderLayout());

        pan.add(grid, BorderLayout.CENTER);
        pan.add(playerList, BorderLayout.EAST);
        pan.add(colorButtons, BorderLayout.SOUTH);

        return pan;
    }

    private JPanel endContainer(){
        JPanel pan = new JPanel();


        Font bold = new Font("Roboto", Font.BOLD,30);
        JLabel label = new JLabel(Config.WINNER_SPLASH(game.getWinner().getName()));
        label.setFont(bold);

        pan.add(label);

        return pan;


    }
}
