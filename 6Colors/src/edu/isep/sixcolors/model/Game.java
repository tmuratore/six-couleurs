package edu.isep.sixcolors.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Random;

public class Game extends Observable{
    private Players players;
    private Board board;
    private GameState State = GameState.GridConfig;
    private int currentPlayerId;

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
        setChanged();
        notifyObservers();
        clearChanged();
    }

    /**
     * Initialises the game for game loops
     * Only executed when in Game state.
     */
    public void initGame(){

        if (this.getState() == GameState.Game){

            setStartCoords();

            setStartColors();

            setStartOwnership();

            setOther();

            // First to play is player #0 :
            this.currentPlayerId = 0;
        }

    }

    private void setStartCoords(){

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
        ArrayList<TileColor> availableTileColors = new ArrayList<>(Arrays.asList(TileColor.values()));
        Random randomGen = new Random();

        //Initial pass to set StartingTileColor for each player
        for(int i = 0; i < players.getPlayerNumber(); i++) {
            Player player = null;
            try {
                player = players.getPlayer(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            player.setTileColor(
                    board.getTile(player.getStartingTileCoords()).getTileColor()
            );
        }

        // Re-pass to prevent two players from getting the same initial color
        for (int i = 0; i < this.players.getPlayerNumber(); i++) {

            TileColor tileColor = null;
            try {
                tileColor = board.getTile(players.getPlayer(i).getStartingTileCoords()).getTileColor();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (availableTileColors.contains(tileColor)) {
                // If the tileColor is available, keep it and remove it from the available colors.
                availableTileColors.remove(tileColor);
            } else {
                // If current tileColor is not available, pick a random one from the available, set it then remove this tileColor from the available.
                int randomIndex = randomGen.nextInt(availableTileColors.size());
                TileColor randomTileColor = availableTileColors.get(randomIndex);
                board.getTile(players.getPlayer(i).getStartingTileCoords()).setTileColor(randomTileColor);
                availableTileColors.remove(randomTileColor);
            }
        }
    }

    public void setStartOwnership(){
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
}
