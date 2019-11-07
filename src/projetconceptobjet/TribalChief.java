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
public final class TribalChief extends Elfe implements Mediator {
    
    //Attribute
    private static int nbTribalChiefsInGame;
    
    
    //Constructor
    public TribalChief()
    {
        super(300,300,500,500,60,40);
    }

    /*
    Getters ;
    */
    public static int getNbTribalChefInGame() {
        return nbTribalChiefsInGame;
    }

    
    /*
    Setters ;
    */
    public static void setNbTribalChefInGame(int nbTribalChefInGame) {
        TribalChief.nbTribalChiefsInGame = nbTribalChefInGame;
    }
    
    /*
    Methods
    */
    @Override
    public void removeOneCharacter() {
        nbTribalChiefsInGame--;
        nbElfesInGame--;
        nbHeroesInGame--;
        nbCharactersInGame--;
    }
    
    
    @Override
    public void negociation()
    {
        System.out.println("NEGOCIATION!");
    }
    
    
    @Override
    public void ralliement()
    {
        System.out.println("RALLIEMENT!");
    }
}
