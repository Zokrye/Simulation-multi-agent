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
    
    public Human(int pEnergie,int pEnergieMax,int pVie,int pVieMax,int strenght, int defense)
    {
        super(pEnergie,pEnergieMax,pVie,pVieMax,strenght,defense);
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
    
    
    /**
     * Function that allows to engage an enemy on the map during a fight;
     * It make character lose some PVs and some PEs to do this action ;
     * For humans, this action consumes PE.
     * @param target : enemy targetted during the fight periode ;
     */
    @Override
    public void attack(Character target)
    {
        int costAtkPE=5;
        /*
        Test the value of PEs of the character and decide if he can attack or not ;
        */
        if( this.getpEnergie()>=costAtkPE)
        {
            /*
            Random calcul of the power of each attack and defense turn ;
            */
            this.doCalculationPE("-", costAtkPE);
            int atkRandomValue=RandomElement.randomThrow(this.getStrenghtPoints(),0);
            int defRandomValue=RandomElement.randomThrow(target.getDefensivePoints(),0);
            //Add of the bonus given by xp of the character ;
            double strenghtATK=atkRandomValue+atkRandomValue*(this.getXp()/1000);
            //Rounding the final attack value to get an integer ;
            int valueATK=(int)strenghtATK;
            //Printing the message to the user ;
            System.out.println(this.getNom()+" attacks with a fast sword dance with a strenght of : "+valueATK+" ;");

            //Add of the bonus given by xp of the character ;
            double defAction=defRandomValue+defRandomValue*(target.getXp()/1000);
            //Rounding the final attack value to get an integer ;
            int valueDEF=(int)defAction;
            //Printing the message to the user ;
            System.out.println(target.getNom()+" defends with a resitance of : "+valueDEF+" ;");

            //Calulation of the end of the step ;
            int result=valueDEF-valueATK;

            /*
            If the result is negative, the target is shot with damages ;
            */
            if(result<0)
            {
                int targetLife=target.getpVie();
                targetLife+=result;
                target.setpVie(targetLife);
                System.out.println("Dammages of : "+result+" are got by "+target.getNom()+" : his life is now of : "+target.getpVie()+"/"+target.getpVieMax()+" PV ;");
            }

            /*
            If it is positive, the damages are given to the attacking one ;
            */
            else if(result>0)
            {
                int persoLife=this.getpVie();
                persoLife-=result;
                this.setpVie(persoLife);
                System.out.println("Dammages of : "+result+" are got by "+this.getNom()+" : his life is now of : "+this.getpVie()+"/"+this.getpVieMax()+" PV ;");
            }

            //Printing of the result of the step ;
            System.out.println("Scoring of the step :\n"
                    + this.getNom()+" : "+this.getpVie()+"/"+this.getpVieMax()+" PV  & "+this.getpEnergie()+"/"+this.getpEnergieMax()+" PE ;\n"
                    + target.getNom()+" : "+target.getpVie()+"/"+target.getpVieMax()+" PV & "+target.getpEnergie()+"/"+target.getpEnergieMax()+" PE ;");
        }
        //The character can't attack because of his lack of PEs ;
        else
        {
            System.out.println(this.getNom()+" can't attack, he is too tired to use his sword.");
        }
    }
    
    /**
     * AJOUTER LA FONCTION DE DEPLACEMENT POUR S'ECHAPPER ET UNE FONCTION DE TEST DE PVs ;
     * Function to try to escape from a fight ;
     * Humans don't need to pay any PEs to try to escape ;
     * Some PEs and PVs are lost if it fails.
     */
    @Override
    public void escape()
    {
        /*
        Cost of the action ;
        */
        int costPEEscape=2;
        int failingCostPE=10;
        int failingCostPV=5;
        if(this.getpEnergie()>=costPEEscape)
        {
            System.out.println("ESCAPE : "+this.getNom()+" try to escape himself from the fight.");
            this.doCalculationPE("-",costPEEscape);
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
            if((randomThrown+bonusEscapeElfe)<99)
            {
                valueEscape=randomThrown+bonusEscapeElfe;
                System.out.println("Bonus is applied.");
            }
            else
            {
                valueEscape=randomThrown;
                System.out.println("Bonus is not applied.");
            }

            /*
            Test if the value is perfect and the escape can't be stopped ;
            */
            if(valueEscape==99)
            {
                    System.out.println("PERFECT! "+this.getNom()+" escapes from the fight without any problems.");
                    //Moving Function ;
                    //this.seDeplacer();
            }

            /*
            Random thrown form the enemy to keep the character in the fight ;
            */
            randomThrown=RandomElement.randomThrow(100, 0);
            difference=valueEscape-randomThrown;
            if(difference<0)
            {
                System.out.println("Escape : "+difference+". The attempt to escape from the fight has failed!\n"+this.getNom()+" lose some PEs.");
                this.doCalculationPE("-", failingCostPV);
                this.doCalculationPV("-", failingCostPE);
                //Funtion to check the life and change the dead state consquently ;
            }
            else
            {
                System.out.println("Escape : "+difference+". The attempt to escape from the fight is successful!\n"+this.getNom()+" goes away.");
                //Moving Function ;
                //this.seDeplacer();
            }
        }
        //If the character hasn't enough energy ;
        else
        {
            System.out.println(this.getNom()+" has not enough energy to try to escape. He is stonned.");
        }
    }
    
    @Override
    public boolean isSameRace(Character character) {
        return character instanceof Human;
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
