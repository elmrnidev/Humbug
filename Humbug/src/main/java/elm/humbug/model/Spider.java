package elm.humbug.model;

/**
 * Spider is an animal can move on the board if the next square is not occuped
 * by a animal or if the squares has not wall. He can fall on the void.
 *
 * @author elm (52103) 52103@etu.he2b.be
 */
public class Spider extends Animal {

    /**
     * Determines the position for this spider.
     *
     * @param positionOnBoard the position of animal
     */
    public Spider(Position positionOnBoard) {
        super(positionOnBoard);
    }

    /**
     * Constructor void.
     *
     */
    public Spider() {

    }

    /**
     * Determine the new position of the spider after the move in the given
     * board in a given direction. Check if the new position is on star or not.
     *
     * @param board the board with squares
     * @param direction the direction chosen
     * @param animals the animal
     * @return the new position for the animal
     */
    @Override
    public Position move(Board board, Direction direction, Animal... animals) {
        Position actualPosition;
        Position nextPosition;
        do {
            actualPosition = this.getPositionOnBoard();
            nextPosition = super.moveOneCrowling(board, actualPosition,
                    direction, animals);
        } while (nextPosition != null && !actualPosition.equals(nextPosition));

        if (nextPosition != null) {
            animalOnStar(nextPosition, board);
        }

        return nextPosition;
    }

    /**
     * The symbol of spider
     *
     * @return String
     */
    @Override
    public String toString() {
        return "A";
    }
}
