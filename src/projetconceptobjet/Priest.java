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
public final class Priest extends Human implements Wizzard {
    
    private int mana;
    private int manaMax;
    private static int nbPriestsInGame;
    
    public Priest()
    {
        super(150,150,300,300,30,40);
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
    
    public static int getNbPriestInGame() {
        return nbPriestsInGame;
    }
    
    @Override
    public void removeOneCharacter() {
        nbPriestsInGame--;
        nbHumansInGame--;
        nbHeroesInGame--;
        nbCharactersInGame--;
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

    public static void setNbPriestInGame(int nbPriestInGame) {
        Priest.nbPriestsInGame = nbPriestInGame;
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
