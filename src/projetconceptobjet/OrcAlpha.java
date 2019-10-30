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
public final class OrcAlpha extends Orc implements Mediator{
    
    private static int nbOrcAlphaInGame;
    
    public OrcAlpha()
    {
        super(350,350,500,500,65,25);
    }

    
    /*
    Getters ;
    */
    public static int getNbOrcAlphaInGame() {
        return nbOrcAlphaInGame;
    }

    /*
    Setters ;
    */
    public static void setNbOrcAlphaInGame(int nbOrcAlphaInGame) {
        OrcAlpha.nbOrcAlphaInGame = nbOrcAlphaInGame;
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
