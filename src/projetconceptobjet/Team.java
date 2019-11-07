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
public class Team {
    
    
    /*
    Attributes
    */
    private ArrayList<? extends Character> listCharacters;
    private int lifePointTeam;
    private int totalLifePointTeam;
    private int xpTeam;
    private int totalCharacterTeam;
    private int energyPointTeam;
    private int totalEnergyPointTeam;
    private Class type;
    private static int nbOfTeam;
    private static ArrayList<Team> allTeams;
    
    /*Getters*/
    public ArrayList<? extends Character> getListCharacters() {
        return listCharacters;
    }
    
    public int getLifePointTeam() {
        return lifePointTeam;
    }
    
    public int getTotalLifePointTeam() {
        return totalLifePointTeam;
    }

    public int getXpTeam() {
        return xpTeam;
    }
    
    public int getTotalCharacterTeam() {
        return totalCharacterTeam;
    }
    
    public int getTotalEnergyPointTeam() {
        return totalEnergyPointTeam;
    }

    public int getEnergyPointTeam() {
        return energyPointTeam;
    }

    public Class getType() {
        return type;
    }

    public static int getNbOfTeam() {
        return nbOfTeam;
    }

    public static ArrayList<Team> getAllTeams() {
        return allTeams;
    }   
    
    /*Setters*/
    public void setListCharacters(ArrayList<? extends Character> listCharacters) {
        this.listCharacters = listCharacters;
    }

    public void setLifePointTeam(int lifePointTeam) {
        this.lifePointTeam = lifePointTeam;
    }

    public void setTotalLifePointTeam(int totalLifePointTeam) {
        this.totalLifePointTeam = totalLifePointTeam;
    }

    public void setXpTeam(int xpTeam) {
        this.xpTeam = xpTeam;
    }

    public void setTotalCharacterTeam(int totalCharacterTeam) {
        this.totalCharacterTeam = totalCharacterTeam;
    }

    public void setTotalEnergyPointTeam(int totalEnergyPointTeam) {
        this.totalEnergyPointTeam = totalEnergyPointTeam;
    }
    
    public void setEnergyPointTeam(int energyPointTeam) {
        this.energyPointTeam = energyPointTeam;
    }
    
    public void setType(Class type) {
        this.type = type;
    }
    
    public static void setNbOfTeam(int nbOfTeam) {
        Team.nbOfTeam = nbOfTeam;
    }
    
