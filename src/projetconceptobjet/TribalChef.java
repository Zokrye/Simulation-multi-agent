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
public final class TribalChef extends Elfe implements Mediator {
    
    private static int nbTribalChefInGame;
    
    public TribalChef()
    {
        super(300,300,500,500,60,40);
    }

    /*
    Getters ;
    */
    public static int getNbTribalChefInGame() {
        return nbTribalChefInGame;
    }

    
    /*
    Setters ;
    */
    public static void setNbTribalChefInGame(int nbTribalChefInGame) {
        TribalChef.nbTribalChefInGame = nbTribalChefInGame;
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
