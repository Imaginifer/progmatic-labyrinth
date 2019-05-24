/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.progmatic.labyrinthproject;

import com.progmatic.labyrinthproject.enums.CellType;
import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.exceptions.CellException;
import com.progmatic.labyrinthproject.exceptions.InvalidMoveException;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;
import com.progmatic.labyrinthproject.interfaces.Player;
import java.util.*;

/**
 *
 * @author imaginifer
 */
public class ConsciousPlayer implements Player{
    private List<Path> models;
    private int steps;
    private Path convenient;

    public ConsciousPlayer() {
        models = new ArrayList<>();
        steps = 0;
        convenient = new Path();
    }

    @Override
    public Direction nextMove(Labyrinth l) {
        if(models.isEmpty()){
            contemplatePossibilities(l);
            //convenient = findShortest();
        }
        if(convenient.getSteps().isEmpty()){
            return wander(l);
        }
        return convenient.getSteps().get(steps++);
    }
    
    private void contemplatePossibilities(Labyrinth l){
        /*ArrayList<ArrayList<CellType>> map = copyMaze(l);
        int kvant = l.getHeight()*l.getWidth();
        for (int i = 0; i < 30; i++) {
            Path p = new Path();
            Coordinate current = l.getPlayerPosition();
            for (int j = 0; j < kvant; j++) {
                List<Direction> d = l.possibleMoves();
                Random r = new Random();
                int q = r.nextInt(d.size());
                p.move(d.get(q));
                try{
                    l.movePlayer(d.get(q));
                }catch(InvalidMoveException e){
                }
                if(l.hasPlayerFinished()){
                    p.finish();
                    break;
                }
            }
            models.add(p);
        }*/
    }
    
    private ArrayList<ArrayList<CellType>> copyMaze(Labyrinth l){
        ArrayList<ArrayList<CellType>> m = new ArrayList<>();
        int h = l.getHeight();
        int w = l.getWidth();
        try{
            for (int i = 0; i < h; i++) {
                m.add(new ArrayList<>());
                for (int j = 0; j < w; j++) {
                    m.get(i).add(l.getCellType(new Coordinate(j,i)));
                }
            }
        } catch (CellException e){
        }
        return m;
    }
    
    private Path findShortest(){
        Path x = new Path();
        int min = models.get(0).getSteps().size();
        for (Path model : models) {
            if(model.isSuccessful() && model.getSteps().size()<min){
                convenient = model;
            }
        }
        return x;
    }
    
    private Direction wander(Labyrinth l) {
        List<Direction> d = l.possibleMoves();
        Random r = new Random();
        int q = r.nextInt(d.size());
        return d.get(q);
    }
    

    
    
}
