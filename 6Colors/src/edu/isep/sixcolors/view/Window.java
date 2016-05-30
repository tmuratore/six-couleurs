package edu.isep.sixcolors.view;

import edu.isep.sixcolors.controller.OutputInfo;
import edu.isep.sixcolors.model.Config;
import edu.isep.sixcolors.model.Game;
import edu.isep.sixcolors.model.entity.Players;
import edu.isep.sixcolors.view.customGrid.ColorPicker;
import edu.isep.sixcolors.view.customGrid.CustomGrid;
import edu.isep.sixcolors.view.game.ColorButtons;
import edu.isep.sixcolors.view.game.Grid;
import edu.isep.sixcolors.view.game.PlayerList;
import edu.isep.sixcolors.view.listener.Exit;
import edu.isep.sixcolors.view.listener.Load;
import edu.isep.sixcolors.view.listener.Save;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Represents the main game window
 */
public class Window extends JFrame implements Observer {


    private ActionListener outputInfo; // Controller interactions
    private Game game; // Model interactions

    public Window(OutputInfo outputInfo, Game game) {
        // accesses to the controller and model
        this.game = game;
        this.outputInfo = (ActionListener) outputInfo;

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
                showCustomGridSetup();
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
        newLocalGame.addActionListener(outputInfo);

        JButton loadSavedGame = new JButton(Config.LOAD_GAME_BUTTON_TEXT);
        loadSavedGame.addActionListener(new Load(game));

        JButton exit = new JButton(Config.EXIT_BUTTON_TEXT);
        exit.addActionListener(outputInfo);

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
        JButton okButton = new JButton(Config.ONTO_PLAYER_NAMES_BUTTON_TEXT);

        // Text fields :
        JLabel gridLabel = new JLabel(Config.GRID_PROMPT_MESSAGE);
        JTextField gridSizeInput = new JTextField(Config.GRID_MIN);
        JLabel playerLabel = new JLabel(Config.PLAYER_NB_PROMPT_MESSAGE);
        JTextField playerNamesInput = new JTextField(Config.PLAYER_NB_MIN);

        // Layout and borders :
        pan.setLayout(new GridLayout(2, 1));

        inputContainer.setLayout(new GridLayout(2, 2));
        inputContainer.setBorder(BorderFactory.createTitledBorder(Config.GAME_PARAMETERS_ZONE_TITLE));

        Dimension d = new Dimension(200, 25);
        gridSizeInput.setPreferredSize(d);
        playerNamesInput.setSize(d);


        // Action Listeners :
        okButton.addActionListener(outputInfo);

        // Building interface :
        inputContainer.add(gridLabel);
        inputContainer.add(gridSizeInput);
        inputContainer.add(playerLabel);
        inputContainer.add(playerNamesInput);

        // Adding to buttons panel :
        pan.add(inputContainer);
        pan.add(okButton);

        this.setContentPane(pan);
        this.pack();
        this.repaint();
    }


    public void showPlayersSetup() {

        String[] playerTypes = {"Human", "Dumb AI", "Greedy AI", "Machiavelic AI", "Clever AI"};

        // main container of this interface :
        JPanel pan = new JPanel();
        JPanel inputContainer = new JPanel();
        JPanel actionContainer = new JPanel();
        JButton customBoardButton = new JButton(Config.CUSTOM_BOARD_BUTTON_TEXT);
        JButton randomBoardButton = new JButton(Config.RANDOM_BOARD_BUTTON_TEXT);

        Players players = game.getPlayers();



        for(int i=0; i < players.getPlayerNumber(); i++) {
            inputContainer.add(new JLabel(Config.PLAYER_NAME_PROMPT_MESSAGE(i)));
            inputContainer.add(new JTextField());
            inputContainer.add(new JComboBox(playerTypes));
        }



        // Layout and borders :
        pan.setLayout(new GridLayout(2, 1));

        inputContainer.setLayout(new GridLayout(players.getPlayerNumber(), 4));
        inputContainer.setBorder(BorderFactory.createTitledBorder(Config.PLAYERS_NAMES_ZONE_TITLE));

        actionContainer.setLayout(new FlowLayout());

        // Action Listeners :
        randomBoardButton.addActionListener(outputInfo);
        customBoardButton.addActionListener(outputInfo);

        // Building interface :

        actionContainer.add(randomBoardButton);
        actionContainer.add(customBoardButton);


        pan.add(inputContainer);
        pan.add(actionContainer);

        this.setContentPane(pan);

        this.pack();
        this.repaint();
    }

    private void showCustomGridSetup(){
        JPanel pan = new JPanel();
        JPanel grid = new CustomGrid(game, outputInfo);
        JPanel ColorButtons = new ColorPicker(outputInfo);
        JPanel gameActionContainer = new JPanel();
        JPanel actionContainer = new JPanel();

        JButton okButton = new JButton("Play");
        JButton saveButton = new JButton("Save");

        Save save = new Save(game);
        Load load = new Load(game);
        Exit exit = new Exit();

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

        saveButton.addActionListener(save);
        okButton.addActionListener(outputInfo);

        menuBar.add(saveItem);
        menuBar.add(loadItem);
        menuBar.add(exitItem);

        this.setJMenuBar(menuBar);

        pan.setLayout(new BorderLayout());
        actionContainer.setLayout(new GridLayout(3,1));
        gameActionContainer.setLayout(new FlowLayout());

        gameActionContainer.add(okButton);
        gameActionContainer.add(saveButton);

        actionContainer.add(ColorButtons);
        actionContainer.add(gameActionContainer);

        pan.add(grid, BorderLayout.CENTER);
        pan.add(actionContainer, BorderLayout.SOUTH);

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
        JPanel colorButtons = new ColorButtons(game, outputInfo);

        pan.setLayout(new BorderLayout());

        pan.add(grid, BorderLayout.CENTER);
        pan.add(playerList, BorderLayout.EAST);
        pan.add(colorButtons, BorderLayout.SOUTH);


        this.setContentPane(pan);
        this.pack();
        this.repaint();
    }

    private void showEnd() {
        JPanel pan = new JPanel();
        pan.setLayout(new GridLayout(2, 1, 20, 20));

        Exit exit = new Exit();

        JMenuBar menuBar = new JMenuBar();

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setAccelerator(KeyStroke.getKeyStroke('e'));
        exitItem.addActionListener(exit);
        menuBar.add(exitItem);

        JButton mainMenuButton = new JButton("Main Menu");

        Font font = new Font("Roboto", Font.BOLD, 26);

        mainMenuButton.addActionListener(outputInfo);
        pan.add(mainMenuButton);

        JLabel label = new JLabel(Config.WINNER_SPLASH(game.getWinner().getName()));
        label.setFont(font);

        EmptyBorder border = new EmptyBorder(20,20,20,20);
        pan.add(label);
        pan.add(mainMenuButton);
        pan.setBorder(border);

        this.setJMenuBar(menuBar);
        this.setContentPane(pan);
        this.pack();
        this.repaint();
    }


    /**
     * FakeButton to force an immediate update of the view when the player is an AI
     * @return button the fake button
     */
    private JButton fakeButton() {
        JButton button = new JButton();
        button.addActionListener(outputInfo);
        this.add(button);
        return button;
    }
}