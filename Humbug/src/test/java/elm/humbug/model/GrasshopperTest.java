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
import elm.humbug.model.Grasshopper;
import elm.humbug.model.Ladybird;
import elm.humbug.model.Position;
import elm.humbug.model.Square;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 * Test of grasshopper.
 * 
 * @author elm (52103) <52103@etu.he2b.be>
 */
public class GrasshopperTest {

    private Board board;
    private Animal[] animals;

    @BeforeEach
    public void setUp() {
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), null},
            {new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS)},
            {null, null, new Square(STAR)}
        });
        animals = new Animal[]{
            new Grasshopper(new Position(0, 0)),
            new Grasshopper(new Position(2, 2))
        };
    }

    /**
     * Test of move method, of class Snail.
     */
    @Test
    public void testMove() {
        System.out.println("jump_general");
        Grasshopper instance = (Grasshopper) animals[0];
        Position expResult = new Position(0, 1); //.next(Direction.EAST);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Grasshopper.
     */
    @Test
    public void testMove_next_JumpOverAnimal() {
        System.out.println("jump next case not free and jump over ");
        Grasshopper instance = (Grasshopper) animals[0];
        animals[0].setPositionOnBoard(new Position(1, 0));
        animals[1].setPositionOnBoard(new Position(1, 1));
        Position expResult = new Position(1, 2); //jump over animal
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Grasshopper.
     */
    @Test
    public void testMove_next_onstar() {
        System.out.println("jump next on star");
        Grasshopper instance = (Grasshopper) animals[1];
        Position expResult = new Position(3, 2);
        Position result = instance.move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
        assertTrue(instance.isOnStar());
        assertEquals(GRASS, board.getSquareType(result));
    }

    /**
     * Test of move method, of class Grasshopper.
     */
    @Test
    public void testMove_next_notinside_2() {
        System.out.println("jump next case not free and jump over ");
        Grasshopper instance = (Grasshopper) animals[0];
        animals[0].setPositionOnBoard(new Position(0, 0));
        animals[1].setPositionOnBoard(new Position(0, 1));
        Position expResult = null; //jump over animal anf fall
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Grasshopper.
     */
    @Test
    public void testMove_actual_hasNorthWall() {
        System.out.println("jump to next case but has a north wall");
        Grasshopper instance = (Grasshopper) animals[0];
        Position expResult = null; // jump over wall and fall
        board.getSquare(instance.getPositionOnBoard()).setNorthWall(true);
        Position result = instance.move(board, Direction.NORTH, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Grasshopper.
     */
    @Test
    public void testMove_actual_hasSouthWall() {
        System.out.println("jump next case has wall");
        Grasshopper instance = (Grasshopper) animals[0];
        Position expResult = new Position(1, 0); // jump over wall
        board.getSquare(instance.getPositionOnBoard()).setSouthWall(true);
        Position result = instance.move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Grasshopper.
     */
    @Test
    public void testMove_actual_hasWestWall() {
        System.out.println("jump to next case but has a West wall");
        Grasshopper instance = (Grasshopper) animals[0];
        Position expResult = null; // jump and fall
        board.getSquare(instance.getPositionOnBoard()).setWestWall(true);
        Position result = instance.move(board, Direction.WEST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Grasshopper.
     */
    @Test
    public void testMove_actual_hasEastWall() {
        System.out.println("jump to next case but has a East wall");
        Grasshopper instance = (Grasshopper) animals[0];
        Position expResult = new Position(0, 1); // jump on next case
        board.getSquare(instance.getPositionOnBoard()).setEastWall(true);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Grasshopper.
     */
    @Test
    public void testMove_next_JumpOverAnimals() {
        System.out.println("jump next case has animals");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS),
                new Square(GRASS), new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS)},
            {null, null, new Square(STAR)}
        });
        animals = new Animal[]{
            new Grasshopper(new Position(0, 0)), 
            new Ladybird(new Position(0, 1)), 
            new Grasshopper(new Position(0, 2))
        };
        Grasshopper instance = (Grasshopper) animals[0];
        Position expResult = new Position(0, 3); // jump over animals        
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }
}
