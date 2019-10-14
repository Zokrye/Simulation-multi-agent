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
    protected int[] lastDirection;
    //protected int niveau;
    //protected int nbPasMaxTour;
    //protected int nbPasTour;
    private static int nbCharactersInGame;
    
    protected Cell currentCell;
    protected Direction safeZoneDirection;
    protected int maxMovement;
    
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
                            //TODO Vérification alliés ou ennemis/ chefs
                            if(!isInSafeZone() && !nextCell.character.isInSafeZone()) {
                                //FIGHT
                            }
                        } 
                    }
                    //Reset remaining cells to 0 in case the character hits an obstacle
                    else {
                        remainingCells=0;
                    }
            }     

        }
        
        
    }
    public abstract void attaquer();
    public abstract void fuir();
    public abstract boolean isInSafeZone();
    
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
    
    
    
}
