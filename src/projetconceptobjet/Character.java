/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetconceptobjet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author ISEN
 */
public abstract class Character {
    
    /*Attributs*/
    protected String nom;
    protected int pEnergie ;
    protected int pEnergieMax ;
    protected int pVie ;
    protected int pVieMax ;
    protected int xp ;
    protected boolean etatFatigue;
    protected int turnRanking;
    protected boolean dead;
    protected Species type;
    protected int[] lastDirection;
    protected int strenghtPoints;
    protected int defensivePoints;
    //protected int niveau;
    //protected int nbPasMaxTour;
    //protected int nbPasTour;
    private static int nbCharactersInGame;
    
    protected Cell currentCell;
    protected Direction safeZoneDirection;
    protected int maxMovement;
    
    public Character(int pEnergie,int pEnergieMax,int pVie,int pVieMax,int strenght, int defense)
    {
        this.pEnergie=pEnergie;
        this.pEnergieMax=pEnergieMax;
        this.pVie=pVie;
        this.pVieMax=pVieMax;
        this.strenghtPoints=strenght;
        this.defensivePoints=defense;
        this.xp=0;
        this.etatFatigue=false;
        this.dead=false;
    }
    
    /*Methods*/
    
    public void seDeplacer() {
        int remainingCells = RandomElement.randomThrow(maxMovement, 1);
        
        Cell nextCell=null;
        Direction chosenDirection=null;
        
        if(this.pEnergie/this.pEnergieMax<=0.2) {
            //Use the default direction to join Safezone
            Direction d1=safeZoneDirection;
            //The 2nd best options for joining the safeZone
            Direction d2=new Direction(safeZoneDirection.getX(),0);
            Direction d3=new Direction(0,safeZoneDirection.getY());
            //The 3rd best options for joining the safezone
            Direction d4=new Direction(safeZoneDirection.getX(),-safeZoneDirection.getY());
            Direction d5=new Direction(-safeZoneDirection.getX(),safeZoneDirection.getY());
            //Last options and less interesting directions to join SafeZone
            Direction d6=new Direction(-safeZoneDirection.getX(),0);
            Direction d7=new Direction(0,-safeZoneDirection.getY());
            
            Direction d8=new Direction(-safeZoneDirection.getX(),-safeZoneDirection.getY());

            List<Direction> directions = new ArrayList<> (Arrays.asList(d1,d2,d3,d4,d5,d6,d7,d8));
            //Get the first available cell in the list
            for(Direction d : directions) {
                //If direction leads to an existing cell
                if(d.applyFrom(currentCell)!=null) {
                    //If the cell has no obstacles
                    if(!d.applyFrom(currentCell).hasObstacle) {
                        //Save the available direction                   
                        chosenDirection=d;
                        break;
                    }
                }

            }
        }
        //Déplacment random quand les PE sont suffisants
        else {
            List<Direction> directions=currentCell.getAvailableDirections();
            if(directions.size()>0) {
                int randomIndex=RandomElement.randomThrow(directions.size()-1, 0);
                chosenDirection=directions.get(randomIndex);
            }
        }
        if(chosenDirection!=null) {
            while(remainingCells>0 && pEnergie>0 && chosenDirection.applyFrom(currentCell)!=null) {
                    nextCell=chosenDirection.applyFrom(currentCell);
                    if(!nextCell.hasObstacle ) {
                        if(nextCell.character==null) {

                            currentCell.setCharacter(null);
                            currentCell=nextCell;
                            currentCell.setCharacter(this);
                            if(pVie<pVieMax) {
                                pVie++;
                            }
                            if(isInSafeZone()){
                                if(this.pEnergie<=pEnergieMax-3) {
                                    pEnergie+=3;
                                }
                                else {
                                    pEnergie=pEnergieMax;
                                }
                            }
                            else {
                                pEnergie--;
                            }
                            remainingCells--;
                        }

                        //Meet another character
                        else {
                            Character otherCharacter = nextCell.getCharacter();
                            if(this.isSameRace(otherCharacter)) {
                                otherCharacter.addPV(remainingCells);
                                this.addPV(remainingCells);
                            }
                            else if(this.isSameSide(otherCharacter)) {
                                   //TODO: Ajout de points d'XP aux personnages
                            }
                                
                            else {
                                if(!isInSafeZone() && !otherCharacter.isInSafeZone()) {
                                //TODO: FIGHT
                                }
                            }                   
                        }
                        remainingCells=0;
                    }
                    //Reset remaining cells to 0 in case the character hits an obstacle
                    else {
                        remainingCells=0;
                    }
            }     

        }
        
        
    }
    
    
    public abstract void attack(Character target);
    public abstract void escape();
    public abstract boolean isInSafeZone();
    
    /**
     * 
     * @param character
     * @return Whether the current and specefied characters are in the same team or not
     */
    public abstract boolean isSameSide(Character character);
    
    /**
     * 
     * @param character
     * @return Whether the current and specefied characters belong to the same race or not
     */
    public abstract boolean isSameRace(Character character);
    
    
    /**
     * Actions of the character during its gaming period ;
     */
    public void characterTurn()
    {
        /*
        Test of the free cells around him ;
        Avoid obstacles but search an enemy ;
        */
        /*
        If there is no enemy and its PE are ok, go forward to search enemies ;
        If there is an enemy and its PE and PV are ok, go to attack him ;
        If there is an enemy and its PE or PV are low, return to the safe zone and try to get them again ;
        If there is no enemy and its PE are low, return to the safe zone to get them again ;
        */
        /*
        End of the turn ;
        */
    }
    
