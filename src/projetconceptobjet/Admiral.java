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
public class Admiral extends Human implements Mediator {    
    
    private static int nbAdmiralInGame;
    
    public Admiral()
    {
        super(300,300,500,500,60,40);
    }

    
    /*
    Getters ;
    */
    public static int getNbAdmiralInGame() {
        return nbAdmiralInGame;
    }
    
    
    /*
    Setters ;
    */
    public static void setNbAdmiralInGame(int nbAdmiralInGame) {
        Admiral.nbAdmiralInGame = nbAdmiralInGame;
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
