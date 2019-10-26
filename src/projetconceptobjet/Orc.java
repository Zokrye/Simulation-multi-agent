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
public abstract class Orc extends Enemy {
    
    private static int nbOrcsInGame;
    private static Species weakness;
    
    
    @Override
    public boolean isInSafeZone() {
        return this.currentCell.getZone()==Zone.SafeZoneOrc;
    }
    
    public Orc(int pEnergie,int pEnergieMax,int pVie,int pVieMax)
    {
        super(pEnergie,pEnergieMax,pVie,pVieMax);
        this.setType(Species.Orc);
        Orc.weakness=Species.Elfe;
        this.safeZoneDirection=new Direction(1,-1);
        this.maxMovement=4;
    }

    
    /*
    Getters ;
    */
    public static int getNbOrcsInGame() {
        return nbOrcsInGame;
    }
    
    public static Species getWeakness() {
        return weakness;
    }

    
    /*
    Setters ;
     */
    public static void setNbOrcsInGame(int nbOrcsInGame) {
        Orc.nbOrcsInGame = nbOrcsInGame;
    }
    
    public static void setWeakness(Species weakness) {
        Orc.weakness = weakness;
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

    //Permet de finir le combat en un coup (peu de chance d'arriver) ;
    public void execution()
    {
        System.out.println("EXECUTER!");
    }
    
    //Permet d'augmeter la quantité d'xp reçut à la fin d'un combat ;
    public void experienceAccrue()
    {
        System.out.println("EXPERIENCE ACCRUE!");
    }
    
//Permet de bloquer la fuite à la victime ;
    public void harcelement()
    {
        System.out.println("HARCELEMENT!");
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
    public boolean isSameRace(Character character) {
        return character instanceof Orc;
    }
    
    /**
     * Function allows to create a team of Orcs with an OrcAlpha and a random
     * distribution of Mage and Warriors, whose the number of team mates is given ;
     * @param nbPerso : number of team mates ;
     * @return : team : ArrayList of Orcs containning all characters of the team ;
     */
    public static Team createOrcTeam(int nbPerso)
    {
        //Initializing of the team instance ;
        Team o_team=new Team();
        //Set the total number of orcs in game ;
        Orc.setNbOrcsInGame(nbPerso);
        o_team.setTotalCharacterTeam(nbPerso);
        o_team.setType(Species.Orc);
        //Create the list of the team ;
        ArrayList<Orc> team=new ArrayList<>();
        //Creation of the Admiral of the team ;
        OrcAlpha captain=new OrcAlpha();
        //Rename the new instance ;
        captain.setNom("OrcAlpha_1");
        OrcAlpha.setNbOrcAlphaInGame(1);
        //Adding of the Admiral to the team ;
        team.add(captain);
        
        //Initializing of the variable to get the new perso craeted ;
        Orc newPerso;
        //Initializing of the total number of Priests ;
        int nbSorcerers=0;
        //Initializing of the total number of Paladins ;
        int nbAssassins=0;
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
                newPerso = new Assassin();
                nbAssassins++;
                newPerso.setNom("Assassin_"+nbAssassins);
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
                newPerso = new Sorcerer();
                nbSorcerers++;
                newPerso.setNom("Sorcerer_"+nbSorcerers);
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
        o_team.setListCharacters(team);
        o_team.setLifePointTeam(nbPVTeam);
        o_team.setTotalLifePointTeam(nbPVTeamMax);
        o_team.setEnergyPointTeam(nbPETeam);
        o_team.setTotalEnergyPointTeam(nbPETeamMax);
        o_team.setXpTeam(nbXpTeam);
        /*
        Set the number of each type of characters in game ;
        */
        Assassin.setNbAssassinInGame(nbAssassins);
        Sorcerer.setNbSorcererInGame(nbSorcerers);
        //Returns the list of Orc team mates;
        return(o_team);
    }
}
