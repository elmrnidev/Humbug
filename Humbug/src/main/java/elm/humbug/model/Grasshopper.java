package elm.humbug.model;

/**
 * Grasshoper is an animal can jump on the board if a animal is on the next
 * position or wall he pass jump over. He can also fall in the void.
 *
 * @author elm (52103) 52103@etu.he2b.be
 */
public class Grasshopper extends Animal {

    /**
     * Determines the position for this grasshopper.
     *
     * @param positionOnBoard the position of animal
     */
    public Grasshopper(Position positionOnBoard) {
        super(positionOnBoard);
    }

    /**
     * Constructor void.
     *
     */
    public Grasshopper() {

    }

    /**
     * Determine the new position of the butterfly after the jump in the given
     * board in a given direction. Check if the new position is on star or not.
     *
     * @param board the board with squares
     * @param direction the direction chosen
     * @param animals the array of animal
     * @return the new position for the animal
     */
    @Override
    public Position move(Board board, Direction direction, Animal... animals) {

        Position actualPosition = this.getPositionOnBoard();
        Position nextPosition = super.moveOneJumping(board, actualPosition,
                direction, animals);

        if (nextPosition != null) {
            animalOnStar(nextPosition, board);
        }
        super.setPositionOnBoard(nextPosition);
        return nextPosition;
    }

    /**
     * The symbol of grasshoper
     *
     * @return String
     */
    @Override
    public String toString() {
        return "S";
    }
}
