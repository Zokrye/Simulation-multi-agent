package projetconceptobjet;


import java.util.ArrayList;
import java.util.List;
import projetconceptobjet.Direction;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alexandre
 */
public class Cell {
    protected Map map;
    protected Zone zone;
    protected int x;
    protected int y;
    protected boolean hasObstacle;
    protected Character character;

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public void setHasObstacle(boolean hasObstacle) {
        this.hasObstacle = hasObstacle;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public boolean getHasObstacle() {
        return hasObstacle;
    }

    public Character getCharacter() {
        return character;
    }

    public Zone getZone() {
        return zone;
    }
    public boolean hasCharacter() {
        return this.character!=null;
    }
    
    public Cell(Map map, int x, int y) {
        this.map=map;
        this.x=x;
        this.y=y;
        
    }
    
    public List<Direction> getAvailableDirections() {
        List<Direction> availableDirections =new ArrayList<>();
        for(int i=-1;i<=1;i++) {
            for(int j=-1;j<=1;j++) {
                if(i!=0 && j!=0) { // Direction 0,0 is not allowed
                    Direction direction=new Direction(i,j);
                    if(checkDirection(direction)) {
                        availableDirections.add(new Direction(i,j));
                    }
                }
            }
        }
        return availableDirections;
    }
    
    public boolean checkDirection(Direction direction) {
        Cell targetedCell=map.getCell(x+direction.getX(), y+direction.getY());
        if(targetedCell!=null) {
            return targetedCell.isAvailable();
        }
        return false;
    }

    public boolean isAvailable() {
        return !hasObstacle && !hasCharacter();
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    
}
