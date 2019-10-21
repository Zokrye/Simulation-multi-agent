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
    protected Integer HEIGHT;
    protected Integer LENGTH;
    protected Integer NB_OBSTACLES;
    //protected List<Cell> cells;
    protected Cell[][] cells;
    private static Map map;
    
    public Map(int height, int length, int nbObstacles) {
        this.HEIGHT=height;
        this.LENGTH=length;
        cells=new Cell[LENGTH][HEIGHT];
        this.NB_OBSTACLES=nbObstacles;  
        for(int i=0; i<LENGTH;i++) {
            for(int j=0;j<HEIGHT;j++) {
                cells[i][j]=(new Cell(this,i,j));
            }
        }
        setSafeZones();
        spreadObstacles();

    }
    
    public Cell getCell(int x, int y) {
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
            map=new Map(50,50,125);
        }
        return map;
    }
    
    public final void spreadObstacles() {
        int x;
        int y;
        int i=0;
        while(i<NB_OBSTACLES) {
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
        //Top left
        for(int i=0; i<SIZE_SAFE_ZONE;i++) {
            for(int j=0;j<SIZE_SAFE_ZONE;j++) {
                this.getCell(i, j).zone=Zone.SafeZoneMan;
            }
        }
        //Bottom right
        for(int i=LENGTH-SIZE_SAFE_ZONE; i<LENGTH;i++) {
            for(int j=HEIGHT-SIZE_SAFE_ZONE;j<HEIGHT;j++) {
                this.getCell(i, j).zone=Zone.SafeZoneElf ;
            }
        }
        //Bottom left
        for(int i=0; i<SIZE_SAFE_ZONE;i++) {
            for(int j=HEIGHT-SIZE_SAFE_ZONE;j<HEIGHT;j++) {
                this.getCell(i, j).zone=Zone.SafeZoneTroll ;
            }
        }
        //Bottom top
        for(int i=LENGTH-SIZE_SAFE_ZONE; i<LENGTH;i++) {
            for(int j=0;j<SIZE_SAFE_ZONE;j++) {
                this.getCell(i, j).zone=Zone.SafeZoneOrc ;
            }
        }
    }
    
    public void displayMap() {
        for(int y=0;y<HEIGHT;y++) {
            for(int x=0;x<LENGTH;x++) {
                Cell cell= getCell(x,y);
                if(!cell.isAvailable()) {
                    if(cell.getCharacter()!=null) {
                        if(cell.getCharacter() instanceof Orc) {
                            System.out.print("O");
                        }
                        if(cell.getCharacter() instanceof Elfe) {
                            System.out.print("E");
                        }
                        if(cell.getCharacter() instanceof Human) {
                            System.out.print("H");
                        }
                        if(cell.getCharacter() instanceof Troll) {
                            System.out.print("T");
                        }
                    }
                    else if(cell.getHasObstacle()==true) {
                        System.out.print("X");
                    }
                }
                //Display the SafeZones
                else {
                    if((cell.getX()==SIZE_SAFE_ZONE-1 && cell.getY()<SIZE_SAFE_ZONE-1) ||
                            (cell.getX()==LENGTH-SIZE_SAFE_ZONE && cell.getY()<SIZE_SAFE_ZONE-1) ||
                            (cell.getX()==SIZE_SAFE_ZONE-1 && cell.getY()>HEIGHT-SIZE_SAFE_ZONE) ||
                            (cell.getX()==LENGTH-SIZE_SAFE_ZONE && cell.getY()>HEIGHT-SIZE_SAFE_ZONE)) {
                        System.out.print("|");
                    }
                    else if((cell.getY()==SIZE_SAFE_ZONE-1 && cell.getX()<SIZE_SAFE_ZONE-1) ||
                            (cell.getY()==LENGTH-SIZE_SAFE_ZONE && cell.getX()<SIZE_SAFE_ZONE-1) ||
                            (cell.getY()==SIZE_SAFE_ZONE-1 && cell.getX()>HEIGHT-SIZE_SAFE_ZONE) ||
                            (cell.getY()==LENGTH-SIZE_SAFE_ZONE && cell.getX()>HEIGHT-SIZE_SAFE_ZONE)) {
                        System.out.print("-");
                    }
                    else if((cell.getX()==SIZE_SAFE_ZONE-1 && cell.getY()==SIZE_SAFE_ZONE-1) ||
                            (cell.getX()==SIZE_SAFE_ZONE-1 && cell.getY()==HEIGHT-SIZE_SAFE_ZONE) ||
                            (cell.getX()==LENGTH-SIZE_SAFE_ZONE && cell.getY()==SIZE_SAFE_ZONE-1) ||
                            (cell.getX()==LENGTH-SIZE_SAFE_ZONE && cell.getY()==HEIGHT-SIZE_SAFE_ZONE)) {
                        System.out.print("+");
                    }
                    else {
                    System.out.print(".");
                    }
                    
                }
            }
            System.out.println("");
        }
    }
    
}
