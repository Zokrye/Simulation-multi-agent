/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetconceptobjet;

import java.util.ArrayList;

/**
 *
 * @author ISEN
 */
public class Projet_Concept_Objet_Simu_Multi_Agents {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /////////////////////////////////////////////////////////////
        ////////////////TEST TIRAGE ALEATOIRE////////////////////////
        int indexMax=100;
        int indexMin=0;
        int n=500;
        for(int index=0; index<n; index++)
        {
            //System.out.println(RandomElement.randomCreationOfTable());
        }
        //////////////////////////////////////////////////////////////
        //////////////TEST CREATION D'UNE EQUIPE ALEATOIRE////////////
        ArrayList<Human> hTeam=RandomElement.randomTeamsCreation(12,12);
        
        //ArrayList<Human> hTeam=Human.createHumanTeam(12);
        int size_hTeam=hTeam.size();
        for(int index=0;index<size_hTeam;index++)
        {
            Human perso=hTeam.get(index);
            System.out.println(perso.getNom());
        }
        
        
    }
    
    
    /**
     * Function that allows the user to create a map with its own dimensions,
     * keeping some control on it to avoid size problems;
     * !!!!!!!!!!!!!! A prévoir dans la fonction principale : init dimension carte en les demandant à l'utilisateur (bloquage à partir de dimension 5);
     */
    public void createTableWithUser()
    {
        
    }
    
}
