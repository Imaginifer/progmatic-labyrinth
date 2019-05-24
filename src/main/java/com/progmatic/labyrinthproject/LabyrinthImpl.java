package com.progmatic.labyrinthproject;

import com.progmatic.labyrinthproject.enums.CellType;
import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.exceptions.CellException;
import com.progmatic.labyrinthproject.exceptions.InvalidMoveException;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 *
 * @author pappgergely
 */
public class LabyrinthImpl implements Labyrinth {
    
    private ArrayList<ArrayList<CellType>> maze;
    private Coordinate current;

    public LabyrinthImpl() {
    }

    @Override
    public void loadLabyrinthFile(String fileName) {
        maze = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(fileName));
            int width = Integer.parseInt(sc.nextLine());
            int height = Integer.parseInt(sc.nextLine());
            for (int hh = 0; hh < height; hh++) {
                String line = sc.nextLine();
                maze.add(new ArrayList<>());
                for (int ww = 0; ww < width; ww++) {
                    switch (line.charAt(ww)) {
                        case 'W':
                            maze.get(hh).add(CellType.WALL);
                            break;
                        case 'E':
                            maze.get(hh).add(CellType.END);
                            break;
                        case 'S':
                            maze.get(hh).add(CellType.START);
                            current = new Coordinate(ww,hh);
                            break;
                        default:
                            maze.get(hh).add(CellType.EMPTY);
                            break;
                    }
                }
            }
        } catch (FileNotFoundException | NumberFormatException ex) {
            System.out.println(ex.toString());
        }
    }

    @Override
    public int getWidth() {
        try{
            return maze.get(0).size();
        } catch (NullPointerException e) {
            System.out.println("There is no labyrinth.");
            return -1;
        }
    }

    @Override
    public int getHeight() {
        try{
            return maze.size();
        } catch (NullPointerException e) {
            System.out.println("There is no labyrinth.");
            return -1;
        }
    }

    @Override
    public CellType getCellType(Coordinate c) throws CellException {
        try {
            int k = maze.size();
        } catch (NullPointerException e) {
            throw new CellException(c, "There is no labyrinth.");
        }
        if(maze.isEmpty()){
            throw new CellException(c, "There is no labyrinth.");
        } else if (c.getRow()<0 || c.getCol()<0 || c.getRow()>=maze.size() 
                || c.getCol()>=maze.get(0).size()){
            throw new CellException(c, "Given coordinates are outside of the labyrinth.");
        }
        return maze.get(c.getRow()).get(c.getCol());
    }

    @Override
    public void setSize(int width, int height) {
        maze = new ArrayList<>();
        for (int i = 0; i < height; i++) {
                maze.add(new ArrayList<>());
                for (int j = 0; j < width; j++) {
                    maze.get(i).add(CellType.EMPTY);
                }
            }
    }

    @Override
    public void setCellType(Coordinate c, CellType type) throws CellException {
        try {
            int k = maze.size();
        } catch (NullPointerException e) {
            throw new CellException(c, "There is no labyrinth.");
        }
         if(maze.isEmpty()){
            throw new CellException(c, "There is no labyrinth.");
        } else if (c.getRow()<0 || c.getCol()<0 || c.getRow()>maze.size() 
                || c.getCol()>maze.get(0).size()){
            throw new CellException(c, "Given coordinates are outside of the labyrinth.");
        }
        if(type==CellType.START){
            current = new Coordinate(c.getCol(), c.getRow());
        }
        maze.get(c.getRow()).set(c.getCol(), type);
    }

    @Override
    public Coordinate getPlayerPosition() {
        try {
            return current;
        } catch (NullPointerException e) {
            System.out.println("There is no labyrinth.");
            return null;
        }
    }

    @Override
    public boolean hasPlayerFinished() {
        try {
            return maze.get(current.getRow()).get(current.getCol())==CellType.END;
        } catch (NullPointerException e) {
            System.out.println("There is no labyrinth.");
            return false;
        }
    }

    @Override
    public List<Direction> possibleMoves() {
        List<Direction> d = new ArrayList<>();
        CellType[] h = {CellType.EMPTY, CellType.END};
        HashSet<CellType> valid = new HashSet<>(Arrays.asList(h));
        try{
            if(current.getRow()>0 && valid.contains(maze.get(current.getRow()-1)
                    .get(current.getCol()))){
                d.add(Direction.NORTH);
            }
            if(current.getRow()<(maze.size()-1) && valid.contains(maze.get(current.getRow()+1)
                    .get(current.getCol()))){
                d.add(Direction.SOUTH);
            }
            if(current.getCol()>0 && valid.contains(maze.get(current.getRow())
                    .get(current.getCol()-1))){
                d.add(Direction.WEST);
            }
            if(current.getCol()<(maze.get(0).size()-1) && valid.contains(maze.get(current.getRow())
                    .get(current.getCol()+1))){
                d.add(Direction.EAST);
            }
        }catch(NullPointerException e){
            System.out.println("There is no labyrinth.");
        }
        return d;
    }

    @Override
    public void movePlayer(Direction direction) throws InvalidMoveException {
        List<Direction> poss = possibleMoves();
        if(!poss.contains(direction)){
            throw new InvalidMoveException();
        }
        int x = current.getCol();
        int y = current.getRow();
        switch(direction){
            case NORTH:
                current = new Coordinate(x,y-1);
                break;
            case EAST:
                current = new Coordinate(x+1,y);
                break;
            case SOUTH:
                current = new Coordinate(x,y+1);
                break;
            case WEST:
                current = new Coordinate(x-1,y);
                break;
        }
    }

}
