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
public final class Assassin extends Orc implements Warrior {
    
    //Attributes
    private static int nbAssassinsInGame;
    
    
    //Constructor
    public Assassin()
    {
        super(300,300,300,300,45,30);
    }

    
    /*
    Getters ;
    */
    public static int getNbAssassinInGame() {
        return nbAssassinsInGame;
    }

    
    /*
    Setters    
    */
    public static void setNbAssassinInGame(int nbAssassinInGame) {
        Assassin.nbAssassinsInGame = nbAssassinInGame;
    }
    
    
    /*
    Methods
    */
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
    
    @Override
    public void removeOneCharacter() {
        nbAssassinsInGame--;
        nbOrcsInGame--;
        nbEnemiesInGame--;
        nbCharactersInGame--;
    }
}
