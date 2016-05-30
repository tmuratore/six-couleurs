package edu.isep.sixcolors.model.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Board model : represents the board of the game.
 */
public class Board implements Serializable {

    private final int width;


    /**
     * Initialises a square board filled with random colors
     *
     * @param width of the board
     */
    public Board(int width) {
        this.tiles = new Tile[width][width];

        this.width = width;

        //Random Map Generation picking tiles colors from the TileColor enum
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                tiles[i][j] = new Tile(TileColor.random());
            }
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                insertNeighbours(i, j);
            }
        }
    }


    /**
     * Updates the board for a players turn
     *
     * @param tile tile from which to update
     * @param player Player who just picked a new color
     */

    public void update(Tile tile, Player player) {
        boolean updateNeighbours = false;

        // The tile being updated is *always* neighbouring the current player's territory.
        if (tile.getTileColor() == player.getTileColor() && tile.getOwner() != player) {
            tile.setOwner(player);
            updateNeighbours = true;
            player.addPoints();
        }
        // updating color of conquered tiles
        else if (tile.getTileColor() == player.getPreviousTileColor() && tile.getOwner() == player) {
            tile.setTileColor(player.getTileColor());
            tile.setOwner(player);
            updateNeighbours = true;
        }
        for (Tile nTile : tile.getNeighbors()) {
            if (
                    nTile.getOwner() != player ||    // not in my territory
                    nTile.getTileColor() != player.getTileColor()
                    ) {
                if (updateNeighbours) {
                    update(nTile, player);
                }
            }
        }
    }
    /**
     * Squares of the board
     */
    private final Tile[][] tiles;

    /**
     * Get a tile of the board
     *
     * @param line
     * @param row
     */
    public Tile getTile(int line, int row) {
        return this.tiles[line][row];
    }

    public Tile getTile(int[] coords) {
        return this.tiles[coords[0]][coords[1]];
    }

    /**
     * Get the whole tiles arrays
     *
     * @return Tile[][] tiles
     */
    public Tile[][] getTiles() {
        return this.tiles;
    }

    public int getWidth() {
        return width;
    }

    /**
     * Inserts a list of the tile's neighbors into it
     * @param x Abscissa of the tile whose neighbours are wanted
     * @param y Ordinates of the tile whose neighbours are wanted
     */
    private void insertNeighbours(int x, int y) {

        // Using ArrayList because we're not sure how many neighbours actually exist
        ArrayList<Tile> neighbours = new ArrayList<>();

        for (int k = -1; k <= 1; k++) {
            for (int l = -1; l <= 1; l++) {
                // Picking only direct neighbours (no diagonal neighbours)
                if ((k == 0 && l != 0) || (l == 0 && k != 0)) {
                    // Checking neighbour's existence
                    if (x + k >= 0 && x + k < this.tiles.length && y + l >= 0 && y + l < this.tiles.length) {
                        neighbours.add(this.getTile(x+k, y+l));
                    }
                }
            }
        }

        this.getTile(x, y).setNeighbors(neighbours.toArray(new Tile[neighbours.size()]));
    }

}
