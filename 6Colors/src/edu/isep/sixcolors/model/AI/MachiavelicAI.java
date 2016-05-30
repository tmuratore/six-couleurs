package edu.isep.sixcolors.model.AI;

import edu.isep.sixcolors.model.Game;
import edu.isep.sixcolors.model.entity.Player;
import edu.isep.sixcolors.model.entity.TileColor;
import java.io.Serializable;
import java.util.ArrayList;

public class MachiavelicAI implements AIInterface, Serializable {

    @Override
    public TileColor colorChoice(Game game) {
        TileColorWithGain tcwg = colorChoiceWithGain(game);

        return tcwg.getTileColorChoice();
    }


    public TileColorWithGain colorChoiceWithGain(Game game) {
        // 1. Fetching resources :
        ArrayList<TileColor> availableTileColor = game.getAvailableTileColors();
        Game guineaPig = null;

        // the color to be picked :
        TileColor chosenColor = availableTileColor.get(0);
        // how much points we prevent the other players to win :
        int gainPrevented = 0;

        // 2. Picking the best color :
        for (int i = 0; i < game.getPlayers().getPlayerNumber(); i++) {
            // test against other players than me :
            if(i != game.getCurrentPlayerId()) {
                for (TileColor tc : availableTileColor) {
                    try {
                        guineaPig = game.deepCopy();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Player p = guineaPig.getPlayers().getPlayer(i);
                    int points = p.getPoints();

                    p.setTileColor(tc);
                    guineaPig.updateBoard(
                            p.getStartingTileCoords()[0],
                            p.getStartingTileCoords()[1],
                            p
                    );

                    int gain = p.getPoints() - points;

                    /*
                    // Debug : showing the other players possible options :
                    for (int k = 0; k < guineaPig.getBoard().getWidth(); k++) {
                        for (int j = 0; j < guineaPig.getBoard().getWidth(); j++) {
                            Tile t = guineaPig.getBoard().getTile(k, j);
                            if (t.getOwner() == null) {
                                System.out.print(t.getTileColor().getInitial() + " ");
                            } else {
                                System.out.print((Character.toUpperCase(t.getTileColor().getInitial())) + " ");
                            }
                        }
                        System.out.println();
                    }

                    // Debug : computing other players potential gain :
                    System.out.println("Gain : " + gain);
                    System.out.println();
                    System.out.println(); */

                    // KILL ALL HUMANS MODE : add condition "&& !p.isAi()"
                    if (gain > gainPrevented) {
                        chosenColor = tc;
                        gainPrevented = gain;
                    }
                }
            }
        }

        TileColorWithGain res = new TileColorWithGain(chosenColor, gainPrevented);

        return res;
    }
}