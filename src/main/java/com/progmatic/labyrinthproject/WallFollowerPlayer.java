/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.progmatic.labyrinthproject;

import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;
import com.progmatic.labyrinthproject.interfaces.Player;
import java.util.*;

/**
 *
 * @author imaginifer
 */
public class WallFollowerPlayer implements Player{  //balra tart
    
    Direction former;
    boolean first;

    public WallFollowerPlayer() {
        first=true;
    }
    
    
    
    @Override
    public Direction nextMove(Labyrinth l) {
        List<Direction> d = l.possibleMoves();
        if(first){
            first = false;
            former = d.get(0);
            return d.get(0);
        }
        switch(former){
            case EAST:
                if(d.contains(Direction.NORTH)){
                    former = Direction.NORTH;
                    return Direction.NORTH;
                }
                break;
            case NORTH:
                if(d.contains(Direction.WEST)){
                    former = Direction.WEST;
                    return Direction.WEST;
                }
                break;
            case WEST:
                if(d.contains(Direction.SOUTH)){
                    former = Direction.SOUTH;
                    return Direction.SOUTH;
                }
                break;
            case SOUTH:
                if(d.contains(Direction.EAST)){
                    former = Direction.EAST;
                    return Direction.EAST;
                }
                break;
                
        }
        if(d.contains(former)){
            return former;
        }
        switch(former){
            case EAST:
                if(d.contains(Direction.SOUTH)){
                    former = Direction.SOUTH;
                    return Direction.SOUTH;
                }
                break;
            case NORTH:
                if(d.contains(Direction.EAST)){
                    former = Direction.EAST;
                    return Direction.EAST;
                }
                break;
            case WEST:
                if(d.contains(Direction.NORTH)){
                    former = Direction.NORTH;
                    return Direction.NORTH;
                }
                break;
            case SOUTH:
                if(d.contains(Direction.WEST)){
                    former = Direction.WEST;
                    return Direction.WEST;
                }
                break;
                
        }
        former = d.get(0);
        return d.get(0);
    }
    
    
    
}
