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
public interface Mediator {
    
    //Permet d'entamer des négociations de paix entre les différentes espèces ;
    public abstract void negociation();
    //Permet d'appeler les alliés à se regrouper aux alentours
    //OU
    //Permet la création de nouveaux alliés si ceux déjà présents possèdent un certain seuil d'xp nécessaire ;
    public abstract void ralliement();
    
}
