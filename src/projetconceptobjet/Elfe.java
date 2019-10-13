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
public abstract class Elfe extends Hero {
    
    private static int nbElfesInGame;
    private static Species weakness;
    
    public Elfe(int pEnergie,int pEnergieMax,int pVie,int pVieMax)
    {
        super(pEnergie,pEnergieMax,pVie,pVieMax);
        Elfe.weakness=Species.Troll;
    }
    
    //Augmente les stats d'un personnage en fonction d'une zone autour de lui et du nombre de ses alliés ;
    @Override
    public void soutenir()
    {
        System.out.println("SOUTENIR!");
    }
    
    //Distribue des points de vie aux alliés rencontrés ;
    @Override
    public void soin()
    {
        System.out.println("SOIN!");
    }
    //Réanime les personnages fatigués avec des PEs ;
    @Override
    public void reanimation()
    {
        System.out.println("REANIMATION!");
    }
    
    @Override
    public void seDeplacer()
    {
        System.out.println("DEPLACEMENT!");
    }
    
    @Override
    public void attaquer()
    {
        System.out.println("ATTAQUE!");
    }
    
    @Override
    public void fuir()
    {
        System.out.println("FUITE!");
    }
    
    //Permet de prendre la main sur les attaques lors des combats après le premier tour de jeux (attaque en premier);
    public abstract void celerite();
    //Permet d'éviter certains des coups infligés par l'ennemi et facilite la fuite ;
    public abstract void esquive();
    //Permet de dépenser moins de points d'énergie lors des déplacements ;
    public abstract void endurance();
    
}
