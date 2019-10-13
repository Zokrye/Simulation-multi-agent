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
public class Paladin extends Human implements Warrior {
    
    //Attaque accrue;
    @Override
    public void manimentArmes()
    {
        System.out.println("MANIMENT ARMES");
    }
    //Attaque imparable (rarement);
    @Override
    public void perforationDefense()
    {
        System.out.println("PERFORATION DEFENSE");
    }
    
}
