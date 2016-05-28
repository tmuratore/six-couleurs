package edu.isep.sixcolors.view;

import edu.isep.sixcolors.controller.Play;
import edu.isep.sixcolors.model.Config;
import edu.isep.sixcolors.model.Game;
import edu.isep.sixcolors.model.GameState;
import edu.isep.sixcolors.view.game.Grid;
import edu.isep.sixcolors.view.game.ColorButtons;
import edu.isep.sixcolors.view.game.PlayerList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;

public class Window extends JFrame implements Observer {
    private JPanel container;
    private JPanel buttons;
    private Play play;
    private Game game;
    private Save save;
    private Load load;

    public Window(Play play, Game game) {
        this.play = play;
        this.game = game;
        this.load = new Load(game);



        this.setTitle("Six Colors Game");
        this.setSize(800, 500);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        buttons = new JPanel();

        buttons.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton random = new JButton(Config.RANDOM_BOARD_BUTTON_TEXT);
        random.addActionListener(play);
        buttons.add(random);
        JButton custom = new JButton(Config.CUSTOM_BOARD_BUTTON_TEXT);
        custom.addActionListener(play);
        buttons.add(custom);
        JButton fromSave = new JButton(Config.IMPORT_BOARD_BUTTON_TEXT);
        fromSave.addActionListener(load);
        buttons.add(fromSave);

        container = initContainer();
        this.add(container);
        this.add(buttons);



        this.pack();
        this.setVisible(true);
    }


    // this method is triggered when the model changes :
    @Override
    public void update(Observable o, Object arg) {
       System.out.println(game.getState().toString());
       JButton validate = ((JButton)(buttons.getComponent(0)));
        if(game.getState() == GameState.NameConfig){ // We are configuring player names
            validate.setText("Play");
            buttons.remove(1);
            buttons.remove(1);
            changeContainer(playerNameContainer());
        } else if (game.getState() == GameState.Game) { // game is in progress :
            if (game.getCurrentPlayer().isAi()){
                validate.doClick();
            }else{

            // removing the "validate" button b/c not removed by panel replacement  :
            this.remove(buttons);
            changeContainer(gameContainer());

            }
        } else if (game.getState() ==  GameState.End){
            changeContainer(endContainer());
        }
    }

    public void changeContainer(JPanel newContainer){
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
            pan.add(new JLabel("AI ? "));
            pan.add(new JCheckBox());
        }

        return pan;
    }

    private JPanel gameContainer() {
        Save save = new Save(game);
        Load load = new Load(game);
        JPanel pan = new JPanel();
        JPanel grid = new Grid(game);
        JMenuBar menuBar = new JMenuBar();
        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.setAccelerator(KeyStroke.getKeyStroke('s'));

        JMenuItem loadItem = new JMenuItem("Load");
        loadItem.setAccelerator(KeyStroke.getKeyStroke('l'));

        saveItem.addActionListener(save);
        loadItem.addActionListener(load);

        menuBar.add(saveItem);
        menuBar.add(loadItem);

        this.setJMenuBar(menuBar);


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
