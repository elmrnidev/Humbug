package elm.humbug.model;

/**
 * Ladybird is an animal can move two square in same times if the sqaure is not
 * occuped.
 *
 * @author elm (52103) 52103@etu.he2b.be
 */
public class Ladybird extends Animal {

    /**
     * Determines the position for this ladybird.
     *
     * @param positionOnBoard the position of animal
     */
    public Ladybird(Position positionOnBoard) {
        super(positionOnBoard);
    }

    /**
     * Constructor void.
     *
     */
    public Ladybird() {

    }

    /**
     * Determine the new position of the animal after the move in the direction
     * chosen.
     *
     * @param board the board with squares
     * @param direction the direction chosen
     * @param animals the animal
     * @return the new position for the animal
     */
    @Override
    public Position move(Board board, Direction direction, Animal... animals) {

        int nMoves = 0;
        Position actualPosition;
        Position nextPosition;
        do {
            actualPosition = this.getPositionOnBoard();
            nextPosition = super.moveOneCrowling(board, actualPosition,
                    direction, animals);
            nMoves++;
        } while (nMoves < 2 && nextPosition != null
                && !nextPosition.equals(actualPosition));

        if (nextPosition != null) {
            animalOnStar(nextPosition, board);
        }

        return nextPosition;
    }

    /**
     * The symbol of ladybird
     *
     * @return String
     */
    @Override
    public String toString() {
        return "C";
    }
}
