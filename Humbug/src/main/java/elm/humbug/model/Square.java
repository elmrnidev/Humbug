package elm.humbug.model;

/**
 * Square on the board. A sqaure has a type grass or star. A square
 * doesn't know where it is on the board.
 *
 * @author elm (52103) 52103@etu.he2b.be
 */
public class Square {

    private SquareType type;
    private boolean northWall;
    private boolean southWall;
    private boolean westWall;
    private boolean eastWall;

    /**
     * Constructor of Square on board.
     *
     * @param type Square can be grass or star
     */
    public Square(SquareType type) {
        this.type = type;
        this.eastWall = false;
        this.westWall = false;
        this.southWall = false;
        this.northWall = false;

    }

    /**
     * Constructor void.
     *
     */
    public Square() {

    }

    /**
     * Simple getter of type.
     *
     * @return type of Square
     */
    public SquareType getSquareType() {
        return type;
    }

    /**
     * Simple getter of type.
     *
     * @return type of Square
     */
    public SquareType getType() {
        return type;
    }

    /**
     * Sets the squareType of a square.
     *
     * @param type the type of a sqaure
     */
    public void setSquareType(SquareType type) {
        this.type = type;
    }

    /**
     * Sets the north Wall.
     *
     * @param northWall true or false
     */
    public void setNorthWall(boolean northWall) {
        this.northWall = northWall;
    }

    /**
     * Sets the south wall.
     *
     * @param southWall true or false
     */
    public void setSouthWall(boolean southWall) {
        this.southWall = southWall;
    }

    /**
     * Sets the west wall.
     *
     * @param westWall true or false
     */
    public void setWestWall(boolean westWall) {
        this.westWall = westWall;
    }

    /**
     * Sets the east wall.
     *
     * @param eastWall true or false
     */
    public void setEastWall(boolean eastWall) {
        this.eastWall = eastWall;
    }

    /**
     * Check if a wall is ont the square.
     *
     * @param direction the direction
     * @return true if has wall, false otherwise
     */
    public boolean hasWall(Direction direction) {

        switch (direction) {
            case NORTH:
                return this.northWall;
            case SOUTH:
                return this.southWall;
            case EAST:
                return this.eastWall;
            case WEST:
                return this.westWall;
        }

        return false;
    }
}
