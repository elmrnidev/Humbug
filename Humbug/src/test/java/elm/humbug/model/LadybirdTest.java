/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elm.humbug.model;

import static elm.humbug.model.SquareType.GRASS;
import static elm.humbug.model.SquareType.STAR;
import org.junit.jupiter.api.Test;

import elm.humbug.model.Animal;
import elm.humbug.model.Board;
import elm.humbug.model.Direction;
import elm.humbug.model.Ladybird;
import elm.humbug.model.Position;
import elm.humbug.model.Snail;
import elm.humbug.model.Square;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 * Test of ladybird.
 *
 * @author elm (52103) <52103@etu.he2b.be>
 */
public class LadybirdTest {

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
            new Ladybird(new Position(0, 0)),
            new Snail(new Position(1, 2))
        };
    }

    /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMove() {
        System.out.println("move and fall");
        Ladybird instance = (Ladybird) animals[0];
        Position expResult = null; // fall
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testOneMove() {
        System.out.println("move once in a large board  ");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(STAR), 
                new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS), null},
            {null, null, new Square(STAR), null}
        });
        animals = new Animal[]{
            new Ladybird(new Position(0, 0))};
        Ladybird instance = (Ladybird) animals[0];
        Position expResult = new Position(0, 2);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMove_endline() {
        System.out.println("move end line and fall");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), null},
            {null, new Square(GRASS), new Square(GRASS)},
            {null, null, new Square(STAR)}
        });
        Ladybird instance = (Ladybird) animals[0];
        Position expResult = null;
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMove_tootheranimal() {
        System.out.println("move to other animal");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS)},
            {null, null, new Square(STAR)}
        });
        animals[1] = new Snail(new Position(0, 2));
        Ladybird instance = (Ladybird) animals[0];
        Position expResult = new Position(0, 1);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMove_next_notfree() {
        System.out.println("move next case not free");
        Ladybird instance = (Ladybird) animals[0];
        animals[1].setPositionOnBoard(new Position(0, 1));
        Position expResult = new Position(0, 0); //don't move
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMove_next_notinside() {
        System.out.println("move next case null and fall");
        Ladybird instance = (Ladybird) animals[0];
        Position expResult = null; // fall
        Position result = instance.move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMove_passOnStar() {
        System.out.println("move and pass on star without win");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(STAR), new Square(GRASS), 
                new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS), null},
            {null, null, new Square(STAR), null}
        });
        animals = new Animal[]{
            new Ladybird(new Position(0, 0)),
            new Snail(new Position(0, 3))
        };
        Ladybird instance = (Ladybird) animals[0];
        Position expResult = new Position(0, 2);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
        assertFalse(animals[0].isOnStar());
        assertFalse(board.getSquareType(new Position(0, 1)) == GRASS);
    }

    /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMove_nextOnStar() {
        System.out.println("move, next on star and win");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(STAR), 
                new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS), null},
            {null, null, new Square(STAR), null}
        });
        animals = new Animal[]{
            new Ladybird(new Position(0, 0)),
            new Snail(new Position(0, 3))
        };
        Ladybird instance = (Ladybird) animals[0];
        Position expResult = new Position(0, 2);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
        assertTrue(animals[0].isOnStar());
        assertEquals(GRASS, board.getSquareType(result));
    }

    /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMoveNorthHasWall() {
        System.out.println("move next case but has a wall");
        Ladybird instance = (Ladybird) animals[0];
        Position actualPosition = new Position(0, 0);
        board.getSquare(actualPosition).setNorthWall(true);
        Position expResult = new Position(0, 0); // Don't move
        Position result = instance.move(board, Direction.NORTH, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMoveSouthHasWall() {
        System.out.println("move next case but has a wall");
        Ladybird instance = (Ladybird) animals[0];
        Position actualPosition = new Position(0, 0);
        board.getSquare(actualPosition).setSouthWall(true);
        Position expResult = new Position(0, 0); // Don't move
        Position result = instance.move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMoveEastHasWall() {
        System.out.println("move next case but has a wall");
        Ladybird instance = (Ladybird) animals[0];
        Position actualPosition = new Position(0, 0);
        board.getSquare(actualPosition).setEastWall(true);
        Position expResult = new Position(0, 0); // Don't move
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMoveWestHasWall() {
        System.out.println("move next case but has a wall");
        Ladybird instance = (Ladybird) animals[0];
        Position actualPosition = new Position(0, 0);
        board.getSquare(actualPosition).setWestWall(true);
        Position expResult = new Position(0, 0); // Don't move
        Position result = instance.move(board, Direction.WEST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMove_passOnStarWithNextWall() {
        System.out.println("move, next on star and win");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(STAR), new Square(GRASS), 
                new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS), null},
            {null, null, new Square(STAR), null}
        });
        animals = new Animal[]{
            new Ladybird(new Position(0, 0))};

        Ladybird instance = (Ladybird) animals[0];
        Position expResult = new Position(0, 1);
        Position posHasWall = new Position(0, 2);
        board.getSquare(posHasWall).setWestWall(true);
        
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
        assertTrue(animals[0].isOnStar());
        assertEquals(GRASS, board.getSquareType(result));
    }

    /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMove_passOnStarWithWall() {
        System.out.println("move, next on star and win");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(STAR), new Square(GRASS), 
                new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS), null},
            {null, null, new Square(STAR), null}
        });
        animals = new Animal[]{
            new Ladybird(new Position(0, 0))};
        Ladybird instance = (Ladybird) animals[0];
        Position expResult = new Position(0, 1);
        board.getSquare(expResult).setEastWall(true);
        
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
        assertTrue(animals[0].isOnStar());
        assertEquals(GRASS, board.getSquareType(result));
    }

}
