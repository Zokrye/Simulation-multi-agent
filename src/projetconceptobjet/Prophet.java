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
    private static int nbPriestInGame;
    
    public Prophet()
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
    
        //Permet de prendre la main sur les attaques lors des combats après le premier tour de jeux (attaque en premier);
    @Override
    public void celerite()
    {
        System.out.println("CELERITE!");
    }
    //Permet d'éviter certains des coups infligés par l'ennemi et facilite la fuite ;
    @Override
    public void esquive()
    {
        System.out.println("ESQUIVE!");
    }
    //Permet de dépenser moins de points d'énergie lors des déplacements ;
    @Override
    public void endurance()
    {
        System.out.println("ENDURANCE!");
    }
}
