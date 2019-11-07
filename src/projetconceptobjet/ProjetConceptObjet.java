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
        /*ArrayList<Team> allTeams=Team.randomTeamsCreation(12,12);
        
        //Recup of teams ;
        Team test=Team.recupTeamFromTheList(allTeams, Human.class);
        System.out.println("Type of entity : "+test.getType()+" ; Nb characters : "+test.getTotalCharacterTeam()+" ; PV : "+test.getLifePointTeam()+"/"+test.getTotalLifePointTeam()+" ;\n"
                + "PE : "+test.getEnergyPointTeam()+"/"+test.getTotalEnergyPointTeam()+" ; XP : "+test.getXpTeam()+" ;");
        
        
        ///////////////////////////////////////////////////////////////
        /////////////TEST OF TURNS/////////////////////////////////////
        System.out.println("/////////////////////////Tests attaques des classes.////////////////////////");
        Hunter elfe_test=new Hunter();
        elfe_test.setNom("Hunter_test");
        Berserker troll_test=new Berserker();
        troll_test.setNom("Berserker_test");
        Assassin orc_test=new Assassin();
        orc_test.setNom("Assassin_test");
        Paladin human_test=new Paladin();
        human_test.setNom("Paladin_test");
        elfe_test.setpVie(20);
        orc_test.setpVie(20);
        elfe_test.setpEnergie(5);
        orc_test.setpEnergie(5);
        elfe_test.fight(orc_test);
        //elfe_test.attack(troll_test);
        //troll_test.setpVie(2);
        //elfe_test.attack(troll_test);
        //System.out.println("Troll is dead : "+troll_test.isDead());
        //elfe_test.doCalculationPE("+", 5);
        //elfe_test.doCalculationPE("-", 5);
        //elfe_test.doCalculationPV("+", 10);
        //elfe_test.doCalculationPV(-10);
        //troll_test.attack(elfe_test);
        //human_test.attack(orc_test);
        //orc_test.attack(human_test);
        //orc_test.checkPVCharacter();
        
        /*System.out.println("/////////////////////////Tests fuite des classes////////////////////////");
        //elfe_test.tryToEscape();
        
        ///////////////////////////////////////////////////////////////
        /////////////////TEST DE LA MAP////////////////////////////////
        System.out.println("/////////////////////////Tests Map////////////////////////");
        Map map=Map.getinstance();
        map.placeAllCharacters(false);
        ProjetConceptObjet.newTurn(1,allTeams);
        map.displayMap();*/
        
        ProjetConceptObjet.allGame();
    }
    
    
    /**
     * Main function of the simulation, it calls all the initializing functions
     * and acting functions to make characters acting. 
     */
    public static void allGame()
    {
        //Map creation
        Map mapOfGame=Map.getinstance();
        //Team creation
        ArrayList<Team> allTeams=Team.randomTeamsCreation(mapOfGame.HEIGHT, mapOfGame.LENGTH);
        //Place chracters on the map
        mapOfGame.placeAllCharacters(false);
        //Display of the map
        mapOfGame.displayMap();
        //Browse of turns
        int turn=0;
        while(Hero.nbHeroesInGame!=0 && Enemy.nbEnemiesInGame!=0) {
            ProjetConceptObjet.newTurn(turn, allTeams);
            turn++;
        }
        System.out.println("The game has ended");
        
    }
    
    /**
     * Function launching a new turn in the simulation ;
     * @param turnPosition : Turn currently played ;
     * @param allTeams : Lists of teams in game ;
     */
    public static void newTurn(int turnPosition, ArrayList<Team> allTeams)
    {
        //Limit of the beggining of the new turn ;
        System.out.println("=====================================================================\n"
                + "=======================TURN "+turnPosition+"====================================");
        //List of characters ordered for the turn ;
        ArrayList<Character> listPlayers=RandomElement.randomOrderOfGameForTheTurn(allTeams);
        Map mapOfGame=Map.getinstance();
        
        /*
        Course of the list to make each character play ;
        */
        for (Character character:listPlayers)
        {
            System.out.println("#####################################################################\n"
                    + "#####################TURN PERSO : "+character.getNom()+"#########################");
            System.out.println("Initial cell of the character position : "+character.getCurrentCell().x
                    +" ; "+character.getCurrentCell().y+"; \n"
                    + "His life : "+character.getpVie()+"/"+character.getpVieMax()+" PV ;\n"
                    + "His energy : "+character.getpEnergie()+"/"+character.getpEnergieMax()+" PE ;\n"
                    + "His XP : "+character.getXp()+".");
            /*
            Action of each character ;
            Tests if the character is dead or not ;
            */
            if(character.isDead()==false && character.isEtatFatigue()==false)
            {
                //Function to make characters acting ;
                character.seDeplacer();
            }
            /*
            Tests if the character is tired ;
            */
            else if(character.isEtatFatigue())
            {
                //Anounces it to the user ;
                System.out.println(character.getNom()+" is tired, he can't play this turn and waits for some help on the cell ("
                        +character.getCurrentCell().x+" ; "+character.getCurrentCell().y+").");
            }
            System.out.println("Final cell of the character position : "+character.getCurrentCell().x
                    +" ; "+character.getCurrentCell().y);
            System.out.println("#######################END OF THE TURN : "+character.getNom()+"################################\n"
                    + "########################################################################################\n");
        }
        Team.updateTeamLife(allTeams, listPlayers);
        //Display of the statistiques ;
        System.out.println("Statistics of the turn "+turnPosition+" :\n"
                + "Number of Characters in game : "+Character.getNbCharactersInGame()+" ;\n"
                + "Number of Heroes in game : "+Hero.getNbHeroesInGame()+" ;\n"
                + "Number of Enemies in game : "+Enemy.getNbEnemiesInGame()+" ;\n"
                + "\to Team Troll :\n"
                + "\t\tTotal life of the team : "+Team.recupTeamFromTheList(allTeams, Troll.class).getLifePointTeam()
                    +"/"+Team.recupTeamFromTheList(allTeams, Troll.class).getTotalLifePointTeam()+" PV ;\n"
                + "\t\tTotal energy of the team : "+Team.recupTeamFromTheList(allTeams, Troll.class).getEnergyPointTeam()
                    +"/"+Team.recupTeamFromTheList(allTeams, Troll.class).getTotalEnergyPointTeam()+" PE ;\n"
                + "\t\tTotal XP of the team : "+Team.recupTeamFromTheList(allTeams, Troll.class).getXpTeam()+" XP ;\n"
                + "\t\tNumber of Trolls in game : "+Troll.getNbTrollsInGame()+" ;\n"
                + "\t\tNumber of Troll Pack Masters alive : "+TrollPackMaster.getNbTrollPackMasterInGame()+" ;\n"
                + "\t\tNumber of Shamans alive : "+Shaman.getNbShamanInGame()+" ;\n"
                + "\t\tNumber of Berserkers alive : "+Berserker.getNbBerserkerInGame()+" ;\n"
                + "\to Team Orc :\n"
                + "\t\tTotal life of the team : "+Team.recupTeamFromTheList(allTeams, Orc.class).getLifePointTeam()
                    +"/"+Team.recupTeamFromTheList(allTeams, Orc.class).getTotalLifePointTeam()+" PV ;\n"
                + "\t\tTotal energy of the team : "+Team.recupTeamFromTheList(allTeams, Orc.class).getEnergyPointTeam()
                    +"/"+Team.recupTeamFromTheList(allTeams, Orc.class).getTotalEnergyPointTeam()+" PE ;\n"
                + "\t\tTotal XP of the team : "+Team.recupTeamFromTheList(allTeams, Orc.class).getXpTeam()+" XP ;\n"
                + "\t\tNumber of Orcs in game : "+Orc.getNbOrcsInGame()+" ;\n"
                + "\t\tNumber of Orc Alpha alive : "+AlphaOrc.getNbOrcAlphaInGame()+" ;\n"
                + "\t\tNumber of Sorcerer alive : "+Sorcerer.getNbSorcererInGame()+" ;\n"
                + "\t\tNumber of Assassins alive : "+Assassin.getNbAssassinInGame()+" ;\n"
                + "\to Team Human :\n"
                + "\t\tTotal life of the team : "+Team.recupTeamFromTheList(allTeams, Human.class).getLifePointTeam()
                    +"/"+Team.recupTeamFromTheList(allTeams, Human.class).getTotalLifePointTeam()+" PV ;\n"
                + "\t\tTotal energy of the team : "+Team.recupTeamFromTheList(allTeams, Human.class).getEnergyPointTeam()
                    +"/"+Team.recupTeamFromTheList(allTeams, Human.class).getTotalEnergyPointTeam()+" PE ;\n"
                + "\t\tTotal XP of the team : "+Team.recupTeamFromTheList(allTeams, Human.class).getXpTeam()+" XP ;\n"
                + "\t\tNumber of Humans in game : "+Human.getNbHumansInGame()+" ;\n"
                + "\t\tNumber of Adimrals alive : "+Admiral.getNbAdmiralInGame()+" ;\n"
                + "\t\tNumber of Priests alive : "+Priest.getNbPriestInGame()+" ;\n"
                + "\t\tNumber of Paladins alive : "+Paladin.getNbPaladinInGame()+" ;\n"
                + "\to Team Elfe :\n"
                + "\t\tTotal life of the team : "+Team.recupTeamFromTheList(allTeams, Elfe.class).getLifePointTeam()
                    +"/"+Team.recupTeamFromTheList(allTeams, Elfe.class).getTotalLifePointTeam()+" PV ;\n"
                + "\t\tTotal energy of the team : "+Team.recupTeamFromTheList(allTeams, Elfe.class).getEnergyPointTeam()
                    +"/"+Team.recupTeamFromTheList(allTeams, Elfe.class).getTotalEnergyPointTeam()+" PE ;\n"
                + "\t\tTotal XP of the team : "+Team.recupTeamFromTheList(allTeams, Elfe.class).getXpTeam()+" XP ;\n"
                + "\t\tNumber of Elves in game : "+Elfe.getNbElfesInGame()+" ;\n"
                + "\t\tNumber of Tribal Chef alive : "+TribalChief.getNbTribalChefInGame()+" ;\n"
                + "\t\tNumber of Prophets alive : "+Prophet.getNbProphetInGame()+" ;\n"
                + "\t\tNumber of Hunters alive : "+Hunter.getNbHunterInGame()+" ;\n");
        mapOfGame.displayMap();
        //End of the Turn ;
        System.out.println("=========================END OF THE TURN "+turnPosition+"==============================\n"
                + "========================================================================");
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
