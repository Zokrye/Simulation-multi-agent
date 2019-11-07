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
public final class TrollPackMaster extends Troll implements Mediator {
    
    //Attribute
    private static int nbTrollPackMasterInGame;
    
    //Constructor
    public TrollPackMaster()
    {
        super(300,300,500,500,70,50);
    }

    
    /*
    Getters ;
    */
    public static int getNbTrollPackMasterInGame() {
        return nbTrollPackMasterInGame;
    }

    /*
    Setters
    */
    public static void setNbTrollPackMasterInGame(int nbTrollPackMasterInGame) {
        TrollPackMaster.nbTrollPackMasterInGame = nbTrollPackMasterInGame;
    }
    
    
    /*
    Methods
    */
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
    
    @Override
    public void removeOneCharacter() {
        nbTrollPackMasterInGame--;
        nbTrollsInGame--;
        nbEnemiesInGame--;
        nbCharactersInGame--;
    }
    
}
