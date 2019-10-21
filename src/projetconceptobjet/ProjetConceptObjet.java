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
        ArrayList<Integer> numberThrown=new ArrayList<>();
        int indexMax=20;
        int indexMin=0;
        int n=20;
        int comparison;

            
            for(int index=0; index<n; index++)
            {
                if(numberThrown.isEmpty())
                {
                    int test=RandomElement.randomThrow(indexMax, indexMin);
                    numberThrown.add(test);
                    System.out.println("Rand : "+test+" ; nbList : "+numberThrown.get(0));
                }
                else if(!numberThrown.isEmpty())
                {
                    boolean eq;
                    do{
                        eq=false;
                        comparison=RandomElement.randomThrow(indexMax, indexMin);
                        //System.out.println("OK ici c'est bon : ELSE IF !");
                        for(int index1=0;index1<numberThrown.size();index1++)
                        {
                            System.out.println("Index : "+index1+" ; Rand : "+comparison+" ; nbList : "+numberThrown.get(index1)+" ; Equal : "+eq);
                            if(comparison==numberThrown.get(index1))
                            {
                                eq=true;
                            }
                        }
                    }
                    while(eq==true);
                    System.out.println("Après WHILE.");
                    numberThrown.add(comparison);
                }
            }
         
            for (int t=0;t<numberThrown.size();t++)
            {
                System.out.println("Nombre : "+numberThrown.get(t));
            }
        //////////////////////////////////////////////////////////////
        //////////////TEST CREATION D'EQUIPES ALEATOIRE////////////
        ArrayList<Team> allTeams=Team.randomTeamsCreation(12,12);
        
        //Recup of teams ;
        Team test=Team.recupTeamFromTheList(allTeams, Species.Human);
        System.out.println("Type of entity : "+test.getType()+" ; Nb characters : "+test.getTotalCharacterTeam()+" ; PV : "+test.getLifePointTeam()+"/"+test.getTotalLifePointTeam()+" ;\n"
                + "PE : "+test.getEnergyPointTeam()+"/"+test.getTotalEnergyPointTeam()+" ; XP : "+test.getXpTeam()+" ;");
        ///////////////////////////////////////////////////////////////
        /////////////TEST OF TURNS/////////////////////////////////////
        ProjetConceptObjet.newTurn(1,allTeams);
        
    }
    
    
    /**
     * Function launching a new turn in the simulation ;
     * @param turnPosition : Turn currently played ;
     */
    public static void newTurn(int turnPosition, ArrayList<Team> allTeams)
    {
        //Limit of the beggining of the new turn ;
        System.out.println("---------------------------------------------------------------\n"
                + "--------------------------TURN "+turnPosition+"-------------------------------");
        
        /*
        Recuperation of each team to create the order of game for the turn
        */
        /*Team tHuman=Team.recupTeamFromTheList(allTeams, Species.Human);
        //System.out.println("Type : "+tHuman.getType());
        Team tOrc=Team.recupTeamFromTheList(allTeams, Species.Orc);
        //System.out.println("Type : "+tOrc.getType());
        Team tElfe=Team.recupTeamFromTheList(allTeams, Species.Elfe);
        //System.out.println("Type : "+tElfe.getType());
        Team tTroll=Team.recupTeamFromTheList(allTeams, Species.Troll);
        //System.out.println("Type : "+tTroll.getType());*/
        
        RandomElement.randomOrderOfGameForTheTurn(allTeams);
        
        //End of the Turn ;
        System.out.println("=========================END OF THE TURN "+turnPosition+"==============================\n"
                + "========================================================================");
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
