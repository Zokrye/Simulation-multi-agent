/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_concept_objet_simu_multi_agents;

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
    
    /*Methods*/
    public abstract void seDeplacer();
    public abstract void attaquer();
    public abstract void fuir();
    
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
