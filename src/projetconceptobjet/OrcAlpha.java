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
public abstract class OrcAlpha extends Orc implements Mediator{
    
    private static int nbOrcAlphaInGame;
    
    public OrcAlpha()
    {
        super(350,350,500,500);
    }
    
}
