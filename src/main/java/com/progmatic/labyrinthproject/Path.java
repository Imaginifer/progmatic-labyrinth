/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.progmatic.labyrinthproject;

import com.progmatic.labyrinthproject.enums.Direction;
import java.util.*;

/**
 *
 * @author imaginifer
 */
public class Path {
    private List<Direction> steps;
    private boolean successful;

    public Path() {
        steps=new ArrayList<>();
        successful=false;
    }

    public List<Direction> getSteps() {
        return steps;
    }

    public boolean isSuccessful() {
        return successful;
    }
    
    public void finish(){
        successful=true;
    }
    
    public void move(Direction x){
        steps.add(x);
    }
    
    
}
