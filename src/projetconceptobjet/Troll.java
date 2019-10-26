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
public abstract class Troll extends Enemy {
    
    private static int nbTrollsInGame;
    private static Species weakness;
    
    @Override
    public boolean isInSafeZone() {
        return this.currentCell.getZone()==Zone.SafeZoneTroll;
    }
    
    public Troll(int pEnergie,int pEnergieMax,int pVie,int pVieMax)
    {
        super(pEnergie,pEnergieMax,pVie,pVieMax);
        this.setType(Species.Troll);
        Troll.weakness=Species.Human;
        this.safeZoneDirection=new Direction(-1,1);
        this.maxMovement=3;
    }

    
    /*
    Getters ;
    */
    public static int getNbTrollsInGame() {
        return nbTrollsInGame;
    }

    public static Species getWeakness() {
        return weakness;
    }

    
    /*
    Setters ;
    */
    public static void setWeakness(Species weakness) {
        Troll.weakness = weakness;
    }
    
    public static void setNbTrollsInGame(int nbTrollsInGame) {
        Troll.nbTrollsInGame = nbTrollsInGame;
    }
    
    @Override
    public boolean isSameRace(Character character) {
        return character instanceof Troll;
    }
    
    //Augmente des caractéristiques défensive du Troll ;
    public void blindage()
    {
        System.out.println("BLINDAGE!");
    }
    
    //Permet de déplacer les obstacles du terrain;
    public void modificationTerrain()
    {
        System.out.println("MODIFICATION TERRAIN!");
    }
    
    //Diminu les stats des adversaire lors des combats ;
    public void impressionnant()
    {
        System.out.println("IMPRESSIONNANT!");
        
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
    
    //Destruction d'un allié pour récupérer son xp si celui-ci est fatigué ;
    @Override
    public void sacrifice()
    {
        System.out.println("SACRIFICE!");
    }
    
    
    //Application de malus sur les statistiques adverses ;
    @Override
    public void fleau()
    {
        System.out.println("FLEAU!");
    }
    
    
    //Application d'un bonus d'attaque et perte de points d'Energie/Mana accrue pour les 2 ou 3 tours suivants ;
    @Override
    public void surchargeDePuissance()
    {
        System.out.println("SURCHARGE DE PUISSANCE!");
    }
    
    
    /**
     * Function allows to create a team of Trolls with a TrollPackMaster and a random
     * distribution of Mage and Warriors, whose the number of team mates is given ;
     * @param nbPerso : number of team mates ;
     * @return : team : ArrayList of Trolls containning all characters of the team ;
     */
    public static Team createTrollTeam(int nbPerso)
    {
        //Initializing of the team instance ;
        Team t_team=new Team();
        //Set the total number of elfes in game ;
        Troll.setNbTrollsInGame(nbPerso);
        t_team.setTotalCharacterTeam(nbPerso);
        t_team.setType(Species.Troll);
        //Create the list of the team ;
        ArrayList<Troll> team=new ArrayList<>();
        //Creation of the Admiral of the team ;
        TrollPackMaster captain=new TrollPackMaster();
        //Rename the new instance ;
        captain.setNom("TrollPackMaster_1");
        TrollPackMaster.setNbTrollPackMasterInGame(1);
        //Adding of the Admiral to the team ;
        team.add(captain);
        
        //Initializing of the variable to get the new perso craeted ;
        Troll newPerso;
        //Initializing of the total number of Priests ;
        int nbShamans=0;
        //Initializing of the total number of Paladins ;
        int nbBerserkers=0;
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
                newPerso = new Berserker();
                nbBerserkers++;
                newPerso.setNom("Berserker_"+nbBerserkers);
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
                newPerso = new Shaman();
                nbShamans++;
                newPerso.setNom("Shaman_"+nbShamans);
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
        t_team.setListCharacters(team);
        t_team.setLifePointTeam(nbPVTeam);
        t_team.setTotalLifePointTeam(nbPVTeamMax);
        t_team.setEnergyPointTeam(nbPETeam);
        t_team.setTotalEnergyPointTeam(nbPETeamMax);
        t_team.setXpTeam(nbXpTeam);
        /*
        Set the number of each type of characters in game ;
        */
        Shaman.setNbShamanInGame(nbShamans);
        Berserker.setNbBerserkerInGame(nbBerserkers);
        //Returns the list of troll team mates;
        return(t_team);
    }
}
