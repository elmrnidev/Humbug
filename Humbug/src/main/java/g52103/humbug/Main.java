package g52103.humbug;


import g52103.humbug.controller.Controller;
import g52103.humbug.model.Game;
import g52103.humbug.view.text.View;

/**
 * The Main class start the controller to create the game.
 *
 * @author El Amrani Mounir (52103)
 */
public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller(new Game(), new View());       
        controller.startGame(0);
    }

}
