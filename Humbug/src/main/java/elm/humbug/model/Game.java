package elm.humbug.model;

import static elm.humbug.model.LevelStatus.NOT_STARTED;
import java.util.ArrayList;
import java.util.List;

/**
 * The game gathers the elements necessary for the game to make a view.
 *
 * @author elm (52103) 52103@etu.he2b.be
 */
public class Game implements Model {

    private Board board;
    private Animal[] animals;
    private int remainingMoves;
    private int currentLevel;
    private LevelStatus levelStatus = NOT_STARTED;
    private List<Move> movesHistory = new ArrayList<>();
    
    /**
     * Gets the board of the game.
     *
     * @return the board with squares
     */
    @Override
    public Board getBoard() {
        return board;
    }

    /**
     * Gets the animal array.tea
     *
     * @return the array of animals
     */
    @Override
    public Animal[] getAnimals() {
        return animals;
    }

    /**
     * Get the level status. He can be NOT_STARTED,IN_PROGRESS,FAIL,WIN;
     *
     * @return the level status.
     */
    @Override
    public LevelStatus getLevelStatus() {
        return levelStatus;
    }

    /**
     * Gets the number of remaining moves.
     *
     * @return the integer
     */
    @Override
    public int getRemainingMoves() {
        return remainingMoves;
    }

    /**
     * Get the number of the current level.
     *
     * @return the integer
     */
    @Override
    public int getCurrentLevel() {
        return currentLevel;
    }

    /**
     *
     * Set the level status. He can be NOT_STARTED,IN_PROGRESS,FAIL,WIN;
     *
     *
     * @param levelStatus the levelStatus
     */
    @Override
    public void setLevelStatus(LevelStatus levelStatus) {
        this.levelStatus = levelStatus;
    }

    /**
     * Initialize the board, the remaining moes and the ainmal array for the
     * given level. Change de level status to In_PROGRRESS.
     *
     * @param level the number of given level
     */
    @Override
    public void startLevel(int level) {

        Level levelChosen = Level.getLevel(level);
        currentLevel = level;
        board = levelChosen.getBoard();
        animals = levelChosen.getAnimals();
        remainingMoves = levelChosen.getnMoves();
        levelStatus = LevelStatus.IN_PROGRESS;
    }

    @Override
    public void restart() {
        startLevel(currentLevel);
    }

    /**
     * Determine the new position of animal for the given direction. Move the
     * animal.
     *
     * @param position the given position
     * @param direction the given direction
     */
    @Override
    public void move(Position position, Direction direction) {

        if (this.levelStatus != LevelStatus.IN_PROGRESS) {
            throw new IllegalStateException("Le niveaux n'a pas commencer.");
        }

        boolean isAnimalPosition = false;
        int i = 0;
        while (i < animals.length) {
            if (animals[i].getPositionOnBoard().equals(position)
                    && !animals[i].isOnStar()) {

                Position actualPosition = animals[i].getPositionOnBoard();
                animals[i].move(board, direction, animals);
                Position newPosition = animals[i].getPositionOnBoard();
                isAnimalPosition = true;
                i = animals.length;

                if (actualPosition != newPosition) {
                    movesHistory.add(new Move(new Position(0,0),direction));
                    updateStatus();
                }
            } else {
                i++;
            }
        }

        if (!isAnimalPosition) {
            throw new IllegalArgumentException("Aucun animal a été choisie.");
        }
    }

    /**
     * Update the status of the level.
     *
     */
    private void updateStatus() {
        this.remainingMoves--;
        boolean gameIsOver = false;
        boolean isInProgress = false;
        int nbAnimalOnStar = 0;
        int i = 0;

        while (!gameIsOver && i < animals.length) {

            if (animals[i].getPositionOnBoard() == null) {
                gameIsOver = true;
                isInProgress = false;
            } else if (animals[i].getPositionOnBoard() != null
                    && !animals[i].isOnStar()) {
                isInProgress = true;
            } else if (animals[i].getPositionOnBoard() != null
                    && animals[i].isOnStar()) {
                nbAnimalOnStar++;
            }
            i++;
        }

        this.levelStatus = checkStatus(gameIsOver, isInProgress, nbAnimalOnStar);

    }

    /**
     * Check the status of the level with the given parameter.
     *
     * @param gameIsOver a boolean
     * @param isInProgress a boolean
     * @param nbAnimalOnStar a boolean
     * @return the levelStatus
     */
    private LevelStatus checkStatus(Boolean gameIsOver, Boolean isInProgress, int nbAnimalOnStar) {
        if (nbAnimalOnStar == animals.length) {
            this.levelStatus = LevelStatus.WIN;
        } else if (gameIsOver || this.remainingMoves == 0) {
            this.levelStatus = LevelStatus.FAIL;
        } else if (isInProgress) {
            this.levelStatus = LevelStatus.IN_PROGRESS;
        }
        return this.levelStatus;
    }

    /**
     * Remove the last move.
     * 
     */
    @Override
    public void undo(){
        restart();
        //je sais que ca ne fonctionne pas et que sa retourne deux fois en arriere.
        for (int i =  0; i < movesHistory.size()-2; i++) {
            Position position = movesHistory.get(movesHistory.size()-1).getPosition();
            Direction direction  = movesHistory.get(movesHistory.size()-1).getDirection();
            move(position,direction);
        }
    }
}
