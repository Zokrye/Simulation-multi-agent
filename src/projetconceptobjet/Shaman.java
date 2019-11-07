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
public final class Shaman extends Troll implements Wizzard {
    
    private int mana;
    private int manaMax;
    private static int nbShamansInGame;
    
    public Shaman()
    {
        super(300,300,300,300,40,50);
        this.mana=200;
        this.manaMax=200;
    }
    
    @Override
    public void removeOneCharacter() {
        nbShamansInGame--;
        nbTrollsInGame--;
        nbEnemiesInGame--;
        nbCharactersInGame--;
    }

    
    /*
    Getters ;
    */
    public int getMana() {
        return mana;
    }
    
    public int getManaMax() {
        return manaMax;
    }
    
    public static int getNbShamanInGame() {
        return nbShamansInGame;
    }

    
    /*
    Setters ;
    */
    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setManaMax(int manaMax) {
        this.manaMax = manaMax;
    }

    public static void setNbShamanInGame(int nbShamanInGame) {
        Shaman.nbShamansInGame = nbShamanInGame;
    }
    
    //Bonus ou malus sur la cible ;
    @Override
    public void sortStats()
    {
        System.out.println("SORTS STATS");
    }
    
    //Permet d'élargir le champs de perception du personnage lors des déplacements pour éviter ou suprimmer les ennemis
    //ou soutenir les alliés ;
    @Override
    public void detection()
    {
        System.out.println("DETECTION");
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
           int nbShamans=Shaman.getNbShamanInGame();
           nbShamans--;
           TrollPackMaster.setNbTrollPackMasterInGame(nbShamans);
       }
    }*/
}
