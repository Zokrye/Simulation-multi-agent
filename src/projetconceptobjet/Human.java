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
public abstract class Human extends Hero {
    
    private static int nbHumansInGame;
    private static Species weakness;
    
    public Human(int pEnergie,int pEnergieMax,int pVie,int pVieMax)
    {
        super(pEnergie,pEnergieMax,pVie,pVieMax);
        this.setType(Species.Human);
        Human.weakness=Species.Orc;
        this.safeZoneDirection=new Direction(-1,-1);
        this.maxMovement=5;
    }
    
    @Override
    public boolean isInSafeZone() {
        return this.currentCell.getZone()==Zone.SafeZoneMan;
    }
    
    /*
    Getters ;
    */
    public static int getNbHumansInGame() {
        return nbHumansInGame;
    }
    
    public static Species getWeakness() {
        return weakness;
    }

    
    /*
    Setters ;
    */
    public static void setNbHumansInGame(int nbHumansInGame) {
        Human.nbHumansInGame = nbHumansInGame;
    }

    public static void setWeakness(Species weakness) {
        Human.weakness = weakness;
    }
    
    
    //Regain de PV par tours ;
    public void secondSouffle()
    {
        System.out.println("SECOND SOUFFLE!");
    }
    
    //Augmentation des dégats au fur et à mesure du combat ;
    public void perseverance()
    {
        System.out.println("PERSEVERANCE!");
    }
    
    //Attire des alliés autours de lui lors des combats ;
    public void tousPourUn()
    {
        System.out.println("TOUS POUR UN!");
    }
    
    //Augmente les stats d'un personnage en fonction d'une zone autour de lui et du nombre de ses alliés ;
    @Override
    public void soutenir()
    {
        System.out.println("SOUTENIR!");
    }
    
    //Distribue des points de vie aux alliés rencontrés ;
    @Override
    public void soin()
    {
        System.out.println("SOIN!");
    }
    //Réanime les personnages fatigués avec des PEs ;
    @Override
    public void reanimation()
    {
        System.out.println("REANIMATION!");
    }
    
    @Override
    public void seDeplacer()
    {
        System.out.println("DEPLACEMENT!");
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
    
    
    /**
     * AJOUTER LA MEME FONCTION POUR LES AUTRES CLASSES ;
     * Function allows to create a team of Humans with an Admiral and a random
     * distribution of Mage and Warriors, whose the number of team mates is given ;
     * @param nbPerso : number of team mates ;
     * @return : team : Team of Humans containning all characters of the team ;
     */
    public static Team createHumanTeam(int nbPerso)
    {
        //Initializing of the team instance ;
        Team h_team=new Team();
        //Set the total number of humans in game ;
        Human.setNbHumansInGame(nbPerso);
        h_team.setTotalCharacterTeam(nbPerso);
        h_team.setType(Species.Human);
        //Create the list of the team ;
        ArrayList<Human> team=new ArrayList<>();
        //Creation of the Admiral of the team ;
        Admiral captain=new Admiral();
        //Rename the new instance ;
        captain.setNom("Admiral_1");
        //Adding of the Admiral to the team ;
        team.add(captain);
        
        //Initializing of the variable to get the new perso craeted ;
        Human newPerso;
        //Initializing of the total number of Priests ;
        int nbPriests=0;
        //Initializing of the total number of Paladins ;
        int nbPaladins=0;
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
                newPerso = new Paladin();
                nbPaladins++;
                newPerso.setNom("Paladin_"+nbPaladins);
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
                newPerso = new Priest();
                nbPriests++;
                newPerso.setNom("Priest_"+nbPriests);
                /*
                Initializing of the all elements of the team ;
                */
                nbPVTeam+=newPerso.getpVie();
                nbPVTeamMax+=newPerso.getpVieMax();
                nbPETeam+=newPerso.getpEnergie();
                nbPETeamMax+=newPerso.getpEnergieMax();
                nbXpTeam+=newPerso.getXp();
            }
            team.add(newPerso);
        }
        /*
        Setting all elements of the team ;
        */
        h_team.setListCharacters(team);
        h_team.setLifePointTeam(nbPVTeam);
        h_team.setTotalLifePointTeam(nbPVTeamMax);
        h_team.setEnergyPointTeam(nbPETeam);
        h_team.setTotalEnergyPointTeam(nbPETeamMax);
        h_team.setXpTeam(nbXpTeam);
        /*
        Set the total number of each type of character in game ;
        */
        Paladin.setNbPaladinInGame(nbPaladins);
        Priest.setNbPriestInGame(nbPriests);
        //Returns the list of Human team mates;
        return(h_team);
    }
    
    
}
