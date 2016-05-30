package edu.isep.sixcolors;

import edu.isep.sixcolors.controller.GraphicInfo;
import edu.isep.sixcolors.controller.OutputInfo;
import edu.isep.sixcolors.model.Game;
import edu.isep.sixcolors.view.Window;

class SixColors {

    public static void main(String[] args) {

        /*** CREATING OBJECTS ***/
        // Model
        Game game = new Game();

        // Controller
        OutputInfo controller = new GraphicInfo(game);
        // The Interface OutputInfo was created to make the main controller Play independent of the display method.
        // So if the ConsoleInfo Class was developed hinging on a Console view class, Play should work anyway.

        // View
        Window window = new Window(controller, game);

        /*** BOOTSTRAPPING THE VIEW ***/

        game.addObserver(window);

        /*** HAVE FUN ! ***/
    }
}
