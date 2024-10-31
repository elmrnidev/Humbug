package g52103.humbug.model;

/**
 * Snail is an animal can move on the board if the next square is not occuped.
 * He can fall if is in the void.
 *
 * @author El Amrani Mounir (52103) 52103@etu.he2b.be
 */
public class Snail extends Animal {

    /**
     * Determines the position for this snail.
     *
     * @param positionOnBoard the position of a animal
     */
    public Snail(Position positionOnBoard) {
        super(positionOnBoard);
    }

    /**
     * Constructor void.
     *
     */
    public Snail() {

    }

    /**
     * Determine the new position of the animal after the move in the chosen
     * direction.
     *
     * @param board the board with squares
     * @param direction the direction chosen
     * @param animals the array with animals
     * @return the new position for the animal
     */
    @Override
    public Position move(Board board, Direction direction, Animal... animals) {
        Position actualPosition;
        actualPosition = this.getPositionOnBoard();
        Position nextPosition;
        nextPosition = super.moveOneCrowling(board, actualPosition, direction,
                animals);
        if (nextPosition != null) {
            animalOnStar(nextPosition, board);
        }

        return nextPosition;
    }

    /**
     * The symbol of snail.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "E";
    }
}
