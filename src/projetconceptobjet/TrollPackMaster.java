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
    
    private static int nbTrollPackMasterInGame;
    
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
    
    
    /**
     * Function to update the number of each type of charaters when one is killed ;
     */
    /*@Override
    public void updateStats()
    {
       if(this.isDead()==true)
       {
           //Decrease the number of characters alive ;
           int nbCharacterInGame=Character.getNbCharactersInGame();
           nbCharacterInGame--;
           Character.setNbCharactersInGame(nbCharacterInGame);
           //Decrease the number of Enemies alive ;
           int nbEnemies=Enemy.getNbEnemiesInGame();
           nbEnemies--;
           Enemy.setNbEnemiesInGame(nbEnemies);
           //Decrease the number of Trolls alive ;
           int nbTrolls=Troll.getNbTrollsInGame();
           nbTrolls--;
           Troll.setNbTrollsInGame(nbTrolls);
           //Decrease the number of TPM alive ;
           int nbTPM=TrollPackMaster.getNbTrollPackMasterInGame();
           nbTPM--;
           TrollPackMaster.setNbTrollPackMasterInGame(nbTPM);
       }
    }*/
}
