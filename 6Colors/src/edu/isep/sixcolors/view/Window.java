package edu.isep.sixcolors.view;

import edu.isep.sixcolors.controller.Play;
import edu.isep.sixcolors.model.Config;
import edu.isep.sixcolors.model.Game;
import edu.isep.sixcolors.model.entity.Players;
import edu.isep.sixcolors.view.game.ColorButtons;
import edu.isep.sixcolors.view.game.Grid;
import edu.isep.sixcolors.view.game.PlayerList;
import edu.isep.sixcolors.view.listener.Exit;
import edu.isep.sixcolors.view.listener.Load;
import edu.isep.sixcolors.view.listener.Save;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
        this.setTitle(Config.GAME_WINDOW_TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        // initial state :
        showMainMenu();

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
                showMainMenu();
                break;
            case GridConfig:
                showLocalGameSetup();
                break;
            case NameConfig:
                showPlayersSetup();
                break;
            case CustomGrid:
                break;
            case Game:
                if (game.getCurrentPlayer().isAi()){
                    fakeButton().doClick();
                }else{
                    // To you the adventurer who dares to try to display a game between AI only, your journey begins here. Good luck from all the team.
                    showGame();
                }
                break;
            case End:
                showEnd();
                break;
        }

    }

    public void showMainMenu() {
        // Main panel :
        JPanel pan = new JPanel();
        EmptyBorder border = new EmptyBorder(50,50,50,50);
        pan.setBorder(border);

        //this.setSize(700,500);
        pan.setLayout(new GridLayout(4,1,50,50));

        // Creating elements :
        Font f = new Font("Roboto", Font.BOLD, 40);

        JLabel title = new JLabel(Config.GAME_NAME, JLabel.CENTER);
        title.setFont(f);

        JButton newLocalGame = new JButton(Config.NEW_LOCAL_GAME_BUTTON_TEXT);
        newLocalGame.addActionListener(play);

        JButton loadSavedGame = new JButton(Config.LOAD_GAME_BUTTON_TEXT);
        loadSavedGame.addActionListener(new Load(game));

        JButton exit = new JButton(Config.EXIT_BUTTON_TEXT);
        exit.addActionListener(play);

        // building interface :
        pan.add(title);
        pan.add(newLocalGame);
        pan.add(loadSavedGame);
        pan.add(exit);

        this.setContentPane(pan);
        this.pack();
        this.repaint();
    }

    public void showLocalGameSetup() {
        // main container of this interface :
        JPanel pan = new JPanel();
        JPanel inputContainer = new JPanel();
        JPanel actionContainer = new JPanel();

        // Text fields :
        JLabel gridLabel = new JLabel(Config.GRID_PROMPT_MESSAGE);
        JTextField gridSizeInput = new JTextField(Config.GRID_MIN);
        JLabel playerLabel = new JLabel(Config.PLAYER_NB_PROMPT_MESSAGE);
        JTextField playerNamesInput = new JTextField(Config.PLAYER_NB_MIN);

        // buttons :
        JButton randomButton = new JButton(Config.RANDOM_BOARD_BUTTON_TEXT);
        JButton customGameButton = new JButton(Config.CUSTOM_BOARD_BUTTON_TEXT);

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

        // Building interface :
        inputContainer.add(gridLabel);
        inputContainer.add(gridSizeInput);
        inputContainer.add(playerLabel);
        inputContainer.add(playerNamesInput);

        actionContainer.add(randomButton);
        actionContainer.add(customGameButton);

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

        this.pack();
        this.repaint();
    }

    public void showGame(){
        Save save = new Save(game);
        Load load = new Load(game);
        Exit exit = new Exit();
        JPanel pan = new JPanel();

        JPanel grid = new Grid(game);
        JMenuBar menuBar = new JMenuBar();
        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.setAccelerator(KeyStroke.getKeyStroke('s'));

        JMenuItem loadItem = new JMenuItem("Load");
        loadItem.setAccelerator(KeyStroke.getKeyStroke('l'));

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setAccelerator(KeyStroke.getKeyStroke('e'));

        saveItem.addActionListener(save);
        loadItem.addActionListener(load);
        exitItem.addActionListener(exit);

        menuBar.add(saveItem);
        menuBar.add(loadItem);
        menuBar.add(exitItem);

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
        //this.revalidate();

    }

    private void showEnd(){
        JLabel label = new JLabel(Config.WINNER_SPLASH(game.getWinner().getName()));
        Exit exit = new Exit();
        JMenuBar menuBar = new JMenuBar();

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setAccelerator(KeyStroke.getKeyStroke('e'));
        exitItem.addActionListener(exit);
        menuBar.add(exitItem);

        Font font = new Font("Roboto", Font.BOLD, 26);
        EmptyBorder border = new EmptyBorder(20,10,20,10);
        label.setBorder(border);
        label.setFont(font);

        this.setJMenuBar(menuBar);
        this.setContentPane(label);
        this.pack();
        this.repaint();
    }

    /**
     * FakeButton to force an immediate update of the view when the player is an AI
     * @return button the fake button
     */
    private JButton fakeButton() {
        JButton button = new JButton();
        button.addActionListener(play);
        this.add(button);
        return button;
    }
}