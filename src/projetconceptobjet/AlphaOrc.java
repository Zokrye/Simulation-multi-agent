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
public final class AlphaOrc extends Orc implements Mediator{
    
    private static int nbAlphaOrcsInGame;
    
    public AlphaOrc()
    {
        super(350,350,500,500,65,25);
    }

    @Override
    public void removeOneCharacter() {
        nbAlphaOrcsInGame--;
        nbOrcsInGame--;
        nbEnemiesInGame--;
        nbCharactersInGame--;
    }
    
    /*
    Getters ;
    */
    public static int getNbOrcAlphaInGame() {
        return nbAlphaOrcsInGame;
    }

    /*
    Setters ;
    */
    public static void setNbOrcAlphaInGame(int nbOrcAlphaInGame) {
        AlphaOrc.nbAlphaOrcsInGame = nbOrcAlphaInGame;
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
