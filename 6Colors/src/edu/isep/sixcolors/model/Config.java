package edu.isep.sixcolors.model;

import java.io.Serializable;

public class Config  implements Serializable {

    public static final String newLine = System.getProperty("line.separator");

    public static final String GAME_TITLE = "Six Colors Game";

    public static final String GRID_PROMPT_MESSAGE = "Size of the board : ";
    public static final int GRID_MIN = 5;
    public static final int GRID_MAX = 50;

    public static final String GAME_PARAMETERS_ZONE_TITLE =  "Game parameters";
    public static final String PLAYER_NB_PROMPT_MESSAGE = "Number of players : ";
    public static final int PLAYER_NB_MIN = 2;
    public static final int PLAYER_NB_MAX = 4;

    public static final String INVALID_ENTRY_TITLE = "Invalid Entry";

    public static final String OUT_OF_BOUNDS_GRID_CONFIG_MESSAGE = "The grid must be between " + GRID_MIN + " and " + GRID_MAX + " tiles wide";
    public static final String OUT_OF_BOUNDS_PLAYER_NB_CONFIG_MESSAGE = "There can be from " + PLAYER_NB_MIN + " to " + PLAYER_NB_MAX + " players";
    public static final String NUMBER_FORMAT_CONFIG_MESSAGE = "Please input valid numbers";


    public static final String PLAYERS_NAMES_ZONE_TITLE = "Players names";
    public static String PLAYER_NAME_PROMPT_MESSAGE(int number){
        return "Player " + (number+1) + "'s Name : ";
    }
    public static final String EMPTY_PLAYER_NAME_MESSAGE = "Please input a name for each player";


    public static String WINNER_SPLASH(String winner){return "Game is over, winner is " + winner + " !"; }

    //public static final String PARAM
    public static final String RANDOM_BOARD_BUTTON_TEXT = "Random Board";
    public static final String CUSTOM_BOARD_BUTTON_TEXT = "Custom Board";
    public static final String LOAD_GAME_BUTTON_TEXT = "Load Game";

    public static final String PLAY_BUTTON_TEXT = "Play";

    public static final String LOAD_SAVE_ACTION_NAME = "Select";

    public static final String EXIT_MESSAGE = "Would you like to exit ?";
    public static final String EXIT_AFTER_SAVE_MESSAGE = "Your game has been saved" + newLine + EXIT_MESSAGE;
    public static final String EXIT_TITLE = "Exit Six Colors";

    public static final long GAME_VERSION_UNIQUE_ID = 1337L + 42L;

}
