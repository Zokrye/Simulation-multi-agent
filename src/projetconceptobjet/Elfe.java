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
public abstract class Elfe extends Hero {
    
    private static int nbElfesInGame;
    private static Species weakness;
    
    @Override
    public boolean isInSafeZone() {
        return this.currentCell.getZone()==Zone.SafeZoneElf;
    }
    
    public static void setNbElfesInGame(int nbElfesInGame) {
        Elfe.nbElfesInGame = nbElfesInGame;
    }
    public static void setWeakness(Species weakness) {
        Elfe.weakness = weakness;
    }
    public Elfe(int pEnergie,int pEnergieMax,int pVie,int pVieMax)
    {
        super(pEnergie,pEnergieMax,pVie,pVieMax);
        this.setType(Species.Elfe);
        Elfe.weakness=Species.Troll;
        this.safeZoneDirection=new Direction(1,1);
        this.maxMovement=6;
    }
    @Override
    public void attaquer()
    {
        System.out.println("ATTAQUE!");
    }
    @Override
    public void fuir()
    {
        System.out.println("FUITE!");
    }
    
    @Override
    public void reanimation()
    {
        System.out.println("REANIMATION!");
    }
        
    
    @Override
    public void soin()
    {
        System.out.println("SOIN!");
    }
    //Réanime les personnages fatigués avec des PEs ;
    
    //Augmente les stats d'un personnage en fonction d'une zone autour de lui et du nombre de ses alliés ;
    @Override
    public void soutenir()
    {
        System.out.println("SOUTENIR!");
    }
    
    //Distribue des points de vie aux alliés rencontrés ;
    
    

    
    /*
    Getters ;
    */
    public static int getNbElfesInGame() {
    
        return nbElfesInGame;
    
    }
    public static Species getWeakness() {
    
        return weakness;
    }
    /*
    Setters ;
    */
    
    

    //Permet de prendre la main sur les attaques lors des combats après le premier tour de jeux (attaque en premier);
    public void celerite()
    {
        System.out.println("CELERITE!");
    }
    //Permet d'éviter certains des coups infligés par l'ennemi et facilite la fuite ;
    public void esquive()
    {
        System.out.println("ESQUIVE!");
    }
    //Permet de dépenser moins de points d'énergie lors des déplacements ;
    public void endurance()
    {
        System.out.println("ENDURANCE!");
    }
    
    /**
     * Function allows to create a team of Elfes with a TribalChef and a random
     * distribution of Mage and Warriors, whose the number of team mates is given ;
     * @param nbPerso : number of team mates ;
     * @return : team : ArrayList of Elfes containning all characters of the team ;
     */
    public static Team createElfeTeam(int nbPerso)
    {
        
        //Initializing of the team instance ;
        Team e_team=new Team();
        //Set the total number of elfes in game ;
        Orc.setNbOrcsInGame(nbPerso);
        e_team.setTotalCharacterTeam(nbPerso);
        e_team.setType(Species.Elfe);
        //Create the list of the team ;
        ArrayList<Elfe> team=new ArrayList<>();
        //Creation of the Admiral of the team ;
        TribalChef captain=new TribalChef();
        //Rename the new instance ;
        captain.setNom("TribalChef_1");
        TribalChef.setNbTribalChefInGame(1);
        //Adding of the Admiral to the team ;
        team.add(captain);
        
        //Initializing of the variable to get the new perso craeted ;
        Elfe newPerso;
        //Initializing of the total number of Priests ;
        int nbProphets=0;
        //Initializing of the total number of Paladins ;
        int nbHunters=0;
        /*
        Preapre the variables to get the total number of each element of the characters ;
        */
        int nbPVTeamMax=0;
        int nbPVTeam=0;
        int nbPETeamMax=0;
        int nbPETeam=0;
        int nbXpTeam=0;
        /*
        Creation of the right number of characters to put them in the list ;
        */
        for(int index=1;index<nbPerso;index++)
        {
            //Trow to determine the type of the new character;
            int rthrow=RandomElement.randomThrow(2, 0);
            if (rthrow<1)
            {
                /*
                Instantiation of a Paladin,increasing of the number of instance and setting of its name ;
                */
                newPerso = new Hunter();
                nbHunters++;
                newPerso.setNom("Hunter_"+nbHunters);
                /*
                Initializing of the all elements of the team ;
                */
                nbPVTeam+=newPerso.getpVie();
                nbPVTeamMax+=newPerso.getpVieMax();
                nbPETeam+=newPerso.getpEnergie();
                nbPETeamMax+=newPerso.getpEnergieMax();
                nbXpTeam+=newPerso.getXp();
            }
            else
            {
                /*
                Instantiation of a Priest, increasing of the number of instance and setting of its name ;
                */
                newPerso = new Prophet();
                nbProphets++;
                newPerso.setNom("Prophet_"+nbProphets);
                /*
                Initializing of the all elements of the team ;
                */
                nbPVTeam+=newPerso.getpVie();
                nbPVTeamMax+=newPerso.getpVieMax();
                nbPETeam+=newPerso.getpEnergie();
                nbPETeamMax+=newPerso.getpEnergieMax();
                nbXpTeam+=newPerso.getXp();
            }
            //Add the new character to the Arraylist of the team;
            team.add(newPerso);
        }
        /*
        Setting all elements of the team ;
        */
        e_team.setListCharacters(team);
        e_team.setLifePointTeam(nbPVTeam);
        e_team.setTotalLifePointTeam(nbPVTeamMax);
        e_team.setEnergyPointTeam(nbPETeam);
        e_team.setTotalEnergyPointTeam(nbPETeamMax);
        e_team.setXpTeam(nbXpTeam);
        /*
        Set the number of each type of characters in game ;
        */
        Prophet.setNbProphettInGame(nbProphets);
        Hunter.setNbHunterInGame(nbHunters);
        //Returns the list of Elfe team mates;
        return(e_team);
    }
    
}
