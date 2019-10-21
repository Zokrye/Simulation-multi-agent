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
public class Berserker extends Troll implements Warrior {
    
    private static int nbBerserkerInGame;
    
    public Berserker()
    {
        super(350,350,300,300);
    }

    
    /*
    Getters ;
    */
    public static int getNbBerserkerInGame() {
        return nbBerserkerInGame;
    }

    
    /*
    Setters ;
    */
    public static void setNbBerserkerInGame(int nbBerserkerInGame) {
        Berserker.nbBerserkerInGame = nbBerserkerInGame;
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
