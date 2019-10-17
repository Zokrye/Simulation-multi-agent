/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetconceptobjet;

import java.util.ArrayList;

/**
 *
 * @author Alexandre
 */
public class ProjetConceptObjet {

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
        //////////////TEST CREATION D'EQUIPES ALEATOIRE////////////
        ArrayList<Team> allTeams=Team.randomTeamsCreation(12,12);
        
        Team test=Team.recupTeamFromTheList(allTeams, Species.Human);
        System.out.println("Type of entity : "+test.getType()+" ; Nb characters : "+test.getTotalCharacterTeam()+" ; PV : "+test.getLifePointTeam()+"/"+test.getTotalLifePointTeam()+" ;\n"
                + "PE : "+test.getEnergyPointTeam()+"/"+test.getTotalEnergyPointTeam()+" ; XP : "+test.getXpTeam()+" ;");
        
    }
    
    
    /**
     * Function launching a new turn in the simulation ;
     * @param turnPosition : Turn currently played ;
     */
    public void newTurn(int turnPosition)
    {
        //Limit of the beggining of the new turn ;
        System.out.println("---------------------------------------------------------------------------------"
                + "--------------------------TURN "+turnPosition+"-------------------------------");
        
        
        
        
        //End of the Turn ;
        System.out.println("=========================END OF THE TURN "+turnPosition+"=============================="
                + "==================================================================================");
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
