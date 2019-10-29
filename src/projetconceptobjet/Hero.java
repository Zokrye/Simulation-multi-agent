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
public abstract class Hero extends Character {
    
    private static int nbHeroesInGame;
    
    public Hero(int pEnergie,int pEnergieMax,int pVie,int pVieMax, int strenght, int defense)
    {
        super(pEnergie,pEnergieMax,pVie,pVieMax,strenght,defense);
    }

    
    /*
    Getters ;
    */
    public static int getNbHeroesInGame() {
        return nbHeroesInGame;
    }

    
    /*
    Setters ;
    */
    public static void setNbHeroesInGame(int nbHeroesInGame) {
        Hero.nbHeroesInGame = nbHeroesInGame;
    }
    
    @Override
    public boolean isSameSide(Character character) {
        return character instanceof Hero;
    }
    
    //Augmente les stats d'un personnage en fonction d'une zone autour de lui et du nombre de ses alliés ;
    public abstract void soutenir();
    //Distribue des points de vie aux alliés rencontrés ;
    public abstract void soin();
    //Réanime les personnages fatigués avec des PEs ;
    public abstract void reanimation();
    
    
}
