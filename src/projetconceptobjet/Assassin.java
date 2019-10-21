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
public class Assassin extends Orc implements Warrior {
    
    private static int nbAssassinInGame;
    
    public Assassin()
    {
        super(300,300,300,300);
    }

    
    /*
    Getters ;
    */
    public static int getNbAssassinInGame() {
        return nbAssassinInGame;
    }

    
    /*
    Setters    
    */
    public static void setNbAssassinInGame(int nbAssassinInGame) {
        Assassin.nbAssassinInGame = nbAssassinInGame;
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
