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
public class Sorcerer extends Orc implements Wizzard{
    
    private int mana;
    private int manaMax;
    private static int nbSorcererInGame;
    
    public Sorcerer()
    {
        super(200,200,300,300);
        this.mana=200;
        this.manaMax=200;
    }

    
    /*
    Getters
    */
    public int getMana() {
        return mana;
    }

    public int getManaMax() {
        return manaMax;
    }

    public static int getNbSorcererInGame() {
        return nbSorcererInGame;
    }

    
    /*
    Setters ;
    */
    public static void setNbSorcererInGame(int nbSorcererInGame) {
        Sorcerer.nbSorcererInGame = nbSorcererInGame;
    }
    
    public void setMana(int mana) {
        this.mana = mana;
    }
    
     public void setManaMax(int manaMax) {
        this.manaMax = manaMax;
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