    public static void setAllTeams(ArrayList<Team> allTeams) {
        Team.allTeams = allTeams;
    }
    
    
    /*
    Methods
    */
    /**
     * Function affording to get the team searched ;
     * @param allTeams : List of all teams of the game ;
     * @param type : Type of the team ;
     * @return : team_found : Team of the type wanted ;
     */
    public static Team recupTeamFromTheList(ArrayList<Team> allTeams, Class type)
    {   
        /*
        Initializing of the variables used ;
        */
        int count=0;
        Team team=null;
        
        /*
        Get the team at the index corresponding to the counter number,
        While the type of the instance of the Team class is different of the type
        searched or the index isn't out of the list range,
        It continue to increment the index ;
        */
        do{
            team=allTeams.get(count);
            count++;
        }
        while(team.getType()!=type && count<allTeams.size());
        //Return of the team found ;
        return(team);  
    }
    
    
    /**
     * Function that call function to create teams of each character class ;
     * A REVOIR : Faire fonction de création d'équipe dans les classes correspondantes ;
     * @param mapLength : dimension of the length of the map ;
     * @param mapWidth : dimension of the width of the map ;
     * @return : List of Teams of characters ;
     */
    public static ArrayList<Team> randomTeamsCreation(int mapLength,int mapWidth)
    {
        //Creation of a new Team ArrayList ;
        ArrayList<Team> allTeams = new ArrayList<>();
        //Calculation of the size of the map ;
        int mapDimension=mapLength*mapWidth;
        //Calculation of the number of characters of each team;
        double eighthOfMap=(mapDimension)*0.02;
        //double eighthOfMap=6;
        Character.setNbCharactersInGame((int)eighthOfMap*4);
        Hero.setNbHeroesInGame((int)eighthOfMap*2);
        Enemy.setNbEnemiesInGame((int)eighthOfMap*2);
        System.out.println("Eighth of the map : "+eighthOfMap+"; Map dimension : "+mapDimension+";");
        /*
        Creation of each team ;
        */
        //Human Team ;
        Team teamHuman=Human.createHumanTeam((int)eighthOfMap);
        System.out.println("Type of entity : "+teamHuman.getType()+" ; Nb characters : "+teamHuman.getTotalCharacterTeam()+" ; PV : "+teamHuman.getLifePointTeam()+"/"+teamHuman.getTotalLifePointTeam()+" ;\n"
                + "PE : "+teamHuman.getEnergyPointTeam()+"/"+teamHuman.getTotalEnergyPointTeam()+" ; XP : "+teamHuman.getXpTeam()+" ;");
        allTeams.add(teamHuman);
        
        //Orc Team ;
        Team teamOrc=Orc.createOrcTeam((int)eighthOfMap);
        System.out.println("Type of entity : "+teamOrc.getType()+" ; Nb characters : "+teamOrc.getTotalCharacterTeam()+" ; PV : "+teamOrc.getLifePointTeam()+"/"+teamOrc.getTotalLifePointTeam()+" ;\n"
                + "PE : "+teamOrc.getEnergyPointTeam()+"/"+teamOrc.getTotalEnergyPointTeam()+" ; XP : "+teamOrc.getXpTeam()+" ;");
        allTeams.add(teamOrc);
        
        //Elfe Team ;
        Team teamElfe=Elfe.createElfeTeam((int)eighthOfMap);
        System.out.println("Type of entity : "+teamElfe.getType()+" ; Nb characters : "+teamElfe.getTotalCharacterTeam()+" ; PV : "+teamElfe.getLifePointTeam()+"/"+teamElfe.getTotalLifePointTeam()+" ;\n"
                + "PE : "+teamElfe.getEnergyPointTeam()+"/"+teamElfe.getTotalEnergyPointTeam()+" ; XP : "+teamElfe.getXpTeam()+" ;");
        allTeams.add(teamElfe);
        
        //Troll Team ;
        Team teamTroll=Troll.createTrollTeam((int)eighthOfMap);
        System.out.println("Type of entity : "+teamTroll.getType()+" ; Nb characters : "+teamTroll.getTotalCharacterTeam()+" ; PV : "+teamTroll.getLifePointTeam()+"/"+teamTroll.getTotalLifePointTeam()+" ;\n"
                + "PE : "+teamTroll.getEnergyPointTeam()+"/"+teamTroll.getTotalEnergyPointTeam()+" ; XP : "+teamTroll.getXpTeam()+" ;");
        allTeams.add(teamTroll);
        
        /*
        Setting the number of alive teams in game ;
        */
        Team.setNbOfTeam(allTeams.size());
        System.out.println("Number of Teams : "+Team.getNbOfTeam()+" ;");
        Team.allTeams=allTeams;
        return(allTeams);
    }
     
    
    /**
     * Function to calculate and set the values of all teams in the list : total life, total energie, total xp ;
     * @param allTeams : List of teams ;
     * @param orderedList : List of characters in game ;
     */
    public static void updateTeamLife(ArrayList<Team> allTeams,ArrayList<Character> orderedList)
    {
        /*
        Initializing the sums of each parameter ;
        */
        int e_life;
        int e_xp;
        int e_energie;        
        
        /*
        Go through all teams of the list ;
        */
        for(Team team:allTeams)
        {
            /*
            Initializing values of each sums to 0 ;
            */
           e_life=0;
           e_xp=0;
           e_energie=0;
           /*
           Go through the list of characters ordered ;
           */
           for(Character character:orderedList)
           {
               /*
               Test if the character is a part of the team ;
               */
               if(team.getType().isInstance(character))
               {
                   /*
                   Add his stats to the sums ;
                   */
                   e_life+=character.getpVie();
                   //System.out.println(character.getNom()+" : "+character.getpVie()+"/"+character.getpVieMax()+" PV et pour le total : "+e_life+" PV;");
                   e_xp+=character.getXp();
                   e_energie+=character.getpEnergie();
               }
           }
           /*
           Set the team stats with the sums ;
           */
           team.setLifePointTeam(e_life);
           team.setEnergyPointTeam(e_energie);
           team.setXpTeam(e_xp);
        }
    }
    
}
