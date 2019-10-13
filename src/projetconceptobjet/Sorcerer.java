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
public abstract class Sorcerer extends Orc implements Wizzard{
    
    private int mana;
    private int manaMax;
    private static int nbSorcererInGame;
    
    public Sorcerer()
    {
        super(200,200,300,300);
        this.mana=200;
        this.manaMax=200;
    }
    
}
