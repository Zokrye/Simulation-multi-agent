/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetconceptobjet;

import java.util.List;

/**
 *
 * @author Alexandre
 */
public class Map {
    protected final int SIZE_SAFE_ZONE=5;
    protected final int HEIGHT;
    protected final int LENGTH;
    protected final int NB_OBSTACLES;
    //protected List<Cell> cells;
    protected Cell[][] cells;
    private static Map map;
    
    public Map(int height, int length, int nbObstacles) {
        this.HEIGHT=height;
        this.LENGTH=length;
        cells=new Cell[LENGTH][HEIGHT];
        this.NB_OBSTACLES=nbObstacles;  
        /*for(int i=0; i<HEIGHT;i++) {
            for(int j=0;j<LENGTH;j++) {
                cells.add(new Cell(this,i,j));
            }
        }*/
        for(int i=0; i<HEIGHT;i++) {
            for(int j=0;j<LENGTH;j++) {
                cells[i][j]=(new Cell(this,i,j));
            }
        }

    }
    
    public Cell getCell(int x, int y) {
        /*for(Cell cell : cells) {
            if(cell.getX()==x && cell.getY()==y) {
                return cell;
            }
        }*/
        try {
            return cells[x][y];
        }
        catch(ArrayIndexOutOfBoundsException e){
            return null;
        }
        //return null;
    }
    public static Map getinstance() {
        if(map==null) {
            map= new Map(50,50,250);
        }
        return map;
    }
    
    private void spreadObstacles(int nbObstacles) {
        int x;
        int y;
        int i=0;
        while(i<nbObstacles) {
            x=(int) (Math.random()*LENGTH);
            y=(int) (Math.random()*HEIGHT);
            Cell cell=getCell(x,y);
            if(cell!=null) {
                if(!cell.hasCharacter() && !cell.hasObstacle && cell.getZone()!=Zone.SafeZoneElf 
                        && cell.getZone()!=Zone.SafeZoneMan && cell.getZone()!=Zone.SafeZoneOrc 
                        && cell.getZone()!=Zone.SafeZoneTroll) {
                    cell.setHasObstacle(true);
                    i++;
                }  
            }
        }
        
    }
    
    private void setSafeZones() {
        /*for(Cell cell : cells) {
            //Bottom left
            if(cell.getX()<=5 && cell.getY()<=5) {
                cell.zone=Zone.SafeZoneMan;
            }
            //Top right
            if(cell.getX()>=this.LENGTH-5 && cell.getY()>=this.HEIGHT-5) {
                cell.zone=Zone.SafeZoneElf;
            }
            //Top left
            if(cell.getX()<=5 && cell.getY()>=this.HEIGHT-5) {
                cell.zone=Zone.SafeZoneTroll;
            }
            //Bottom right
            if(cell.getX()>=this.LENGTH-5 && cell.getY()<=5) {
                cell.zone=Zone.SafeZoneOrc;
            }
            else { 
                cell.zone=Zone.Other; 
            }
        }*/
        //Bottom left
        for(int i=0; i<SIZE_SAFE_ZONE;i++) {
            for(int j=0;j<SIZE_SAFE_ZONE;j++) {
                this.getCell(i, j).zone=Zone.SafeZoneMan;
            }
        }
        //Top right
        for(int i=LENGTH-SIZE_SAFE_ZONE; i<LENGTH;i++) {
            for(int j=HEIGHT-SIZE_SAFE_ZONE;j<SIZE_SAFE_ZONE;j++) {
                this.getCell(i, j).zone=Zone.SafeZoneElf ;
            }
        }
        //Top left
        for(int i=0; i<SIZE_SAFE_ZONE;i++) {
            for(int j=HEIGHT-SIZE_SAFE_ZONE;j<HEIGHT;j++) {
                this.getCell(i, j).zone=Zone.SafeZoneTroll ;
            }
        }
        //Bottom right
        for(int i=LENGTH-SIZE_SAFE_ZONE; i<LENGTH;i++) {
            for(int j=0;j<SIZE_SAFE_ZONE;j++) {
                this.getCell(i, j).zone=Zone.SafeZoneOrc ;
            }
        }
    }
    
}
