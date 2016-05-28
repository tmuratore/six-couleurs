package edu.isep.sixcolors.model;

public class Config {
    public static final String GRID_PROMPT_MESSAGE = "Size of the board : ";
    public static final int GRID_MIN = 5;
    public static final int GRID_MAX = 50;

    public static final String PLAYER_NB_PROMPT_MESSAGE = "Number of players : ";
    public static final int PLAYER_NB_MIN = 2;
    public static final int PLAYER_NB_MAX = 4;

    public static final String OUT_OF_BOUNDS_GRID_CONFIG_MESSAGE = "The grid must be between" + GRID_MIN + " and " + GRID_MAX + " . ";
    public static final String OUT_OF_BOUNDS_PLAYER_NB_CONFIG_MESSAGE = "There can be from " + PLAYER_NB_MIN + " to " + PLAYER_NB_MAX + " players . ";
    public static final String OUT_OF_BOUNDS_CONFIG_TITLE = "Invalid Entry";

    public static String PLAYER_NAME_PROMPT_MESSAGE(int number){
        return "Player " + (number+1) + "'s Name : ";
    }

    public static final String INVALID_TYPE_CONFIG_MESSAGE = "Invalid Entry, you must enter numerical values";

    public static final String [] whatToAsk = {GRID_PROMPT_MESSAGE, PLAYER_NB_PROMPT_MESSAGE};
    public static final int [] max = {GRID_MAX, PLAYER_NB_MAX};
    public static final int [] min = {GRID_MIN, PLAYER_NB_MIN};

    public static String WINNER_SPLASH(String winner){return "Game is over, winner is " + winner + " !"; }

}
