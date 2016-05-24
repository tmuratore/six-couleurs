package edu.isep.sixcolors.view.graphic;

import edu.isep.sixcolors.model.Board;
import edu.isep.sixcolors.model.Player;
import edu.isep.sixcolors.model.TileColor;
import edu.isep.sixcolors.view.Output;

import javax.swing.*;

public class MainWindow extends JFrame implements Output {

    private int currentPage = SETUP_PAGE;

    // This should remain public, to be used when calling setCurrentPage method
    public static final int SETUP_PAGE = 10;
    public static final int GAME_PAGE = 20;

    private int[] availablePages = {SETUP_PAGE, GAME_PAGE};


    public MainWindow() {
        this.setTitle("Six Colors Game");
        this.setSize(800, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        try {
            // todo currentPage
            this.setCurrentPage(GAME_PAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setVisible(true);
    }

    /**
     * Change the current page
     * Only changes the current page if the requested page is different from the current one
     * This method <b>should</b> be used with the constants defined in this class
     * @param newPage Use this class' constants ! *_PAGE
     * @throws Exception
     */
    public void setCurrentPage(int newPage) throws Exception {
        for(int page:this.availablePages) {
            if(newPage == page && currentPage != page) {
                currentPage = page;

                // todo avoid instantiation of pages objects each time the page is switched :
                switch(page) {
                    case GAME_PAGE:
                        this.setContentPane(new GamePage());
                    break;

                    case SETUP_PAGE:
                        // todo
                        // this.setContentPane(new SetupPage());
                    break;
                }

                this.invalidate();
                this.validate();
                this.repaint();

                // exiting method body:
                return;
            }
        }

        // if no page was found :
        throw new Exception("Page not found");
    }


    /********
     * OUTPUT INTERFACE IMPLEMENTATION
     ********/

    @Override
    public int promptFramedInt(String whatToAsk, String invalidEntryMessage, int min, int max) {
        return 0;
    }

    @Override
    public int promptFramedInt(String whatToAsk, int min, int max) {
        return 0;
    }

    @Override
    public String promptString(String whatToAsk) {
        return null;
    }

    @Override
    public void printGameStatus(Board board, Player currentPlayer) {
        if (this.currentPage != GAME_PAGE) {
            try {
                this.setCurrentPage(GAME_PAGE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // this typecast is secured by the code above :
        GamePage p = (GamePage) this.getContentPane();
        p.updateBoard(board);
    }

    @Override
    public TileColor promptColorChoice() {
        return null;
    }

    @Override
    public void printGameErrorMessage(String message) {

    }

    @Override
    public void printInfoMessage(String message) {

    }
}
