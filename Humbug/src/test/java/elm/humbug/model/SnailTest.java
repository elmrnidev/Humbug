/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elm.humbug.model;

import static elm.humbug.model.SquareType.GRASS;
import static elm.humbug.model.SquareType.STAR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import elm.humbug.model.Animal;
import elm.humbug.model.Board;
import elm.humbug.model.Direction;
import elm.humbug.model.Position;
import elm.humbug.model.Snail;
import elm.humbug.model.Square;

/**
 * Test of snail.
 *
 * @author Pierre Bettens (pbt) <pbettens@he2b.be>
 */
public class SnailTest {

    private Board board;
    private Animal[] animals;

    @BeforeEach
    public void setUp() {
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), null},
            {null, new Square(GRASS), new Square(GRASS)},
            {null, null, new Square(STAR)}
        });
        animals = new Animal[]{
            new Snail(new Position(0, 0)),
            new Snail(new Position(1, 2))
        };
    }

    /**
     * Test of move method, of class Snail.
     */
    @Test
    public void testMove() {
        System.out.println("move_general");
        Snail instance = (Snail) animals[0];
        Position expResult = new Position(0, 1); //.next(Direction.EAST);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Snail.
     */
    @Test
    public void testMove_next_notfree() {
        System.out.println("move next case not free");
        Snail instance = (Snail) animals[0];
        animals[1].setPositionOnBoard(new Position(0, 1));
        Position expResult = new Position(0, 0); //don't move
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Snail.
     */
    @Test
    public void testMove_next_onstar() {
        System.out.println("move next on star");
        Snail instance = (Snail) animals[1];
        Position expResult = new Position(2, 2);
        Position result = instance.move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
        assertTrue(instance.isOnStar());
        assertEquals(GRASS, board.getSquareType(result));
    }

    /**
     * Test of move method, of class Snail.
     */
    @Test
    public void testMove_next_notinside_2() {
        System.out.println("move next case null");
        Snail instance = (Snail) animals[0];
        Position expResult = null; // move and fall
        Position result = instance.move(board, Direction.WEST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Snail.
     */
    @Test
    public void testMove_actual_hasNorthWall() {
        System.out.println("move to next case but has a north wall");
        Snail instance = (Snail) animals[0];
        Position expResult = new Position(0, 0); // Don't move
        board.getSquare(expResult).setNorthWall(true);
        Position result = instance.move(board, Direction.NORTH, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Snail.
     */
    @Test
    public void testMove_actual_hasSouthWall() {
        System.out.println("move next case has wall");
        Snail instance = (Snail) animals[0];
        Position expResult = new Position(0, 0); // Don't move
        board.getSquare(expResult).setSouthWall(true);
        Position result = instance.move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Snail.
     */
    @Test
    public void testMove_actual_hasWestWall() {
        System.out.println("move to next case but has a West wall");
        Snail instance = (Snail) animals[0];
        Position expResult = new Position(0, 0); // Don't move
        board.getSquare(expResult).setWestWall(true);
        Position result = instance.move(board, Direction.WEST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Snail.
     */
    @Test
    public void testMove_actual_hasEastWall() {
        System.out.println("move to next case but has a East wall");
        Snail instance = (Snail) animals[0];
        Position expResult = new Position(0, 0); // Don't move
        board.getSquare(expResult).setEastWall(true);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Snail.
     */
    @Test
    public void testMove_next_hasWall() {
        System.out.println("move next case has a wall");
        Snail instance = (Snail) animals[0];
        Position expResult = new Position(0, 0); // Don't move        
        Position nextPosition = new Position(0, 1);
        board.getSquare(nextPosition).setWestWall(true);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Snail.
     */
    @Test
    public void testMove_next_notinside_hasWALL() {
        System.out.println("move next case null but has a wall");
        Snail instance = (Snail) animals[0];
        Position expResult = new Position(0, 0); // Don't move
        board.getSquare(expResult).setNorthWall(true);
        Position result = instance.move(board, Direction.NORTH, animals);
        assertEquals(expResult, result);
    }
}
