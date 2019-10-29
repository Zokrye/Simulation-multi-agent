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
public class Paladin extends Human implements Warrior {
    
    private static int nbPaladinInGame;
    
    public Paladin()
    {
        super(200,200,300,300,40,40);
    }

    
    /*
    Getters ;
    */
    public static int getNbPaladinInGame() {
        return nbPaladinInGame;
    }

    
    /*
    Setters
    */
    public static void setNbPaladinInGame(int nbPaladinInGame) {
        Paladin.nbPaladinInGame = nbPaladinInGame;
    }
    

    //Attaque accrue;
    @Override
    public void manimentArmes()
    {
        System.out.println("MANIMENT ARMES");
    }
    //Attaque imparable (rarement);
    @Override
    public void perforationDefense()
    {
        System.out.println("PERFORATION DEFENSE");
    }
    
}
