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
public final class Berserker extends Troll implements Warrior {
    
    private static int nbBerserkersInGame;
    
    public Berserker()
    {
        super(300,300,350,350,60,50);
    }

    @Override
    public void removeOneCharacter() {
        nbBerserkersInGame--;
        nbTrollsInGame--;
        nbEnemiesInGame--;
        nbCharactersInGame--;
    }
    
    /*
    Getters ;
    */
    public static int getNbBerserkerInGame() {
        return nbBerserkersInGame;
    }

    
    /*
    Setters ;
    */
    public static void setNbBerserkerInGame(int nbBerserkerInGame) {
        Berserker.nbBerserkersInGame = nbBerserkerInGame;
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
