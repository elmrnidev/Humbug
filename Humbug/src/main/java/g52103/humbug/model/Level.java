package g52103.humbug.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

/**
 * The Level gathers the elements necessary for the level of the game.
 *
 * @author El Amrani Mounir (52103) 52103@etu.he2b.be
 */
public class Level {

    private Board board;
    private Animal[] animals;
    private int nMoves;

    /**
     * Constructor of a level.
     *
     * @param board the board with squares
     * @param animals the array of animals
     * @param nMoves the integer of remaining move
     */
    private Level(Board board, Animal[] animals, int nMoves) {
        this.board = board;
        this.animals = animals;
        this.nMoves = nMoves;
    }

    /**
     * Constructor void.
     *
     */
    public Level() {

    }

    /**
     * Get the board of the game.
     *
     * @return the board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Ges the array of animals.
     *
     * @return the array of animals
     */
    public Animal[] getAnimals() {
        return animals;
    }

    /**
     * Gets the number of movement.
     *
     * @return the integer
     */
    public int getnMoves() {
        return nMoves;
    }

    /**
     * Get the board with animal for the given level.
     *
     * @param nbLevel the given integer
     * @return the game with board, animals and the number of move authorized
     */
    public static Level getLevel(int nbLevel) {
        return readLevel(nbLevel);
    }

    /**
     * Gets the board for the given level.
     *
     * @param n the given level
     * @return the board with animals and the move authorized.
     */
    private static Level readLevel(int n) {

        try {
            var objectMapper = new ObjectMapper();
            var inputStream = Level.class.getResourceAsStream(
                    "/data/level-" + n + ".json");
            var level = objectMapper.readValue(inputStream, Level.class);
            return level;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Le niveau " + n + " n'existe pas ! ");
            System.exit(0);
        }
       
        return null;
    }

}
