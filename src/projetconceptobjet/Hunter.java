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
public final class Hunter extends Elfe implements Warrior {
    
    private static int nbHuntersInGame;
    
    public Hunter()
    {
        super(200,200,300,300,40,30);
    }
    
    @Override
    public void removeOneCharacter() {
        nbHuntersInGame--;
        nbElfesInGame--;
        nbHeroesInGame--;
        nbCharactersInGame--;
    }

    
    /*
    Getter ;
    */
    public static int getNbHunterInGame() {
        return nbHuntersInGame;
    }

    
    /*
    Setters ;
    */
    public static void setNbHunterInGame(int nbHunterInGame) {
        Hunter.nbHuntersInGame = nbHunterInGame;
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
