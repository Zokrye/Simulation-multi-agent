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
        //Fonction ok pour un tirage unique de nombre ;
        //RandomElement.tirageListValeursUniques(72);
        /*ArrayList<Integer> numberThrown=new ArrayList<>();
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
            }*/
        //////////////////////////////////////////////////////////////
        //////////////TEST CREATION D'EQUIPES ALEATOIRE////////////
        ArrayList<Team> allTeams=Team.randomTeamsCreation(12,12);
        
        //Recup of teams ;
        Team test=Team.recupTeamFromTheList(allTeams, Species.Human);
        System.out.println("Type of entity : "+test.getType()+" ; Nb characters : "+test.getTotalCharacterTeam()+" ; PV : "+test.getLifePointTeam()+"/"+test.getTotalLifePointTeam()+" ;\n"
                + "PE : "+test.getEnergyPointTeam()+"/"+test.getTotalEnergyPointTeam()+" ; XP : "+test.getXpTeam()+" ;");
        
        Map map=Map.getinstance();
        map.placeAllCharacters();
        
        ///////////////////////////////////////////////////////////////
        /////////////TEST OF TURNS/////////////////////////////////////
        ProjetConceptObjet.newTurn(1,allTeams);
        
        
        map.displayMap();
    }
    
    
    /**
     * Function launching a new turn in the simulation ;
     * @param turnPosition : Turn currently played ;
     * @param allTeams : Lists of teams in game ;
     */
    public static void newTurn(int turnPosition, ArrayList<Team> allTeams)
    {
        //Limit of the beggining of the new turn ;
        System.out.println("---------------------------------------------------------------\n"
                + "--------------------------TURN "+turnPosition+"-------------------------------");
        //List of characters ordered for the turn ;
        ArrayList<Character> listPlayers=RandomElement.randomOrderOfGameForTheTurn(allTeams);
        
        /*
        Cursing of the list to make each character play ;
        */
        for (Character character:listPlayers)
        {
            
        }
        
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
    
    
    /**
     * Function allowing to sort the list of characters in the turn ;
     * @param listPlayers : List of living characters with their ranking number ;
     * @return : list of characters sorted.
     */
    public static ArrayList<Character> sortListCharacters(ArrayList<Character> listPlayers)
    {        
        /*
        Curse of all characters of the list ;
        */
        for(int index=0; index<listPlayers.size(); index++)
        {
            //Initializing the count of turn for the while loop ;
            int count=0;
            /*
            While count is under the size of the list, it executes the loop ;
            */
            while(count<listPlayers.size())
            {
                /*
                Test is the position of the character is the good one thank to its ranking number ;
                */
                if(index!=listPlayers.get(index).getTurnRanking())
                {
                    /*
                    We change the position of the character for the right one ;
                    */
                    Character tmp=listPlayers.get(index);
                    listPlayers.set(index, listPlayers.get(tmp.getTurnRanking()));
                    //System.out.println("Perso_2 : "+listPlayers.get(index).getTurnRanking());
                    listPlayers.set(tmp.getTurnRanking(),tmp);
                    //System.out.println("Perso_1 : "+listPlayers.get(tmp.getTurnRanking()).getTurnRanking());
                }
                //Increasing of the count to avoid infinte loop ;
                count++;
            }
        }
        
        /*
        Part of the function to test the sorting ;
        */
        System.out.println(":::::::::::::::::::::Ranked List of the turn::::::::::::::::::::::");
        for(int index=0; index<listPlayers.size(); index++)
        {
            System.out.println("Perso : "+listPlayers.get(index).getNom()+" ; rang : "+listPlayers.get(index).getTurnRanking());
        }
        
        return(listPlayers);
    }
    
}
