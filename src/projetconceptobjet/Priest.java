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
public class Priest extends Human implements Wizzard {
    
    private int mana;
    private int manaMax;
    private static int nbPriestInGame;
    
    public Priest()
    {
        super(150,150,300,300);
        this.mana=200;
        this.manaMax=200;
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
