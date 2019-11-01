/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetconceptobjet;

import java.util.ArrayList;
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
    private ArrayList<Cell> SafeZoneElfes = new ArrayList<>();
    private ArrayList<Cell> SafeZoneHumans = new ArrayList<>();
    private ArrayList<Cell> SafeZoneOrcs = new ArrayList<>();
    private ArrayList<Cell> SafeZoneTrolls = new ArrayList<>();
    
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
    
    /**
     * 
     * @param x horizontal position of the cell on the map
     * @param y vertical position of the cell on the map
     * @return Cell with these coordinates
     */
    public Cell getCell(int x, int y) {
        try {
            return cells[x][y];
        }
        catch(ArrayIndexOutOfBoundsException e){
            return null;
        }
        //return null;
    }
    
    /**
     * Return the unique nstance of the class Map and create it if doesn't exists
     * @return Map
     */
    public static Map getinstance() {
        if(map==null) {
            map=new Map(50,50,125);
        }
        return map;
    }
    
    /**
     * Spread the obstacles on the map based on NB_OBSTACLES
     */
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
    
    /**
     * Set the different safezones on the map based on SIZE_SAFE_ZONE
     */
    private void setSafeZones() {
        //Top left - Men
        for(int i=0; i<SIZE_SAFE_ZONE;i++) {
            for(int j=0;j<SIZE_SAFE_ZONE;j++) {
                this.getCell(i, j).zone=Zone.SafeZoneMan;
                SafeZoneHumans.add(this.getCell(i, j));
            }
        }
        //Bottom right - Elfs
        for(int i=LENGTH-SIZE_SAFE_ZONE; i<LENGTH;i++) {
            for(int j=HEIGHT-SIZE_SAFE_ZONE;j<HEIGHT;j++) {
                this.getCell(i, j).zone=Zone.SafeZoneElf ;
                SafeZoneElfes.add(this.getCell(i, j));
            }
        }
        //Bottom left - Trolls
        for(int i=0; i<SIZE_SAFE_ZONE;i++) {
            for(int j=HEIGHT-SIZE_SAFE_ZONE;j<HEIGHT;j++) {
                this.getCell(i, j).zone=Zone.SafeZoneTroll ;
                SafeZoneTrolls.add(this.getCell(i, j));
            }
        }
        //Bottom top - Orcs
        for(int i=LENGTH-SIZE_SAFE_ZONE; i<LENGTH;i++) {
            for(int j=0;j<SIZE_SAFE_ZONE;j++) {
                this.getCell(i, j).zone=Zone.SafeZoneOrc ;
                SafeZoneOrcs.add(this.getCell(i, j));
            }
        }
    }
    
    /**
     * Place all the characters at hte beginning of the game
     */
    public void placeAllCharacters(boolean onlyInSafeZones) {
        ArrayList<Team> allTeams=Team.getAllTeams();
        
        for(Team team : allTeams) {
            ArrayList<Cell> availableCells;
            if(onlyInSafeZones) {
                if(team.getType()==Elfe.class) {
                    availableCells=this.SafeZoneElfes;
                }
                else if(team.getType()==Human.class) {
                    availableCells=this.SafeZoneHumans;
                }
                else if(team.getType()==Orc.class) {
                    availableCells=this.SafeZoneOrcs;
                }
                else if(team.getType()==Troll.class) {
                    availableCells=this.SafeZoneTrolls;
                }
                else {
                    availableCells=new ArrayList<>();
                }
                if(availableCells.size()>0) {
                
            }
            }
            else {
                availableCells=new ArrayList<>();
                for(int x=0;x<LENGTH;x++) {
                    for(int y=0;y<HEIGHT;y++) {
                        Cell cell=getCell(x,y);
                        if(!cell.hasObstacle) {
                            availableCells.add(cell);
                        }
                    }
                }
            }
            for(Character character : team.getListCharacters()) {
                    int randomIndex = RandomElement.randomThrow(availableCells.size()-1,0);
                    Cell randomCell = availableCells.get(randomIndex);
                    character.setCurrentCell(randomCell);
                    randomCell.setCharacter(character);
                    availableCells.remove(randomIndex);         
                }
            
        }
    }
    
    /**
     * Display the map into the console using ASCII characters
     */
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
