/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elm.humbug.model;

/**
 * The class move.
 * 
 * @author elm (52103) 52103@etu.he2b.be
 */
public class Move {
    private final Position position;
    private final Direction direction;

    public Move(Position position, Direction direction) {
        this.position = position;
        this.direction = direction;
    }

    public Position getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }
    
    
}
