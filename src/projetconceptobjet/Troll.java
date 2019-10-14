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
public abstract class Troll extends Enemy {
    
    private static int nbTrollsInGame;
    private static Species weakness;
    
    public Troll() {
        this.safeZoneDirection=new Direction(-1,1);
        this.maxMovement=3;
    }
    
    @Override
    public boolean isInSafeZone() {
        return this.currentCell.getZone()==Zone.SafeZoneTroll;
    }
    
    //Augmente des caractéristiques défensive du Troll ;
    public abstract void blindage();
    //Permet de déplacer les obstacles du terrain;
    public abstract void modificationTerrain();
    //Diminu les stats des adversaire lors des combats ;
    public abstract void impressionnant();
    
    
}
