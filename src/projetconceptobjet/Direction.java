/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetconceptobjet;

/**
 *
 * @author Alexandre
 */
public class Direction {
    /*
    Attributes
    */
    private int x;
    private int y;
    
    
    //Constructor
    public Direction(int x, int y) {
        this.x=x;
        this.y=y;
    }

    
    /*
    Getters
    */
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
     * Apply a direction of move from a cell and return the next cell in this direction
     * @param departureCell
     * @return next cell
     */
    public Cell applyFrom(Cell departureCell) {
        int xArrivalCell=departureCell.getX()+x;
        int yArrivalCell=departureCell.getY()+y;
        return Map.getinstance().getCell(xArrivalCell,yArrivalCell);
    }
    
    
}
