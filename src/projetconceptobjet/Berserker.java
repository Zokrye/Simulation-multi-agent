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
public abstract class Berserker extends Troll implements Warrior {
    
    private static int nbBerserkerInGame;
    
    public Berserker()
    {
        super(350,350,300,300);
    }
    
}
