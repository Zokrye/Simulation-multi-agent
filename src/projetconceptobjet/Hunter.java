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
public class Hunter extends Elfe implements Warrior {
    
    private static int nbHunterInGame;
    
    public Hunter()
    {
        super(200,200,300,300,40,30);
    }

    
    /*
    Getter ;
    */
    public static int getNbHunterInGame() {
        return nbHunterInGame;
    }

    
    /*
    Setters ;
    */
    public static void setNbHunterInGame(int nbHunterInGame) {
        Hunter.nbHunterInGame = nbHunterInGame;
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
