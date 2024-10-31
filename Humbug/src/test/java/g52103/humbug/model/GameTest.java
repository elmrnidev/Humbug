/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g52103.humbug.model;

import static g52103.humbug.model.Action.MOVE;
import static g52103.humbug.model.LevelStatus.IN_PROGRESS;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test of Game
 *
 * @author El Amrani Mounir (52103) 52103@etu.he2b.be
 */
public class GameTest {

    private final Game game = new Game();

    @Test
    public void GameTest() {
        game.startLevel(1);

        Board board = game.getBoard();
        Animal animals[] = game.getAnimals();
        System.out.println("Move to north and fall");
        Snail instance = (Snail) animals[0];
        Position expResult = null;
        Position result = instance.move(board, Direction.NORTH, animals);
        assertEquals(expResult, result);
    }

    @Test
    public void testGame_Restart_CurrentLevelStaus_OnStar() {
        System.out.println("Restart the curren level but is on star");
        game.startLevel(1);
        Board board = game.getBoard();
        Animal animals[] = game.getAnimals();
        animals[0].setPositionOnBoard(new Position(1,2));
        Snail instance = (Snail) animals[0];
        instance.move(board, Direction.SOUTH, animals);

        int result = game.getCurrentLevel();
        LevelStatus levelResult = game.getLevelStatus();
        game.restart();
        int expResult = 1;
        LevelStatus levelExpResult = IN_PROGRESS;
        assertEquals(result, expResult);
        assertEquals(levelResult, levelExpResult);
    }
    
    @Test
    public void testGame_Restart_CurrentLevelStaus_FALL() {
        System.out.println("Move but fall in the void");
        game.startLevel(1);
        Board board = game.getBoard();
        Animal animals[] = game.getAnimals();
        Snail instance = (Snail) animals[0];
        instance.move(board, Direction.NORTH, animals);

        int result = game.getCurrentLevel();
        LevelStatus levelResult = game.getLevelStatus();
        game.restart();
        int expResult = 1;
        LevelStatus levelExpResult = IN_PROGRESS;
        assertEquals(result, expResult);
        assertEquals(levelResult, levelExpResult);
    }
    
    @Test
    public void testGame_Restart_CurrentLevel_Move() {
        System.out.println("move in the nextPosition but the level restart");
        game.startLevel(1);
        Board board = game.getBoard();
        Animal animals[] = game.getAnimals();
        Snail instance = (Snail) animals[0];
        instance.move(board, Direction.EAST, animals);

        int result = game.getCurrentLevel();
        LevelStatus levelResult = game.getLevelStatus();
        game.restart();
        int expResult = 1;
        LevelStatus levelExpResult = IN_PROGRESS;
        assertEquals(result, expResult);
        assertEquals(levelResult, levelExpResult);
    }

    
}
