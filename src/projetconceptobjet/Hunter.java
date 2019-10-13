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
public class Hunter extends Elfe implements Warrior {
    
    private static int nbHunterInGame;
    
    public Hunter()
    {
        super(200,200,300,300);
    }
    
    
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
    
    //Permet de prendre la main sur les attaques lors des combats après le premier tour de jeux (attaque en premier);
    @Override
    public void celerite()
    {
        System.out.println("CELERITE!");
    }
    //Permet d'éviter certains des coups infligés par l'ennemi et facilite la fuite ;
    @Override
    public void esquive()
    {
        System.out.println("ESQUIVE!");
    }
    //Permet de dépenser moins de points d'énergie lors des déplacements ;
    @Override
    public void endurance()
    {
        System.out.println("ENDURANCE!");
    }
    
}
