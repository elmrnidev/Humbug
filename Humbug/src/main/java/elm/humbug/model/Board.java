package elm.humbug.model;

import static elm.humbug.model.SquareType.GRASS;
import static elm.humbug.model.SquareType.STAR;

/**
 * The board of squares, a square can be a grass or star otherwise null. The
 * game board does not know the different animals walking on it.
 *
 * @author elm (52103) 52103@etu.he2b.be
 */
public class Board {

    private Square[][] squares;

    /**
     * Void constructor of board.
     *
     */
    public Board() {

    }

    /**
     * Constructor of a board.
     *
     * @param squares the given array of squares.
     */
    Board(Square[][] squares) {
        this.squares = squares;
    }

    /**
     * Gets the number of row in this board.
     *
     * @return the number of row
     */
    public int getNbRow() {
        return squares.length;
    }

    /**
     * Gets the number of column in this board.
     *
     * @return the number of column
     */
    public int getNbColumn() {
        return squares[0].length;
    }

    /**
     * Get the specific type of square on the given position.
     *
     * @param pos the position
     * @return the square in thr given position
     */
    public Square getSquare(Position pos) {
        return squares[pos.getRow()][pos.getColumn()];
    }

    /**
     * Gets the squares array.
     *
     * @return the array of square
     */
    public Square[][] getSquares() {
        return squares;
    }

    /**
     * Create an inital board(level 1) with square and makes a new instance of
     * board.
     *
     * @return new board with square.
     */
    public static Board getInitialBoard() {
        Square[][] boardSquare = new Square[][]{
            {new Square(GRASS), new Square(GRASS), null},
            {null, new Square(GRASS), new Square(GRASS)},
            {null, null, new Square(STAR)}
        };

        return new Board(boardSquare);
    }

    /**
     * Gives the type of square in the given position.
     *
     * @param pos the given position of square.
     * @return the type of a square
     */
    public SquareType getSquareType(Position pos) {
        if (!isInside(pos)) {
            throw new IllegalArgumentException(
                    "La position n'est pas sur le plateau. Position choisie : "
                    + pos);
        }
        return squares[pos.getRow()][pos.getColumn()].getSquareType();
    }

    /**
     * Remove the star in the given position.
     *
     * @param pos the given position
     */
    public void removeStar(Position pos) {
        if (!isInside(pos)) {
            throw new IllegalArgumentException(
                    "La position n'est pas sur le plateau. Position choisie : "
                    + pos
            );
        }
        squares[pos.getRow()][pos.getColumn()].setSquareType(GRASS);
    }

    /**
     * Checks if the given position is in the board and the given position is
     * not null.
     *
     * @param pos the given position
     * @return true if the position is on the board and not null, otherwise
     * false
     */
    public boolean isInside(Position pos) {
        if (pos == null) {
            throw new IllegalArgumentException("La position est nulle.");
        }
        boolean rowAndColumnCheck = pos.getRow() >= 0
                && pos.getRow() < squares.length
                && pos.getColumn() >= 0
                && pos.getColumn() < squares[0].length;

        return rowAndColumnCheck
                && squares[pos.getRow()][pos.getColumn()] != null;
    }

}
