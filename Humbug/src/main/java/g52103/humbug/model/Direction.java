package g52103.humbug.model;

/**
 * Direction represents the displacement of an object in the Carthesian
 * coordinate system. North, south, east and west can be use for a displacement.
 *
 * @author El Amrani Mounir (52103) 52103@etu.he2b.be
 */
public enum Direction {
    NORTH(-1, 0), SOUTH(1, 0), EAST(0, 1), WEST(0, -1);

    private final int deltaRow;
    private final int deltaColumn;

    /**
     * Constructor of a direction.
     *
     * @param deltaRow the given delta of row
     * @param deltaColumn the given delta of column
     */
    private Direction(int deltaRow, int deltaColumn) {
        this.deltaRow = deltaRow;
        this.deltaColumn = deltaColumn;
    }

    /**
     * Simple getter of deltaRow.
     *
     * @return the value of the delta row
     */
    public int getDeltaRow() {
        return deltaRow;
    }

    /**
     * Simple getter of deltaColumn.
     *
     * @return the value of the delta column
     */
    public int getDeltaColumn() {
        return deltaColumn;
    }

    /**
     * Check the current direction and return the opposite direction of the
     * current direction.
     *
     * @return the direction
     */
    public Direction opposite() {

        switch (this) {
            case NORTH:
                return SOUTH;
            case SOUTH:
                return NORTH;
            case EAST:
                return WEST;
            case WEST:
                return EAST;
        }

        return null;
    }
}
