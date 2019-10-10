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
public class Admiral extends Human implements Mediator {
    
    @Override
    public void negociation()
    {
        System.out.println("NEGOCIATION!");
    }
    
    @Override
    public void ralliement()
    {
        System.out.println("RALLIEMENT!");
    }
    
    
    
}
