package edu.isep.sixcolors.view;

import edu.isep.sixcolors.model.Board;
import edu.isep.sixcolors.model.Player;
import edu.isep.sixcolors.model.TileColor;
import edu.isep.sixcolors.view.graphic.MainWindow;

public class Graphic implements Output {

    private MainWindow window;

    public Graphic() {
        this.window = new MainWindow();
    }

    /*
    public (Board board, Game game){

        this.game = game;
        this.setTitle("Six Colors Game");
        this.setSize(800, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.setContentPane(container);
        container.setLayout(new BorderLayout());

        gamePanel.setBackground(Color.BLACK);
        gamePanel.setLayout(new GridBagLayout());

        updateBoard(board);
        playerList.setBackground(Color.RED);

        colorButtons.setLayout(new FlowLayout());
        updateColorButtons();

        this.getContentPane().add(gamePanel, BorderLayout.CENTER);
        this.getContentPane().add(playerList, BorderLayout.EAST);
        this.getContentPane().add(colorButtons, BorderLayout.SOUTH);

        title.setText("Six Colors");
        Font police = new Font("Roboto", Font.BOLD, 16);
        title.setFont(police);
        title.setForeground(Color.BLACK);
        title.setHorizontalAlignment(JLabel.CENTER);
        this.getContentPane().add(title, BorderLayout.NORTH);

        this.setVisible(true);
    } */

    public void printGameStatus(Board board, Player currentPlayer) {
        this.window.printGameStatus(board, currentPlayer);
    }


    @Override
    public int promptFramedInt(String whatToAsk, String invalidEntryMessage, int min, int max) {
        // arrière plan noir
        // champ texte
        // parse int
        // test frame
        // OU select dans une liste (on verra)
        return 0;
    }

    public int promptFramedInt(String whatToAsk, int min, int max) {
        // idem avec custom error message
        return 0;
    }

    public String promptString(String whatToAsk) {
        return null;
    }

    public TileColor promptColorChoice() {
        // merge with update
        return null;
    }

    public void printGameErrorMessage(String message) {
        // wtf ?
    }

    public void printInfoMessage(String message) {
        // wtf ?
    }

    public void open() { }
}
