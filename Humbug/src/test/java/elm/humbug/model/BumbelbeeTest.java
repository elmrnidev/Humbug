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
import elm.humbug.model.Bumbelbee;
import elm.humbug.model.Direction;
import elm.humbug.model.Ladybird;
import elm.humbug.model.Position;
import elm.humbug.model.Square;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 * Test of BumbelbeeTest.
 *
 * @author elm (52103) <52103@etu.he2b.be>
 */
public class BumbelbeeTest {

    private Board board;
    private Animal[] animals;

    @BeforeEach
    public void setUp() {
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS), 
                new Square(GRASS)},
            {new Square(GRASS), new Square(GRASS), null, new Square(GRASS)},
            {null, null, new Square(GRASS), new Square(GRASS)},
            {null, null, null, new Square(STAR)}
        });
        animals = new Animal[]{
            new Bumbelbee(new Position(0, 0)),
            new Bumbelbee(new Position(2, 3))
        };
    }

    /**
     * Test of move method, of class Bumbelbee.
     */
    @Test
    public void testMove() {
        System.out.println("jump_general");
        Bumbelbee instance = (Bumbelbee) animals[0];
        Position expResult = new Position(0, 2); //.next(Direction.EAST);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Bumbelbee.
     */
    @Test
    public void testMove_next_AndFall() {
        System.out.println("flies and fall");
        Bumbelbee instance = (Bumbelbee) animals[1];
        Position expResult = null; //.next(Direction.EAST);
        Position result = instance.move(board, Direction.WEST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Bumbelbee.
     */
    @Test
    public void testMove_FlyOverNull() {
        System.out.println("flies over null square in board");
         animals = new Animal[]{
            new Bumbelbee(new Position(1, 1))
        };
        Bumbelbee instance = (Bumbelbee) animals[0];
        Position expResult = new Position(1,3); //.next(Direction.EAST);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Bumbelbee.
     */
    @Test
    public void testMove_next_onstar() {
        System.out.println("Flies next on star");
        animals = new Animal[]{
            new Bumbelbee(new Position(0, 3)),
            new Bumbelbee(new Position(2, 3))

        };
        Bumbelbee instance = (Bumbelbee) animals[0];
        Position expResult = new Position(3, 3);
        Position result = instance.move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
        assertTrue(instance.isOnStar());
        assertEquals(GRASS, board.getSquareType(result));
    }

    /**
     * Test of move method, of class Bumbelbee.
     */
    @Test
    public void testMove_next_notinside_2() {
        System.out.println("Flies next case not inside and jump over ");
        animals = new Animal[]{
            new Bumbelbee(new Position(1, 0))
        };
        Bumbelbee instance = (Bumbelbee) animals[0];
        Position expResult = null; //fall in the void
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Bumbelbee.
     */
    @Test
    public void testMove_actualHasNorthWall() {
        System.out.println("jump to next case but has a north wall");
        Bumbelbee instance = (Bumbelbee) animals[0];
        Position expResult = null; // jump over wall and fall
        board.getSquare(instance.getPositionOnBoard()).setNorthWall(true);
        Position result = instance.move(board, Direction.NORTH, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Bumbelbee.
     */
    @Test
    public void testMove_actualHasSouthWall() {
        System.out.println("jump next case has wall");
        Bumbelbee instance = (Bumbelbee) animals[0];
        Position expResult = null; // jump over wall
        board.getSquare(instance.getPositionOnBoard()).setSouthWall(true);
        Position result = instance.move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Bumbelbee.
     */
    @Test
    public void testMove_actualHasWestWall() {
        System.out.println("jump to next case but has a West wall");
        Bumbelbee instance = (Bumbelbee) animals[0];
        Position expResult = null; // jump and fall
        board.getSquare(instance.getPositionOnBoard()).setWestWall(true);
        Position result = instance.move(board, Direction.WEST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Bumbelbee.
     */
    @Test
    public void testMove_actualHasEastWall() {
        System.out.println("jump to next case but has a East wall");
        Bumbelbee instance = (Bumbelbee) animals[0];
        Position expResult = new Position(0, 2); // jump on next case
        board.getSquare(instance.getPositionOnBoard()).setEastWall(true);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Bumbelbee.
     */
    @Test
    public void testMove_JumpOverAnimal() {
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS), 
                new Square(GRASS)}
        });
        animals = new Animal[]{
            new Bumbelbee(new Position(0, 0)),
            new Ladybird(new Position(0, 2))
        };
        System.out.println("fly next case not free and jump over ");
        Bumbelbee instance = (Bumbelbee) animals[0];
        Position expResult = new Position(0, 3); //jump over animal
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Bumbelbee.
     */
    @Test
    public void testMove_JumpOverAnimals() {
        System.out.println("jump next case has animals");

        animals = new Animal[]{
            new Bumbelbee(new Position(0, 0)),
            new Ladybird(new Position(0, 1)),
            new Ladybird(new Position(0, 2))
        };
        Bumbelbee instance = (Bumbelbee) animals[0];
        Position expResult = new Position(0, 3); // jump over animals        
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }
}
