package g52103.humbug.model;

/**
 * Model group all the methods necessary for the operation of the game.
 *
 * @author El Amrani Mounir (52103) 52103@etu.he2b.be
 */
public interface Model {

    /**
     * Gets the board with squares
     *
     * @return the board with squares
     */
    Board getBoard();

    /**
     * Gets the array of animals
     *
     * @return tha array with animals
     */
    Animal[] getAnimals();

    /**
     * Gets the number of remaining moves.
     *
     * @return the integer of move
     */
    int getRemainingMoves();

    /**
     * Gets the number of the curren level.
     *
     * @return integer of level
     */
    int getCurrentLevel();

    /**
     * Start the game with the board and the animals corresponding to the level
     *
     * @param n intger
     */
    void startLevel(int n);
    
    /**
     * Gives the possibility to the player to restart the level.
     */
    void restart();
    
    /**
     * Gets the status of the current level.
     *
     * @return the status of the level
     */
    LevelStatus getLevelStatus();
    
    /**
     * Sets the status of the current level.
     * 
     * @param levelStatus the status of the level
     */
    void setLevelStatus(LevelStatus levelStatus);
    /**
     * Determines the new position when he moved in the given direction.
     *
     * @param position the given position of animal
     * @param direction the given direction
     */
    void move(Position position, Direction direction);
    
    /**
     * Remove the last move.
     */
    void undo();
}
