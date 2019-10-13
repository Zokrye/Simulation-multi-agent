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
public abstract class Orc extends Enemy {
    
    private static int nbOrcsInGame;
    private static Species weakness;
    
    //Permet de finir le combat en un coup (peu de chance d'arriver) ;
    public abstract void execution();
    //Permet d'augmeter la quantité d'xp reçut à la fin d'un combat ;
    public abstract void experienceAccrue();
    //Permet de bloquer la fuite à la victime ;
    public abstract void harcelement();
}
