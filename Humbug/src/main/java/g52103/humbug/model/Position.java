package g52103.humbug.model;

/**
 * Positons represents the position of the object in the board. The position is
 * determined by a line and a column.
 *
 * @author El Amrani Mounir (52103) 52103@etu.he2b.be
 */
public class Position {

    private int row;
    private int column;

    /**
     * Constructor of a Position on board.
     *
     * @param row the given integer for row position
     * @param column the given integer for column position
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Void vonstructor of position.
     *
     */
    public Position() {

    }

    /**
     * Simple getter of row.
     *
     * @return the number of row
     */
    public int getRow() {
        return row;
    }

    /**
     * Simple getter of column.
     *
     * @return the number of column
     */
    public int getColumn() {
        return column;
    }

    /**
     * Determine the new position with the given direction.
     *
     * @param direction the given direction
     * @return the new position
     */
    public Position next(Direction direction) {
        int newColumn = this.getColumn() + direction.getDeltaColumn();
        int newRow = this.getRow() + direction.getDeltaRow();
        Position nextPosition = new Position(newRow, newColumn);
        return nextPosition;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.row;
        hash = 89 * hash + this.column;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        if (this.row != other.row) {
            return false;
        }
        return this.column == other.column;
    }

    @Override
    public String toString() {
        return "Position : " + "(" + row + ", " + column + ')';
    }

}
