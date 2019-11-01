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
public interface Wizzard {
    
    //Constant
    final int XP_VALUE=350;
    //Bonus ou malus sur la cible ;
    public abstract void sortStats();
    //Permet d'élargir le champs de perception du personnage lors des déplacements pour éviter ou suprimmer les ennemis
    //ou soutenir les alliés ;
    public abstract void detection();
    
}
