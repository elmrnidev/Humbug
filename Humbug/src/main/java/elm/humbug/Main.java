package elm.humbug;


import elm.humbug.controller.Controller;
import elm.humbug.model.Game;
import elm.humbug.view.text.View;

/**
 * The Main class start the controller to create the game.
 *
 * @author elm (52103)
 */
public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller(new Game(), new View());       
        controller.startGame(0);
    }

}
