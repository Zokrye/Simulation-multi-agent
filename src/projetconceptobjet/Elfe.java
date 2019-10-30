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
    public Elfe(int pEnergie,int pEnergieMax,int pVie,int pVieMax, int strenght, int defense)
    {
        super(pEnergie,pEnergieMax,pVie,pVieMax,strenght,defense);
        this.setType(Species.Elfe);
        Elfe.weakness=Species.Troll;
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
    
    
    /**
     * AJOUTER LA FONCTION DE DEPLACEMENT POUR S'ECHAPPER ;
     * Function to try to escape from a fight ;
     * Elves don't need to pay any PEs to try to escape ;
     * Some PEs and PVs are lost if it fails.
     */
    @Override
    public void escape()
    {
        /*
        Cost of the action ;
        */
        int failingCostPE=10;
        int failingCostPV=5;
        System.out.println("ESCAPE : "+this.getNom()+" try to escape himself from the fight.");
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
                //Moving Function to go away ;
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
            this.doCalculationPE("-", failingCostPE);
            this.doCalculationPV("-", failingCostPV);
            //Check the PVs of the character to adapt his situation ;
            this.checkPVCharacter();
        }
        else
        {
            System.out.println("Escape : "+difference+". The attempt to escape from the fight is successful!\n"+this.getNom()+" goes away.");
            //Moving Function to go away ;
            //this.seDeplacer();
        }
    }
    
    //Réanime les personnages fatigués avec des PEs ;
    @Override
    public void reanimation()
    {
        System.out.println("REANIMATION!");
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
