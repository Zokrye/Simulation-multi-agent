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
public final class Shaman extends Troll implements Wizzard {
    
    /*
    Attributes
    */
    private int mana;
    private int manaMax;
    private static int nbShamansInGame;
    
    
    //Constructor
    public Shaman()
    {
        super(300,300,300,300,40,50);
        this.mana=200;
        this.manaMax=200;
    }

    
    /*
    Getters ;
    */
    public int getMana() {
        return mana;
    }
    
    public int getManaMax() {
        return manaMax;
    }
    
    public static int getNbShamanInGame() {
        return nbShamansInGame;
    }

    
    /*
    Setters ;
    */
    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setManaMax(int manaMax) {
        this.manaMax = manaMax;
    }

    public static void setNbShamanInGame(int nbShamanInGame) {
        Shaman.nbShamansInGame = nbShamanInGame;
    }
    
    
    /*
    Methods
    */
    @Override
    public void removeOneCharacter() {
        nbShamansInGame--;
        nbTrollsInGame--;
        nbEnemiesInGame--;
        nbCharactersInGame--;
    }
    
    //Bonus ou malus sur la cible ;
    @Override
    public void sortStats()
    {
        System.out.println("SORTS STATS");
    }
    
    //Permet d'élargir le champs de perception du personnage lors des déplacements pour éviter ou suprimmer les ennemis
    //ou soutenir les alliés ;
    @Override
    public void detection()
    {
        System.out.println("DETECTION");
    }
    
}
