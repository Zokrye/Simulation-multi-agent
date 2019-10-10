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
public abstract class Enemy extends Character {
    
    private static int nbEnemiesInGame;
    
    //Destruction d'un allié pour récupérer son xp si celui-ci est fatigué ;
    public abstract void sacrifice();
    //Application de malus sur les statistiques adverses ;
    public abstract void fleau();
    //Application d'un bonus d'attaque et perte de points d'Energie/Mana accrue pour les 2 ou 3 tours suivants ;
    public abstract void surchargeDePuissance();
    
}