package elm.humbug.model;

/**
 * The Butterfly is an animal flies three squares (even above the void). If the
 * squre arrival is busy, it stops at the next one. he can jump over the wall
 * and over animals. He can also fall.
 *
 * @author elm (52103) 52103@etu.he2b.be
 */
public class Butterfly extends Animal {

    /**
     * Constructor of butterfly.
     *
     * @param positionOnBoard the position of animal
     */
    public Butterfly(Position positionOnBoard) {
        super(positionOnBoard);
    }

    /**
     * Void constructor of butterfly
     *
     */
    public Butterfly() {
        this(null);

    }

    /**
     * Determine the new position of the butterfly after the move in the given
     * board in a given direction. Check if the new position is on star or not.
     *
     * @param board the board with squares
     * @param direction the given direction
     * @param animals the array with animals
     * @return the new position for the animal moved
     */
    @Override
    public Position move(Board board, Direction direction, Animal... animals) {
        Position actualPosition = this.getPositionOnBoard();
        Position nextPosition = super.moveOneFlying(board, actualPosition,
                direction, animals);

        if (nextPosition != null) {
            animalOnStar(nextPosition, board);
        }
        super.setPositionOnBoard(nextPosition);

        return nextPosition;
    }

    /**
     * The symbol of buterfly.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "P";
    }
    
}
