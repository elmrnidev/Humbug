package elm.humbug.view.text;

import elm.humbug.model.Action;
import elm.humbug.model.Animal;
import elm.humbug.model.Board;
import elm.humbug.model.Direction;
import elm.humbug.model.Position;

/**
 * All the methods necessary for the operation of the game.
 *
 * @author elm (52103) 52103@etu.he2b.be
 */
public interface InterfaceView {

    /**
     * Display the board of the game. For all square show the type and if the
     * position is null print a space.
     *
     * @param board the given board
     * @param animals the given animal
     */
    void displayBoard(Board board, Animal... animals);

    /**
     * Ask the position desired by the user.
     *
     * @return the chosen position
     */
    Position askPosition();

    /**
     * Asks the direction desired by the user.
     *
     * @return the chosen direction
     */
    Direction askDirection();

     /**
     * Ask the action to the user.
     * 
     * @return the chosen action
     */
    Action askAction();
    
    /**
     * Display the given string received.
     *
     * @param message a string
     */
    void displayError(String message);

    /**
     * Display the number of remaining move.
     *
     * @param nbRemainingMoves a integer
     */
    void displayRemainingMoves(int nbRemainingMoves);

    /**
     * Display the number of the current Level.
     *
     * @param nLevel a integer
     */
    void displayNumberLevel(int nLevel);

    /**
     * Display the rules of the game.
     *
     */
    void displayRules();

    /**
     * Robust read integer if is not a integer show the given message.
     *
     * @param message String message
     * @return level
     */
    int robustIntegerRead(String message);

    /**
     * Display the given string message.
     *
     * @param message String
     */
    void displayMessage(String message);
    
    /**
     * Display the end of the game.
     * 
     */
    void displayEndGame();
    
   
}
