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
public abstract class Elfe extends Hero {
    
    private static int nbElfesInGame;
    private static Species weakness;
    
    //Permet de prendre la main sur les attaques lors des combats après le premier tour de jeux (attaque en premier);
    public abstract void celerite();
    //Permet d'éviter certains des coups infligés par l'ennemi et facilite la fuite ;
    public abstract void esquive();
    //Permet de dépenser moins de points d'énergie lors des déplacements ;
    public abstract void endurance();
    
}
