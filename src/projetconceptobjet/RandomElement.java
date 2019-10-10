/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_concept_objet_simu_multi_agents;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author ISEN
 */
public class RandomElement {
    
    /**
     * Function that allows to create a new game table with a random choice of its size, chosing it in a list of predicted sizes
     */
    public static void randomCreationOfTable()
    {
        //List of lists of predicted dimensions;
        //First index of the list;
        int firstTableListIndex=0;
        //Should be the length of the tables list (constante/the number of the length isn't reached through the throw) ;
        int lengthTableList = 10;
        
        //Random throw to chose the one to use;
        randomThrow(lengthTableList,firstTableListIndex);
        //Take the good instance in the list of dimensions ;
        //Create the instance of the table ;
        //Calculation of the map dimensions (number of squares), use the random throw to dertermine the number of obstacles between 1/5 et 3/5 of the map;
        //Go through each lines and put the number of obstacles indicated by a random throw between 0 and 1/2 of the number of squares of the ligne (decrease the number of available obstacles);
        //Keep à list of their coordinates to know where they are ;
        //Return the Table instance to the game with obstacles;
        
    }
    
    
    /**
     * Funtion allowing to make a random throw when it is necessary;
     * @param indexMax : higher limit of the throw ;
     * @param indexMin : lower limit of the throw ;
     * @return randomIntValue : return the resulting number of the throw ;
     */
    public static int randomThrow(int indexMax, int indexMin)
    {        
        /*
        Initializing of the random entity and Throw of the random value ;
        */
        Random rand_1=new Random();
        int randomIntValue= rand_1.nextInt(indexMax-indexMin)+indexMin;
        return(randomIntValue);
    }
    
    
    
    /**
     * Function that call function to create teams of each character class ;
     * A REVOIR : Faire fonction de création d'équipe dans les classes correspondantes ;
     * @param mapLength
     * @param mapWidth 
     */
    public static ArrayList randomTeamsCreation(int mapLength,int mapWidth)
    {
        //Creation of a team of characters ;
        ArrayList<Character> teamOfCharacters=new ArrayList<>();
        //Calculation of the size of the map ;
        int mapDimension=mapLength*mapWidth;
        //Calculation of the number of characters of each team;
        double eighthOfMap=(mapDimension)/8;
        System.out.println("Eighth of the map : "+eighthOfMap+"; Map dimension : "+mapDimension+";");
        //Creation of each team ;
        ArrayList<Human> team_h=Human.createHumanTeam((int)eighthOfMap);
        return(team_h);
    }
    
}
