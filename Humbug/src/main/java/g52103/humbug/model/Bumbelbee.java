package g52103.humbug.model;

/**
 * The bumblebee is an animal flies two squares even above the void. If the
 * square arrival is busy, it stops at the next one. He can fly over the wall
 * and over animals. He can also fall.
 *
 * @author El Amrani Mounir (52103) 52103@etu.he2b.be
 */
public class Bumbelbee extends Animal {

    /**
     * Constructor of a animal.
     *
     * @param positionOnBoard the given position
     */
    public Bumbelbee(Position positionOnBoard) {
        super(positionOnBoard);
    }

    /**
     * Void constructor of animal.
     *
     */
    public Bumbelbee() {
        this(null);

    }

    /**
     * Determine the new position of the bumbelbee after the move in the given
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
     * The symbol of bumbelbee
     *
     * @return String
     */
    @Override
    public String toString() {
        return "B";
    }

}
