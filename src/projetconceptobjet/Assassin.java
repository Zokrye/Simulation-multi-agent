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
public abstract class Assassin extends Orc implements Warrior {
    
    private static int nbAssassinInGame;
    
    public Assassin()
    {
        super(300,300,300,300);
    }
    
}
