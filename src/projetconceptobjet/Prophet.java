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
public class Prophet extends Elfe implements Wizzard  {
    
    private int mana;
    private int manaMax;
    private static int nbProphetsInGame;
    
    public Prophet()
    {
        super(300,300,150,150,35,35);
        this.mana=200;
        this.manaMax=200;
    }
    
    @Override
    public void removeOneCharacter() {
        nbProphetsInGame--;
        nbElfesInGame--;
        nbHeroesInGame--;
        nbCharactersInGame--;
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
    
    public static int getNbProphetInGame() {
        return nbProphetsInGame;
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

    public static void setNbProphettInGame(int nbProphetInGame) {
        Prophet.nbProphetsInGame = nbProphetInGame;
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
