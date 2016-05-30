package edu.isep.sixcolors.model;

import edu.isep.sixcolors.model.entity.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Random;

/**
 * Parent model class, regrouping all the necessary elements to reflect a games state.
 * It extends Observable so the view can update itself when the game has been modified
 * It implements Serializable to be able to create saves
 */
public class Game extends Observable implements Serializable {

    private static final long serialVersionUID = Config.GAME_VERSION_UNIQUE_ID;
    private Players players;
    private Board board;
    private GameState state = GameState.Menu;
    private int currentPlayerId;
    private Player winner;

    /**
     * Constructor
     */
    public Game() {
        state = GameState.Menu;
    }

    /**
     * Brute force resetting of the game, used when a serialized game is loaded
     * Notifies the view
     *
     * @param game the game that is to override the current one
     */
    public void setGame(Game game) {
        this.players = game.getPlayers();
        this.board = game.getBoard();
        this.state = game.getState();
        this.currentPlayerId = game.getCurrentPlayerId();
        this.winner = game.getWinner();

        setChanged();
        notifyObservers();
        clearChanged();
    }

    /**
     * Get the Players of Game
     *
     * @return the Players of the Game
     */
    public Players getPlayers() {
        return this.players;
    }

    /**
     * Set the Players of Game
     */
    public void setPlayers(Players players) {
        this.players = players;
    }

    /**
     * Get the Board of Game
     *
     * @return the Board of the Game
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Set the Board of Game
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Get the State of Game
     *
     * @return the State of the Game
     */
    public GameState getState() {
        return this.state;
    }

    /**
     * Set the State of Game
     * Notifies the View
     */
    public void setState(GameState state) {
        this.state = state;

        // Used to update the view :
        setChanged();
        notifyObservers();
        clearChanged();
    }

    /**
     * Get the winner of Game
     *
     * @return the winner of the Game
     */
    public Player getWinner() {
        return winner;
    }

    /**
     * Set the winner of Game
     */
    public void setWinner(Player winner) {
        this.winner = winner;
    }

    /**
     * Initialises the game for game loops
     * Only executed when in Game state.
     */
    public void initGame() {

        if (players.getPlayerNumber() > 0 && board.getWidth() > 0 && players.getPlayer(0).getName() != null) {

            setStartCoords();

            setStartColors();

            setStartOwnership();

            setOther();

            // First to play is player #0 :
            this.currentPlayerId = 0;
        }
    }

    /**
     * Sets the starting coordinates of each Player in Players
     * accordingly to the number of player
     */
    private void setStartCoords() {

        if (players.getPlayerNumber() == 2) {
            players.getPlayer(0).setStartingTile(board.getTile(0, 0));
            players.getPlayer(1).setStartingTile(board.getTile(board.getWidth() - 1, board.getWidth() - 1));
        } else {
            int max = board.getWidth() - 1;
            for (int i = 0; i < this.players.getPlayerNumber(); i++) {
                // Computing starting tile abscissa and ordinate using the player's id :
                int x = (i == 2 || i == 3) ? max : 0;
                int y = (i == 1 || i == 2) ? max : 0;
                players.getPlayer(i).setStartingTile(board.getTile(x, y));
            }
        }

    }

    /**
     * Sets the starting TileColors of each Player in Players
     */
    private void setStartColors() {

        // 1. Create a list of available colors and a random generator
        ArrayList<TileColor> availableTileColors = new ArrayList<>(Arrays.asList(TileColor.values()));
        Random random = new Random();

        for (int i = 0; i < players.getPlayerNumber(); i++) {

            // Get the player being iterated over :
            Player player = players.getPlayer(i);

            // Get a random, available color :
            TileColor tileColor = availableTileColors.get(random.nextInt(availableTileColors.size()));

            // 1. Set the player's color to the randomly chosen TileColor :
            player.setTileColor(tileColor);

            // 2. Update the board accordingly :
            player.getStartingTile().setTileColor(tileColor);

            // 3. Pop this color from the list of available colors :
            availableTileColors.remove(tileColor);
        }
    }

    /**
     * Initialises the ownership on the starting tiles of each Player of Players
     */
    private void setStartOwnership() {
        for (int i = 0; i < this.players.getPlayerNumber(); i++) {
            players.getPlayer(i).getStartingTile().setOwner(players.getPlayer(i));
            board.update(players.getPlayer(i).getStartingTile(), players.getPlayer(i));
        }
    }

    /**
     * Initialises the current, previous colors and initial points of each Player in Players
     * Runs a round of update to add the neighboring tiles of the same color
     */
    private void setOther() {

        // Setting current, previous colors and initial points of the players :
        for (int i = 0; i < this.players.getPlayerNumber(); i++) {
            TileColor tileColor = players.getPlayer(i).getStartingTile().getTileColor();
            players.getPlayer(i).setTileColor(tileColor);
            players.getPlayer(i).setPoints(1);

            // Updating board to give the players ownership of the tiles of their colors next to their starting point.
            board.update(players.getPlayer(i).getStartingTile(), players.getPlayer(i));
        }

    }

    /**
     * @return the current Player's Id
     */
    public int getCurrentPlayerId() {
        return currentPlayerId;
    }

    /**
     * @return the current Player in the Game
     */
    public Player getCurrentPlayer() {
        return this.getPlayers().getPlayer(currentPlayerId);
    }

    /**
     * Changes the currentPlayerId to the next one
     */
    public void nextPlayer() {
        if (currentPlayerId == players.getPlayerNumber() - 1) {
            currentPlayerId = 0;
        } else {
            currentPlayerId++;
        }
        setChanged();
        notifyObservers();
        clearChanged();
    }

    /**
     * Calls the update method of the Board model
     *
     * @param tile   the starting tile to update from
     * @param player the player who it is updating for
     */
    public void updateBoard(Tile tile, Player player) {
        board.update(tile, player);
    }

    /**
     * A method to call when there is a need to update the view when the game is not modified
     */
    public void customNotify() {
        setChanged();
        notifyObservers();
        clearChanged();
    }

    /**
     * Deep copy by serialization
     * Used to create guinea pigs to allow IA's to test their moves.
     * Uses serialization tools in the apache.common library.
     *
     * @throws Exception when the Serialization fails
     */
    public Game deepCopy() throws Exception {
        //Serialization of object
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(this);

        //De-serialization of object
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        InputStream buffer = new BufferedInputStream(bis);
        ObjectInputStream in = new ObjectInputStream(buffer);

        //

        return (Game) in.readObject();
    }

    /**
     * Get available colors
     */
    public ArrayList<TileColor> getAvailableTileColors() {
        ArrayList<TileColor> availableTileColors = new ArrayList<>(Arrays.asList(TileColor.values()));
        for (int i = 0; i < getPlayers().getPlayerNumber(); i++) {
            TileColor tileColor = getPlayers().getPlayer(i).getTileColor();
            if (availableTileColors.contains(tileColor)) {
                availableTileColors.remove(tileColor);
            } else {
                // an exception could be thrown here, bc running this else body would mean the model is broken
            }
        }
        return availableTileColors;
    }


}