    /**
     * Add Life points to the character
     * @param pvAdded 
     */
    public void addPV(int pvAdded) {
        if(pVie+pvAdded<pVieMax) {
            pVie+=pvAdded;
        }
        else {
            pVie=pVieMax;
        }
    }
    
    /**
     * Remove Life points from the character
     * @param pvRemoved 
     */
    public void removePV(int pvRemoved) {
        if(pVie-pvRemoved>0) {
            pVie-=pvRemoved;
        }
        else {
            pVie=0;
        }
    }
    
    /**
     * Add stamina to the character
     * @param peAdded 
     */
    public void addPE(int peAdded) {
        if(pEnergie+peAdded<pEnergieMax) {
            this.pEnergie+=peAdded;
        }
        else {
            pEnergie=pEnergieMax;
        }
    }
    
    /**
     * Remove stamina from the character
     * @param peRemoved 
     */
    public void removePE(int peRemoved) {
        if(pEnergie-peRemoved>0) {
            this.pEnergie-=peRemoved;
        }
        else {
            pEnergie=0;
        }
    }
    
    /*
    Getters
    */
    public String getNom() {
        return nom;
    }
    
    public int getpEnergie() {
        return pEnergie;
    }

    public int getpEnergieMax() {
        return pEnergieMax;
    }
    
    public int getpVie() {
        return pVie;
    }
    
    public int getpVieMax() {
        return pVieMax;
    }
    
    public int getXp() {
        return xp;
    }
    
    public boolean isEtatFatigue() {
        return etatFatigue;
    }
    
    public int[] getLastDirection() {
        return lastDirection;
    }
    
    public static int getNbCharactersInGame() {
        return nbCharactersInGame;
    }

    public int getTurnRanking() {
        return turnRanking;
    }

    public boolean isDead() {
        return dead;
    }

    public Species getType() {
        return type;
    }

    public int getStrenghtPoints() {
        return strenghtPoints;
    }

    public int getDefensivePoints() {
        return defensivePoints;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public Direction getSafeZoneDirection() {
        return safeZoneDirection;
    }

    public int getMaxMovement() {
        return maxMovement;
    }

    
     
    
    
    /*
    Setters
    */
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setpEnergie(int pEnergie) {
        this.pEnergie = pEnergie;
    }

    public void setpEnergieMax(int pEnergieMax) {
        this.pEnergieMax = pEnergieMax;
    }

    public void setpVie(int pVie) {
        this.pVie = pVie;
    }

    public void setpVieMax(int pVieMax) {
        this.pVieMax = pVieMax;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void setEtatFatigue(boolean etatFatigue) {
        this.etatFatigue = etatFatigue;
    }
    
    public void setLastDirection(int[] lastDirection) {
        this.lastDirection = lastDirection;
    }

    public static void setNbCharactersInGame(int nbCharactersInGame) {
        Character.nbCharactersInGame = nbCharactersInGame;
    }
    
    public void setTurnRanking(int turnRanking) {
        this.turnRanking = turnRanking;
    }
    
    public void setDead(boolean dead) {
        this.dead = dead;
    }
    
    public void setType(Species type) {
        this.type = type;
    }
    
    public void setStrenghtPoints(int strenghtPoints) {
        this.strenghtPoints = strenghtPoints;
    }
    
    public void setMaxMovement(int maxMovement) {
        this.maxMovement = maxMovement;
    }
    
    public void setSafeZoneDirection(Direction safeZoneDirection) {
        this.safeZoneDirection = safeZoneDirection;
    }
    
    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }
    
    public void setDefensivePoints(int defensivePoints) {
        this.defensivePoints = defensivePoints;
    }
    
    /*
    Methods
    */
    
    /**
     * Function that allows to calculate and set the final number of PEs of a character ;
     * @param signe : Allows to indicate if the value must be added or substracted to the PEs ;
     * @param value : Value of the PE to add or put away ;
     */
    public void doCalculationPE(String signe, int value)
    {
        //Getting the current PE value of the character ;
        int valuePE=this.getpEnergie();
        System.out.println("PEs of "+this.getNom()+" were of "+this.getpEnergie()+"/"+this.getpEnergieMax()+" PE.");
        /*
        Test the signe of the value and do the calculation ;
        */
        if(signe.equals("+"))
        {
            //Adds PE if the signe is "+" ;
            valuePE+=value;
        }
        else if (signe.equals("-"))
        {
            //Substracts PE if the signe is "-" ;
            valuePE-=value;
        }
        //Sets character energy points ;
        this.setpEnergie(valuePE);
        System.out.println("They are now of "+this.getpEnergie()+"/"+this.getpEnergieMax()+" PE.");
    }
    
    /**
     * Function that allows to calculate and set the final number of PVs of a character ;
     * @param signe : Allows to indicate if the value must be added or substracted to the PVs ;
     * @param value : Value of the PE to add or put away ;
     */
    public void doCalculationPV(String signe, int value)
    {
        //Getting the current PE value of the character ;
        int valuePV=this.getpVie();
        System.out.println("PVs of "+this.getNom()+" were of "+this.getpVie()+"/"+this.getpVieMax()+" PV.");
        /*
        Test the signe of the value and do the calculation ;
        */
        if(signe.equals("+"))
        {
            //Adds PE if the signe is "+" ;
            valuePV+=value;
        }
        else if (signe.equals("-"))
        {
            //Substracts PE if the signe is "-" ;
            valuePV-=value;
        }
        //Sets character energy points ;
        this.setpVie(valuePV);
        System.out.println("They are now of "+this.getpVie()+"/"+this.getpVieMax()+" PV.");
    }
}
