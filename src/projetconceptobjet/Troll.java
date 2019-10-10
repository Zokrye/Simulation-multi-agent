/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_concept_objet_simu_multi_agents;

/**
 *
 * @author ISEN
 */
public abstract class Troll extends Enemy {
    
    private static int nbTrollsInGame;
    private static Species weakness;
    
    //Augmente des caractéristiques défensive du Troll ;
    public abstract void blindage();
    //Permet de déplacer les obstacles du terrain;
    public abstract void modificationTerrain();
    //Diminu les stats des adversaire lors des combats ;
    public abstract void impressionnant();
    
}
