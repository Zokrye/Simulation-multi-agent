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
public interface Warrior {
    
    //Constant
    final int XP_VALUE=500;
    
    //Attaque accrue;
    public abstract void manimentArmes();
    //Attaque imparable (rarement);
    public abstract void perforationDefense();
    
}
