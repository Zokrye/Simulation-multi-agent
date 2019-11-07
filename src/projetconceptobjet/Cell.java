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
    
    /*
    Attributes
    */
    protected Map map;
    protected Zone zone;
    protected int x;
    protected int y;
    protected boolean hasObstacle;
    protected Character character;

    //Constructor
    public Cell(Map map, int x, int y) {
        this.map=map;
        this.x=x;
        this.y=y;
        
    }
    
    /*
    Getters and Setters
    */
    
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
    
     public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    
    /*
    Methods
    */
    
    /**
     * Get all direction availaible (with no obstacle) from the current cell
     * @return List of the available directions
     */
    public List<Direction> getAvailableDirections() {
        List<Direction> availableDirections =new ArrayList<>();
        for(int i=-1;i<=1;i++) {
            for(int j=-1;j<=1;j++) {
                if(i!=0 || j!=0) { // Direction 0,0 is not allowed
                    Direction direction=new Direction(i,j);
                    if(checkDirection(direction)) {
                        availableDirections.add(new Direction(i,j));
                    }
                }
            }
        }
        return availableDirections;
    }
    
    /**
     * Checks if the direction specified is available (with no obstacle)
     * @param direction chosen
     * @return Whether it is available or not
     */
    public boolean checkDirection(Direction direction) {
        Cell targetedCell=map.getCell(x+direction.getX(), y+direction.getY());
        if(targetedCell!=null) {
            return !targetedCell.hasObstacle;
        }
        return false;
    }

    /**
     * 
     * @return Whether the cell is available or not
     */
    public boolean isAvailable() {
        return !hasObstacle && !hasCharacter();
    }    
}
