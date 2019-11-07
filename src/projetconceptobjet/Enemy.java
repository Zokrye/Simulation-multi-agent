/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetconceptobjet;

/**
 *
 * @author ISEN
 */
public abstract class Enemy extends Character {
    
    //Attributes
    protected static int nbEnemiesInGame;
    
    
    //Constructor
    public Enemy(int pEnergie,int pEnergieMax,int pVie,int pVieMax, int strenght, int defense)
    {
        super(pEnergie,pEnergieMax,pVie,pVieMax,strenght,defense);
    }

    
    /*
    Getters ;
    */
    public static int getNbEnemiesInGame() {
        return nbEnemiesInGame;
    }

    
    /*
    Setters ;
    */
    public static void setNbEnemiesInGame(int nbEnemiesInGame) {
        Enemy.nbEnemiesInGame = nbEnemiesInGame;
    }
    
    /*
    Methods
    */
    
    @Override
    public abstract void removeOneCharacter();
    
    @Override
    public boolean isSameSide(Character character) {
        return character instanceof Enemy;
    }

    @Override
    public void meet(Character otherCharacter, int remainingCells) {
        if(this.isSameSide(otherCharacter)) {
            sacrifice(otherCharacter);
        }
        else {
            if(!otherCharacter.isInSafeZone()) {
            fight(otherCharacter);
            }                          
        } 
    }
    

    @Override
    public abstract boolean isSameRace(Character character);
    
    //Destruction d'un allié pour récupérer son xp si celui-ci est fatigué ;
    public void sacrifice(Character character) {     
        if(this.isSameRace(character)) {
            //Steal the half of his life
            character.doCalculationPV(-character.getpVie()/2);
            this.doCalculationPV(character.getpVie()/2);
            
        }
        else {
            //Kills the character, steal the half of his life and his strenght
            this.doCalculationPV(character.getpVie()/2);
            this.strenghtPoints+=character.getStrenghtPoints()/2;
            character.kill();
        }
    }
    
    //Application de malus sur les statistiques adverses ;
    public abstract void fleau();
    //Application d'un bonus d'attaque et perte de points d'Energie/Mana accrue pour les 2 ou 3 tours suivants ;
    public abstract void surchargeDePuissance();
    
}
