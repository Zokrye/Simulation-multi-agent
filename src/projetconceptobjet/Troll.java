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
    private static Class weakness;
    
    
    
    public Troll(int pEnergie,int pEnergieMax,int pVie,int pVieMax,int strenght, int defense)
    {
        super(pEnergie,pEnergieMax,pVie,pVieMax,strenght, defense);
        Troll.weakness=Human.class;
        this.safeZoneDirection=new Direction(-1,1);
        this.maxMovement=3;
    }

    @Override
    public boolean isInSafeZone() {
        return this.currentCell.getZone()==Zone.SafeZoneTroll;
    }
    
    /*
    Getters ;
    */
    public static int getNbTrollsInGame() {
        return nbTrollsInGame;
    }

    public static Class getWeakness() {
        return weakness;
    }

    /*
    Setters ;
    */
    public static void setWeakness(Class weakness) {
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
    
    
    /**
     * Function that allows to engage an enemy on the map during a fight;
     * It make character lose some PVs and some PEs to do this action ;
     * For trolls, this action consumes PE.
     * @param target : enemy targetted during the fight periode ;
     */
    @Override
    public void attack(Character target)
    {
        int costAtkPE=-10;
        /*
        Tests the value of PEs of the character and decide if he can attack or not ;
        Tests also the tiredness state of the opponent to kill him directly or not;
        */
        if( this.getpEnergie()>=(-costAtkPE) && this.isEtatFatigue()==false && target.isEtatFatigue()==false)
        {
            /*
            Random calcul of the power of each attack and defense turn ;
            */
            //this.doCalculationPE(costAtkPE);
            int atkRandomValue=RandomElement.randomThrow(this.getStrenghtPoints(),0);
            int defRandomValue=RandomElement.randomThrow(target.getDefensivePoints(),0);
            //Add of the bonus given by xp of the character ;
            double strenghtATK=atkRandomValue+atkRandomValue*(this.getXp()/1000);
            //Rounding the final attack value to get an integer ;
            int valueATK=(int)strenghtATK;
            //Printing the message to the user ;
            System.out.println(this.getNom()+" attacks with a bloody mass crash with a strenght of : "+valueATK+" ;");

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
                System.out.println("Dammages of : "+result+" are got by "+target.getNom()+" : his life is now of : "+target.getpVie()+"/"+target.getpVieMax()+" PV ;");
            }

            /*
            If it is positive, the damages are given to the attacking one ;
            */
            else if(result>0)
            {
                System.out.println("Dammages of : "+result+" are got by "+this.getNom()+" : his life is now of : "+this.getpVie()+"/"+this.getpVieMax()+" PV ;");
            }

            //Printing of the result of the step ;
            System.out.println("\nScoring of the step :\n"
                    + this.getNom()+" : "+this.getpVie()+"/"+this.getpVieMax()+" PV  & "+this.getpEnergie()+"/"+this.getpEnergieMax()+" PE ;\n"
                    + target.getNom()+" : "+target.getpVie()+"/"+target.getpVieMax()+" PV & "+target.getpEnergie()+"/"+target.getpEnergieMax()+" PE ;");
        }
        else if (this.getpEnergie()>=costAtkPE && this.isEtatFatigue()==false && target.isEtatFatigue()==true)
        {
            target.kill();
            System.out.println(target.getNom()+" was too tired to resist. "+this.getNom()+" cruhes him easily.");
        }
        //The character can't attack because of his lack of PEs ;
        else
        {
            System.out.println(this.getNom()+" can't attack, he is too tired to get his mass up again.");
        }
    }
    
    /**
     * Function to try to escape from a fight ;
     * Trolls don't need to pay any PEs to try to escape ;
     * Some PEs and PVs are lost if it fails.
     * @param character : targetted character of the fight,
     * @return : goneAway is a boolean to indicate that the character has escaped from the fight.
     */
    @Override
    public boolean tryToEscape(Character character)
    {
        /*
        Cost of the action ;
        */
        int costPEEscape=-10;
        int failingCostPE=-15;
        int failingCostPV=-2;
        boolean goneAway=false;
        if(this.getpEnergie()>=(-costPEEscape))
        {
            System.out.println("ESCAPE : "+this.getNom()+" try to escape himself from the fight.");
            //this.doCalculationPE(costPEEscape);
            /*
            Initializing of all the variable;
            A random thrown to determine the right to escape from the fight ;
            */
            int valueEscape=RandomElement.randomThrow(100, 0);
            int difference;

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
                int randomThrown=RandomElement.randomThrow(100, 0);
                difference=valueEscape-randomThrown;
                if(difference<0)
                {
                    System.out.println("Escape : "+difference+". The attempt to escape from the fight has failed!\n"+this.getNom()+" lose some PEs and PVs.");
                    //this.doCalculationPE(failingCostPE);
                    this.doCalculationPV(failingCostPV);
                    //Funtion to check the life and change the dead state consquently ;
                    this.checkPVCharacter();
                    this.checkPECharacter();
                }
                else
                {
                    System.out.println("Escape : "+difference+". The attempt to escape from the fight is successful!\n"+this.getNom()+" goes away.");
                    goneAway=true;
                    //Moving Function to go away ;
                    escapeFrom(character);
                }
            }
        }
        //If the character hasn't enough energy ;
        else
        {
            System.out.println(this.getNom()+" has not enough energy to try to escape. He is stonned.");
        }
        System.out.println("\nScoring of the step :\n"
                        + this.getNom()+" : "+this.getpVie()+"/"+this.getpVieMax()+" PV  & "+this.getpEnergie()+"/"+this.getpEnergieMax()+" PE ;\n");
        return(goneAway);
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
        t_team.setType(Troll.class);
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
