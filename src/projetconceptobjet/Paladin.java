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
public final class Paladin extends Human implements Warrior {
    
    //Attribute
    private static int nbPaladinsInGame;
    
    
    //Constructor
    public Paladin()
    {
        super(200,200,300,300,40,40);
    }

    
    /*
    Getters ;
    */
    public static int getNbPaladinInGame() {
        return nbPaladinsInGame;
    }

    
    /*
    Setters
    */
    public static void setNbPaladinInGame(int nbPaladinInGame) {
        Paladin.nbPaladinsInGame = nbPaladinInGame;
    }
    
    
    /*
    Methods
    */
    @Override
    public void removeOneCharacter() {
        nbPaladinsInGame--;
        nbHumansInGame--;
        nbHeroesInGame--;
        nbCharactersInGame--;
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
