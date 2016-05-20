package edu.isep.sixcolors.view;

import edu.isep.sixcolors.model.*;

public interface Output {
    int promptFramedInt(String whatToAsk, String invalidEntryMessage, int min, int max);

    int promptFramedInt(String whatToAsk, int min, int max);

    String promptString(String whatToAsk);

    void printGameStatus(Board board, Player currentPlayer);

    GameColor promptColorChoice();

    void printGameErrorMessage(String message);

    void printInfoMessage(String message);


}
