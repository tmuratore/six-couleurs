package edu.isep.sixcolors.controller;

/**
 * View choice interface with the main controller Play
 */
public interface OutputInfo {

    /**
     * Generic method to assure a non view specific input to Play
     *
     * @param sourceText          The text of the pressed button
     * @param boardSize           The size of the board
     * @param playerNb            The number of players
     * @param playerName          A table of the player's names
     * @param playerType          A table of the player' what the player is (what AI or human)
     * @param sourceActionCommand The text sent as an Action Command through the button, must not be mixed up with sourceText
     */
    void returnGameParamSet(String sourceText, int boardSize, int playerNb, String[] playerName, String[] playerType, String sourceActionCommand);

}
