package elm.humbug.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

/**
 * The animal can move ont the board, a animal knows where it is. But he does
 * know if he is on a star square. he can fall if the square not exist or if is
 * not in the board.
 *
 * @author elm (52103) 52103@etu.he2b.be
 */
@JsonTypeInfo(use = Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
    @Type(value = Bumbelbee.class),
    @Type(value = Butterfly.class),
    @Type(value = Grasshopper.class),
    @Type(value = Ladybird.class),
    @Type(value = Snail.class),
    @Type(value = Spider.class),})
public abstract class Animal {

    private Position positionOnBoard;
    private boolean onStar;

    /**
     * Constructor of a animal.
     *
     * @param positionOnBoard the given position
     */
    public Animal(Position positionOnBoard) {
        this.positionOnBoard = positionOnBoard;
        this.onStar = false;
    }

    /**
     * Void constructor of animal.
     *
     */
    public Animal() {

    }

    /**
     * Determine the new position of the animal after the move in the given
     * board in a given direction.
     *
     * @param board the board with squares
     * @param direction the given direction
     * @param animals the array with animals
     * @return the new position for the animal moved
     */
    public abstract Position move(Board board, Direction direction,
            Animal... animals);

    /**
     * Gets the position of the animal on the board.
     *
     * @return the position on the board
     */
    public Position getPositionOnBoard() {
        return positionOnBoard;
    }

    /**
     * Checks if the animal is on a star square.
     *
     * @return true if the animal is on star, false otherwise
     */
    public boolean isOnStar() {
        return onStar;
    }

    /**
     * Sets the position of the animal on the board.
     *
     * @param positionOnBoard the position of animal
     */
    public void setPositionOnBoard(Position positionOnBoard) {
        this.positionOnBoard = positionOnBoard;
    }

    /**
     * Sets the value of star, if the animal is on the star or not.
     *
     * @param onStar the value of star true or false.
     */
    public void setOnStar(boolean onStar) {
        this.onStar = onStar;
    }

    /**
     * Check if the given position on the board is not occuped by a animal.
     *
     * @param nextPosition the given position
     * @param animals the given array of animal
     * @return true if the position is free, false otherwise
     */
    protected boolean isFreePosition(Position nextPosition, Animal... animals) {
        int i = 0;
        boolean isFree = true;
        while (i < animals.length && isFree && animals[i].getPositionOnBoard()
                != null) {
            if (animals[i].getPositionOnBoard().equals(nextPosition)
                    && !animals[i].isOnStar()) {
                isFree = false;
            }
            i++;
        }
        return isFree;
    }

    /**
     * Check if a animal is on star square or not. if is on star square change
     * the square to Grass.
     *
     * @param nextPosition the position on the board
     * @param board the board with squares
     */
    public void animalOnStar(Position nextPosition, Board board) {
        if (board.getSquareType(nextPosition) == SquareType.STAR) {
            setOnStar(true);
            board.removeStar(nextPosition);
        }
    }

    /**
     * Determine the position of the given animal when he move. The animal can
     * fall from the board, and can meet an animal or a wall.
     *
     * @param board the given board
     * @param actualPosition the actual given positon
     * @param direction the given direction
     * @param animals the given array of animals
     *
     * @return the new position of animal
     */
    protected Position moveOneCrowling(Board board, Position actualPosition,
            Direction direction, Animal... animals) {
        Position nextPosition = actualPosition.next(direction);

        if (board.getSquare(actualPosition).hasWall(direction)
                && board.isInside(actualPosition)
                || board.isInside(nextPosition)
                && board.getSquare(nextPosition)
                        .hasWall(direction.opposite())) {
            nextPosition = actualPosition;
        } else {
            if (!board.isInside(nextPosition)) {
                nextPosition = null;
            } else if (!isFreePosition(nextPosition, animals)) {
                nextPosition = actualPosition;
            }
        }

        setPositionOnBoard(nextPosition);

        return nextPosition;
    }

    /**
     * Determine the position of the given animal when he jumping on other
     * animal or on the next square. The animal can fall from the board, and can
     * jump on an animal or a wall.
     *
     * @param board the given board
     * @param actualPosition the actual given positon
     * @param direction the given direction
     * @param animals the given array of animals
     * @return the new position after move
     */
    protected Position moveOneJumping(Board board, Position actualPosition,
            Direction direction, Animal... animals) {

        Position nextPosition = actualPosition.next(direction);

        Boolean squareEmpty = false;

        do {
            if (!board.isInside(nextPosition)) {
                nextPosition = null;
            } else if (isFreePosition(nextPosition, animals)) {
                squareEmpty = true;
            } else {
                nextPosition = nextPosition.next(direction);
            }

        } while (nextPosition != null && !squareEmpty);

        setPositionOnBoard(nextPosition);

        return nextPosition;

    }

    /**
     * Determine the position of the given animal when he will flying on the
     * board. The animal can fall from the board, and can jump, fly on other
     * animal or a wall. he can fly over the void.
     *
     * @param board the given board
     * @param actualPosition the actual given positon
     * @param direction the given direction
     * @param animals the given array of animals
     * @return the new position
     */
    protected Position moveOneFlying(Board board, Position actualPosition,
            Direction direction, Animal... animals) {
        Position nextPosition = null;

        if (this instanceof Bumbelbee) {
            nextPosition = actualPosition.next(direction).next(direction);
        } else if (this instanceof Butterfly) {
            nextPosition = actualPosition.next(direction).next(direction)
                    .next(direction);
        }

        if (board.isInside(nextPosition)) {
            if (!isFreePosition(nextPosition, animals)) {
                nextPosition = moveOneJumping(board, nextPosition, direction,
                        animals);
            }
        } else {
            nextPosition = null;
        }

        setPositionOnBoard(nextPosition);

        return nextPosition;
    }
}
