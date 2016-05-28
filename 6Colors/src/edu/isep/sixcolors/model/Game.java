package edu.isep.sixcolors.model;

import edu.isep.sixcolors.model.entity.Board;
import edu.isep.sixcolors.model.entity.Player;
import edu.isep.sixcolors.model.entity.Players;
import edu.isep.sixcolors.model.entity.TileColor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Random;

public class Game extends Observable implements Serializable{

    private static final long serialVersionUID = Config.GAME_VERSION_UNIQUE_ID;

    private Players players;
    private Board board;
    private GameState State = GameState.GridConfig;
    private int currentPlayerId;

    private Player winner;

    // Brute force game loading : used to load saved games :
    public void setGame(Game game){
        this.players = game.getPlayers();
        this.board = game.getBoard();
        this.State = game.getState();
        this.currentPlayerId = game.getCurrentPlayerId();
        this.winner = game.getWinner();
        setChanged();
        notifyObservers();
        clearChanged();
    }

    public Players getPlayers() {
        return this.players;
    }

    public void setPlayers(Players players) {
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public GameState getState() {
        return State;
    }

    public void setState(GameState state) {
        State = state;

        // Used to update the view :
        setChanged();
        notifyObservers();
        clearChanged();
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
        setChanged();
        notifyObservers();
        clearChanged();
    }

    /**
     * Initialises the game for game loops
     * Only executed when in Game state.
     */
    public void initGame(){

        // TODO Cleaner condition ?
        if (players.getPlayerNumber() > 0 && board.getWidth() > 0 && players.getPlayer(0).getName() != null){

            setStartCoords();

            setStartColors();

            setStartOwnership();

            setOther();

            // First to play is player #0 :
            this.currentPlayerId = 0;
        }
    }

    private void setStartCoords() {

        if (players.getPlayerNumber() == 2) {
            players.getPlayer(0).setStartingTileCoords(0, 0);
            players.getPlayer(1).setStartingTileCoords(board.getWidth() - 1, board.getWidth() - 1);
        } else {
            for (int i = 0; i < this.players.getPlayerNumber(); i++) {
                // Computing starting tile abscissa and ordinate using the player's id :
                int x = (i % 2 == 0) ? 0 : board.getWidth() - 1;
                int y = (i > 0 && i < 3) ? board.getWidth() - 1 : 0;
                players.getPlayer(i).setStartingTileCoords(x, y);
            }
        }

    }

    private void setStartColors(){

        // 1. Create a list of available colors and a random generator
        ArrayList<TileColor> availableTileColors = new ArrayList<>(Arrays.asList(TileColor.values()));
        Random random = new Random();

        for(int i = 0; i < players.getPlayerNumber(); i++) {

            // Get the player being iterated over :
            Player player = players.getPlayer(i);

            // Get a random, available color :
            TileColor tileColor = availableTileColors.get(random.nextInt(availableTileColors.size()));

            // 1. Set the player's color to the randomly chosen TileColor :
            player.setTileColor(tileColor);

            // 2. Update the board accordingly :
            board.getTile(player.getStartingTileCoords()).setTileColor(tileColor);

            // 3. Pop this color from the list of available colors :
            availableTileColors.remove(tileColor);
        }
    }

    public void setStartOwnership() {
        for (int i = 0; i < this.players.getPlayerNumber(); i++) {
            int[] startingTile = players.getPlayer(i).getStartingTileCoords();
            board.getTile(startingTile).setOwner(players.getPlayer(i));
            board.update(startingTile[0], startingTile[1], players.getPlayer(i));
        }
    }

    /**
     * Initialises the game
     */
    public void setOther() {

        // Setting current, previous colors and initial points of the players :
        for (int i = 0; i < this.players.getPlayerNumber(); i++) {
            int[] startingTile = players.getPlayer(i).getStartingTileCoords();
            TileColor tileColor = board.getTile(startingTile[0], startingTile[1]).getTileColor();
            players.getPlayer(i).setTileColor(tileColor);
            players.getPlayer(i).setPoints(1);

            // Updating board to give the players ownership of the tiles of their colors next to their starting point.
            board.update(startingTile[0], startingTile[1], players.getPlayer(i));
        }

    }


    public Player getCurrentPlayer() {
        return this.getPlayers().getPlayer(currentPlayerId);
    }

    public void nextPlayer() {
        if(currentPlayerId == players.getPlayerNumber() - 1) {
            currentPlayerId = 0;
        }
        else {
            currentPlayerId ++;
        }
        setChanged();
        notifyObservers();
        clearChanged();
    }

    public void updateBoard(int tileX, int tileY, Player player) {
        board.update(tileX, tileY, player);
    }



    public int getCurrentPlayerId() {
        return currentPlayerId;
    }
}
