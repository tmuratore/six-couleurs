package edu.isep.sixcolors.view;

import edu.isep.sixcolors.controller.Play;
import edu.isep.sixcolors.model.Config;
import edu.isep.sixcolors.model.Game;
import edu.isep.sixcolors.model.entity.Players;
import edu.isep.sixcolors.view.game.ColorButtons;
import edu.isep.sixcolors.view.game.Grid;
import edu.isep.sixcolors.view.game.PlayerList;
import edu.isep.sixcolors.view.listener.Load;
import edu.isep.sixcolors.view.listener.Save;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Represents the main game window
 */
public class Window extends JFrame implements Observer {


    private Play play; // Controller interactions
    private Game game; // Model interactions

    public Window(Play play, Game game) {
        // accesses to the controller and model
        this.game = game;
        this.play = play;

        // general parameters :
        this.setTitle(Config.GAME_TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        showLocalGameSetup();

        // minimal size around containers :
        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * This method is called when the Game model (this.game) is modified :
     *
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        switch (game.getState()) {
            case Menu:
            case GridConfig:
                showLocalGameSetup();
                break;
            case NameConfig:
                showPlayersSetup();
                break;
            case CustomGrid:
                break;
            case Game:
                showGame();
                break;
            case End:
                break;
        }

    }

    public void showLocalGameSetup() {
        // main container of this interface :
        JPanel pan = new JPanel();
        JPanel inputContainer = new JPanel();
        JPanel actionContainer = new JPanel();

        // It's a magical unicorn flying through the sky :
        Load load = new Load(game);

        // Text fields :
        JLabel gridLabel = new JLabel(Config.GRID_PROMPT_MESSAGE);
        JTextField gridSizeInput = new JTextField(Config.GRID_MIN);
        JLabel playerLabel = new JLabel(Config.PLAYER_NB_PROMPT_MESSAGE);
        JTextField playerNamesInput = new JTextField(Config.PLAYER_NB_MIN);

        // buttons :
        JButton randomButton = new JButton(Config.RANDOM_BOARD_BUTTON_TEXT);
        JButton customGameButton = new JButton(Config.CUSTOM_BOARD_BUTTON_TEXT);
        JButton fromSaveButton = new JButton(Config.LOAD_GAME_BUTTON_TEXT);

        // Layout and borders :
        pan.setLayout(new GridLayout(2, 1));

        inputContainer.setLayout(new GridLayout(2, 2));
        inputContainer.setBorder(BorderFactory.createTitledBorder(Config.GAME_PARAMETERS_ZONE_TITLE));

        Dimension d = new Dimension(200, 25);
        gridSizeInput.setPreferredSize(d);
        playerNamesInput.setSize(d);

        actionContainer.setLayout(new FlowLayout());

        // Action Listeners :
        randomButton.addActionListener(play);
        customGameButton.addActionListener(play);
        fromSaveButton.addActionListener(load);

        // Building interface :
        inputContainer.add(gridLabel);
        inputContainer.add(gridSizeInput);
        inputContainer.add(playerLabel);
        inputContainer.add(playerNamesInput);

        actionContainer.add(randomButton);
        actionContainer.add(customGameButton);
        actionContainer.add(fromSaveButton);

        // Adding to buttons panel :
        pan.add(inputContainer);
        pan.add(actionContainer);

        this.setContentPane(pan);
        this.pack();
        this.repaint();
    }


    public void showPlayersSetup() {
        // main container of this interface :
        JPanel pan = new JPanel();
        JPanel inputContainer = new JPanel();
        JButton okButton = new JButton(Config.PLAY_BUTTON_TEXT);

        Players players = game.getPlayers();
        for(int i=0; i < players.getPlayerNumber(); i++) {
            inputContainer.add(new JLabel(Config.PLAYER_NAME_PROMPT_MESSAGE(i)));
            inputContainer.add(new JTextField());
            inputContainer.add(new JCheckBox());
            inputContainer.add(new JLabel("AI ? "));
        }

        // Layout and borders :
        pan.setLayout(new GridLayout(2, 1));

        inputContainer.setLayout(new GridLayout(players.getPlayerNumber(), 4));
        inputContainer.setBorder(BorderFactory.createTitledBorder(Config.PLAYERS_NAMES_ZONE_TITLE));

        // Action Listeners :
        okButton.addActionListener(play);

        // Building interface :

        pan.add(inputContainer);
        pan.add(okButton);

        this.setContentPane(pan);
        // this.invalidate();
        // this.revalidate();
        this.pack();
        this.repaint();
    }
    // TODO MASS DEBUG NEEDED, COLOR SWITCHED
    public void showGame(){
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


        this.setContentPane(pan);
        this.pack();
        this.repaint();

    }
}