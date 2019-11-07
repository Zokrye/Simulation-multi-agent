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
    
    protected static int nbElfesInGame;
    private static Class weakness;
    private static Team elfeTeam;
    
    
    @Override
    public boolean isInSafeZone() {
        return this.currentCell.getZone()==Zone.SafeZoneElf;
    }
    
    @Override
    public abstract void removeOneCharacter();
    
    /*
    Getters ;
    */
    public static int getNbElfesInGame() {
    
        return nbElfesInGame;
    
    }
    public static Class getWeakness() {
    
        return weakness;
    }

    public static Team getElfeTeam() {
        return elfeTeam;
    }
    
    
    /*
    Setters ;
    */
    public static void setNbElfesInGame(int nbElfesInGame) {
        Elfe.nbElfesInGame = nbElfesInGame;
    }
    public static void setWeakness(Class weakness) {
        Elfe.weakness = weakness;
    }
    
    public static void setElfeTeam(Team elfeTeam) {
        Elfe.elfeTeam = elfeTeam;
    }
    
    /*
    Constructor ;
    */
    public Elfe(int pEnergie,int pEnergieMax,int pVie,int pVieMax, int strenght, int defense)
    {
        super(pEnergie,pEnergieMax,pVie,pVieMax,strenght,defense);
        Elfe.weakness=Troll.class;
        this.safeZoneDirection=new Direction(1,1);
        this.maxMovement=6;
    }
    
    
    /**
     * Function that allows to engage an enemy on the map during a fight;
     * It make character lose some PVs ;
     * For elves, this action doesn't consume any PE, they are too well physically ;
     * @param target : enemy targetted during the fight periode ;
     */
    @Override
    public void attack(Character target)
    {
        /*
        Tests the tiredness state of all characters to act and kill opponent directly if it is tired;
        */
        if(this.isEtatFatigue()==false && target.isEtatFatigue()==false)
        {
            /*
            Random calcul of the power of each attack and defense turn ;
            */
            int atkRandomValue=RandomElement.randomThrow(this.getStrenghtPoints(),0);
            int defRandomValue=RandomElement.randomThrow(target.getDefensivePoints(),0);
            //Add of the bonus given by xp of the character ;
            double strenghtATK=atkRandomValue+atkRandomValue*(this.getXp()/1000);
            //Rounding the final attack value to get an integer ;
            int valueATK=(int)strenghtATK;
            //Printing the message to the user ;
            System.out.println(this.getNom()+" attacks with multiple shots of powered arrows with a strenght of : "+valueATK+" ;");

            //Add of the bonus given by xp of the character ;
            double defAction=defRandomValue+defRandomValue*(target.getXp()/1000);
            //Rounding the final attack value to get an integer ;
            int valueDEF=(int)defAction;
            //Printing the message to the user ;
            System.out.println(target.getNom()+" defends with a resitance of : "+valueDEF+" ;");

            //Calulation of the end of the step ;
            int result=valueDEF-valueATK;

            target.doCalculationPV(result);
            target.checkPVCharacter();
            /*
            If the result is negative, the target is shot with damages ;
            */
            if(result<0)
            {
                System.out.println("Dammages of : "+result+" taken by "+target.getNom()+" : his life is now : "+target.getpVie()+"/"+target.getpVieMax()+" PV ;");
            }

            /*
            If it is positive, the damages are given to the attacking one ;
            */
            else if(result>0)
            {
                System.out.println("Dammages of : "+result+" taken by "+this.getNom()+" : his life is now : "+this.getpVie()+"/"+this.getpVieMax()+" PV ;");
            }

            //Printing of the result of the step ;
                System.out.println("\nScoring of the step :\n"
                        + this.getNom()+" : "+this.getpVie()+"/"+this.getpVieMax()+" PV  & "+this.getpEnergie()+"/"+this.getpEnergieMax()+" PE ;\n"
                        + target.getNom()+" : "+target.getpVie()+"/"+target.getpVieMax()+" PV & "+target.getpEnergie()+"/"+target.getpEnergieMax()+" PE ;");
        }
        else if (this.isEtatFatigue()==false && target.isEtatFatigue()==true)
        {
            target.kill();
            System.out.println(target.getNom()+" was too tired to resist. "+this.getNom()+" pierces him easily.");
        }
        else
        {
            System.out.println(this.getNom()+" is tired. He can't attack.");
        }
    }
    
    
    /**
     * Function to try to escape from a fight ;
     * Elves don't need to pay any PEs to try to escape ;
     * Some PEs and PVs are lost if it fails.
     * @param character : targetted character of the fight.
     * @return : goneAway is a boolean to indicate that the character has escaped from the fight.
     */
    @Override
    public boolean tryToEscape(Character character)
    {
        /*
        Cost of the action ;
        */
        boolean goneAway=false;
        int failingCostPE=-10;
        int failingCostPV=-5;
        System.out.println("ESCAPE : "+this.getNom()+" try to escape the fight.");
        /*
        Initializing of all the variable;
        Some Elfe bonus thanks to their agility ;
        A random thrown to determine the right to escape from the fight ;
        */
        int bonusEscapeElfe=10 ;
        int valueEscape;
        int difference;
        int randomThrown=RandomElement.randomThrow(100, 0);
        
        /*
        Test if the sum of the random value and the bonus is lower than 99 to know if we should aplly the bonus or not ;
        */
        if((randomThrown+bonusEscapeElfe)<=99)
        {
            valueEscape=randomThrown+bonusEscapeElfe;
        }
        else
        {
            valueEscape=99;
        }
        System.out.println("Elfic bonus is applied.");
        
        /*
        Test if the value is perfect and the escape can't be stopped ;
        */
        if(valueEscape==99)
        {
                System.out.println("PERFECT! "+this.getNom()+" escapes from the fight without any problems.");
                goneAway=true;
                //Moving Function to go away ;
                escapeFrom(character);
        }
        
        else
        {
            /*
            Random thrown form the enemy to keep the character in the fight ;
            */
            randomThrown=RandomElement.randomThrow(100, 0);
            difference=valueEscape-randomThrown;
            if(difference<0)
            {
                System.out.println("Escape : "+difference+". The attempt to escape from the fight has failed!\n"+this.getNom()+" lose some PEs and PVs.");
                //this.doCalculationPE(failingCostPE);
                this.doCalculationPV(failingCostPV);
                this.checkPVCharacter();
                this.checkPECharacter();
                //Test PVs ;
            }
            else
            {
                System.out.println("Escape : "+difference+". The attempt to escape from the fight is successful!\n"+this.getNom()+" goes away.");
                goneAway=true;
                //Moving Function to go away ;
                escapeFrom(character);
            }
        }
        System.out.println("\nScoring of the step :\n"
                        + this.getNom()+" : "+this.getpVie()+"/"+this.getpVieMax()+" PV  & "+this.getpEnergie()+"/"+this.getpEnergieMax()+" PE ;\n");
        return(goneAway);
    }
        
    //Distribue des points de vie aux alliés rencontrés ;
    @Override
    public void soin()
    {
        System.out.println("SOIN!");
    }
    
    //Augmente les stats d'un personnage en fonction d'une zone autour de lui et du nombre de ses alliés ;
    @Override
    public void soutenir()
    {
        System.out.println("SOUTENIR!");
    }
    
    
    
    @Override
    public boolean isSameRace(Character character) {
        return character instanceof Elfe;
    }


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
     * Function allows to create a team of Elfes with a TribalChief and a random
     * distribution of Mage and Warriors, whose the number of team mates is given ;
     * @param nbPerso : number of team mates ;
     * @return : team : ArrayList of Elfes containning all characters of the team ;
     */
    public static Team createElfeTeam(int nbPerso)
    {
        
        //Initializing of the team instance ;
        Team e_team=new Team();
        //Set the total number of elfes in game ;
        Elfe.setNbElfesInGame(nbPerso);
        e_team.setTotalCharacterTeam(nbPerso);
        e_team.setType(Elfe.class);
        //Create the list of the team ;
        ArrayList<Elfe> team=new ArrayList<>();
        //Creation of the Admiral of the team ;
        TribalChief captain=new TribalChief();
        //Rename the new instance ;
        captain.setNom("TribalChef_1");
        TribalChief.setNbTribalChefInGame(1);
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
        //Set the static variable of the team ;
        Elfe.setElfeTeam(e_team);
        //Returns the list of Elfe team mates;
        return(elfeTeam);
    }
    
}
