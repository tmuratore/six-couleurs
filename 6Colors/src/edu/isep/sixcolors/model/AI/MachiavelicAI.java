package edu.isep.sixcolors.model.AI;

import edu.isep.sixcolors.model.Game;
import edu.isep.sixcolors.model.entity.Player;
import edu.isep.sixcolors.model.entity.TileColor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class MachiavelicAI implements AIInterface, Serializable {

    @Override
    public TileColor colorChoice(Game game) {
        HashMap<TileColor, Integer> ccwg = colorChoiceWithGain(game);

        Set<TileColor> tileColors = ccwg.keySet();
        TileColor chosenColor = (TileColor) tileColors.toArray()[0];

        return chosenColor;

    }


    public HashMap<TileColor, Integer> colorChoiceWithGain(Game game) {
        // 1. Fetching resources :
        ArrayList<TileColor> availableTileColor = game.getAvailableTileColors();
        Game guineaPig = null;
        HashMap<TileColor, Integer> res = new HashMap<>();

        // the color to be picked :
        TileColor chosenColor = availableTileColor.get(0);
        // how much points we prevent the other players to win :
        int gainPrevented = 0;

        // 2. Picking the best color :
        for (int i = 0; i < game.getPlayers().getPlayerNumber(); i++) {
            try {
                guineaPig = game.deepCopy();
            } catch (Exception e) {
                e.printStackTrace();
            }

            for (TileColor tc : availableTileColor) {
                Player p = guineaPig.getPlayers().getPlayer(i);
                int points = p.getPoints();

                p.setTileColor(tc);
                guineaPig.updateBoard(
                        p.getStartingTileCoords()[0],
                        p.getStartingTileCoords()[1],
                        p
                );

                int gain = p.getPoints() - points;

                // KILL ALL HUMANS !
                if (gain > gainPrevented && !p.isAi()) {
                    chosenColor = tc;
                    gainPrevented = gain;
                }
            }

        }

        res.put(chosenColor, gainPrevented);

        return res;
    }
}