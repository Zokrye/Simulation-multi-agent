/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetconceptobjet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author ISEN
 */
public abstract class Character {
    
    /*Attributs*/
    protected String nom;
    protected int pEnergie ;
    protected int pEnergieMax ;
    protected int pVie ;
    protected int pVieMax ;
    protected int xp ;
    protected boolean etatFatigue;
    protected int turnRanking;
    protected boolean dead;
    protected int[] lastDirection;
    protected int strenghtPoints;
    protected int defensivePoints;
    //protected int niveau;
    //protected int nbPasMaxTour;
    //protected int nbPasTour;
    private static int nbCharactersInGame;
    
    protected Cell currentCell;
    protected Direction safeZoneDirection;
    protected int maxMovement;
    
    public Character(int pEnergie,int pEnergieMax,int pVie,int pVieMax,int strenght, int defense)
    {
        this.pEnergie=pEnergie;
        this.pEnergieMax=pEnergieMax;
        this.pVie=pVie;
        this.pVieMax=pVieMax;
        this.strenghtPoints=strenght;
        this.defensivePoints=defense;
        this.xp=0;
        this.etatFatigue=false;
        this.dead=false;
    }
    
    /*Methods*/
    
    public void seDeplacer() {
        int remainingCells = RandomElement.randomThrow(maxMovement+1, 1);
        
        Cell nextCell=null;
        Direction chosenDirection=null;
        
        if(this.pEnergie/this.pEnergieMax<=0.2) {
            //Use the default direction to join Safezone
            Direction d1=safeZoneDirection;
            //The 2nd best options for joining the safeZone
            Direction d2=new Direction(safeZoneDirection.getX(),0);
            Direction d3=new Direction(0,safeZoneDirection.getY());
            //The 3rd best options for joining the safezone
            Direction d4=new Direction(safeZoneDirection.getX(),-safeZoneDirection.getY());
            Direction d5=new Direction(-safeZoneDirection.getX(),safeZoneDirection.getY());
            //Last options and less interesting directions to join SafeZone
            Direction d6=new Direction(-safeZoneDirection.getX(),0);
            Direction d7=new Direction(0,-safeZoneDirection.getY());
            
            Direction d8=new Direction(-safeZoneDirection.getX(),-safeZoneDirection.getY());

            List<Direction> directions = new ArrayList<> (Arrays.asList(d1,d2,d3,d4,d5,d6,d7,d8));
            //Get the first available cell in the list
            for(Direction direction : directions) {
                nextCell=direction.applyFrom(currentCell);
                //If direction leads to an existing cell
                if(nextCell!=null) {
                    //If the cell has no obstacles
                    if(nextCell.hasObstacle) {
                        //Save the available direction                   
                        chosenDirection=direction;
                        break;
                    }
                }

            }
        }
        //Déplacment random quand les PE sont suffisants
        else {
            List<Direction> directions=currentCell.getAvailableDirections();
            if(directions.size()>0) {
                int randomIndex=RandomElement.randomThrow(directions.size(), 0);
                chosenDirection=directions.get(randomIndex);
            }
        }
        if(chosenDirection!=null) {
            while(remainingCells>0 && pEnergie>0 && chosenDirection.applyFrom(currentCell)!=null) {
                    nextCell=chosenDirection.applyFrom(currentCell);
                    if(!nextCell.hasObstacle ) {
                        if(nextCell.character==null) {
                            moveTo(nextCell);
                            doCalculationPV(1);
                            if(isInSafeZone()){
                                if(this.pEnergie<=pEnergieMax-3) {
                                    pEnergie+=3;
                                }
                                else {
                                    pEnergie=pEnergieMax;
                                }
                            }
                            else {
                                pEnergie--;
                            }
                            remainingCells--;
                        }
                        //Meet another character
                        else {
                            Character otherCharacter = nextCell.getCharacter();
                            meet(otherCharacter, remainingCells);
                        }
                    }
                    //Reset remaining cells to 0 in case the character hits an obstacle
                    else {
                        remainingCells=0;
                    }
            }     

        }
        
        
    }
    
    /**
     * Move the character to the next cell
     * @param cell 
     */
    public void moveTo(Cell cell) {
        currentCell.setCharacter(null);
        currentCell=cell;
        currentCell.setCharacter(this);
    }
    
    public void escapeFrom(Character character) {
        Cell characterCell=character.getCurrentCell();
        Direction directionToEscape=new Direction(characterCell.getX()-currentCell.x,characterCell.getY()-currentCell.y);
        //Opposite direction of the character, best option to escape him
        Direction bestEscape=new Direction(-directionToEscape.getX(),-directionToEscape.getY());
        //The 2nd best options for escaping the character
        Direction escape2=new Direction(bestEscape.getX(),0);
        Direction escape3=new Direction(0,bestEscape.getY());
        //The 3rd best options for escaping the character
        Direction escape4=new Direction(bestEscape.getX(),-bestEscape.getY());
        Direction escape5=new Direction(-bestEscape.getX(),bestEscape.getY());
        //Last options and less interesting directions to escape the character
        Direction escape6=new Direction(-bestEscape.getX(),0);
        Direction escape7=new Direction(0,-bestEscape.getY());
        
        List<Direction> directions = new ArrayList<> (Arrays.asList(bestEscape,escape2,escape3,escape4,escape5,escape6,escape7));
        //Get the first available cell in the list
        for(Direction direction : directions) {
            Cell nextCell=direction.applyFrom(currentCell);
            //If direction leads to an existing cell
            if(nextCell!=null) {
                //If the cell has no obstacles and characters
                if(!nextCell.hasObstacle && !nextCell.hasCharacter()) {
                    //Move to the free cell                
                    moveTo(nextCell);
                    break;
                }
            }
        }
        
    }
    
    public abstract void meet(Character otherCharacter, int remainingCells);
    public abstract void attack(Character target);
    public abstract void tryToEscape(Character character);
    /**
     * 
     * @return Whether the character is in its own Safezone or not
     */
    public abstract boolean isInSafeZone();
    
    /**
     * 
     * @param character
     * @return Whether the current and specefied characters are in the same team or not
     */
    public abstract boolean isSameSide(Character character);
    
    /**
     * 
     * @param character
     * @return Whether the current and specefied characters belong to the same race or not
     */
    public abstract boolean isSameRace(Character character);
    
    
    /**
     * Actions of the character during its gaming period ;
     */
    public void characterTurn()
    {
        /*
        Test of the free cells around him ;
        Avoid obstacles but search an enemy ;
        */
        /*
        If there is no enemy and its PE are ok, go forward to search enemies ;
        If there is an enemy and its PE and PV are ok, go to attack him ;
        If there is an enemy and its PE or PV are low, return to the safe zone and try to get them again ;
        If there is no enemy and its PE are low, return to the safe zone to get them again ;
        */
        /*
        End of the turn ;
        */
    }
    
    /**
     * Add Life points to the character
     * @param pvAdded 
     */
    public void addPV(int pvAdded) {
        if(pVie+pvAdded<pVieMax) {
            pVie+=pvAdded;
        }
        else {
            pVie=pVieMax;
        }
    }
    
    /**
     * Remove Life points from the character
     * @param pvRemoved 
     */
    public void removePV(int pvRemoved) {
        if(pVie-pvRemoved>0) {
            pVie-=pvRemoved;
        }
        else {
            pVie=0;
        }
    }
    
    /**
     * Add stamina to the character
     * @param peAdded 
     */
    public void addPE(int peAdded) {
        if(pEnergie+peAdded<pEnergieMax) {
            this.pEnergie+=peAdded;
        }
        else {
            pEnergie=pEnergieMax;
        }
    }
    
    /**
     * Remove stamina from the character
     * @param peRemoved 
     */
    public void removePE(int peRemoved) {
        if(pEnergie-peRemoved>0) {
            this.pEnergie-=peRemoved;
        }
        else {
            pEnergie=0;
        }
    }
    
    /*
    Getters
    */
    public String getNom() {
        return nom;
    }
    
    public int getpEnergie() {
        return pEnergie;
    }

    public int getpEnergieMax() {
        return pEnergieMax;
    }
    
    public int getpVie() {
        return pVie;
    }
    
    public int getpVieMax() {
        return pVieMax;
    }
    
    public int getXp() {
        return xp;
    }
    
    public boolean isEtatFatigue() {
        return etatFatigue;
    }
    
    public int[] getLastDirection() {
        return lastDirection;
    }
    
    public static int getNbCharactersInGame() {
        return nbCharactersInGame;
    }

    public int getTurnRanking() {
        return turnRanking;
    }

    public boolean isDead() {
        return dead;
    }

    public int getStrenghtPoints() {
        return strenghtPoints;
    }

    public int getDefensivePoints() {
        return defensivePoints;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public Direction getSafeZoneDirection() {
        return safeZoneDirection;
    }

    public int getMaxMovement() {
        return maxMovement;
    }

    
     
    
    
    /*
    Setters
    */
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setpEnergie(int pEnergie) {
        this.pEnergie = pEnergie;
    }

    public void setpEnergieMax(int pEnergieMax) {
        this.pEnergieMax = pEnergieMax;
    }

    public void setpVie(int pVie) {
        this.pVie = pVie;
    }

    public void setpVieMax(int pVieMax) {
        this.pVieMax = pVieMax;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void setEtatFatigue(boolean etatFatigue) {
        this.etatFatigue = etatFatigue;
    }
    
    public void setLastDirection(int[] lastDirection) {
        this.lastDirection = lastDirection;
    }

    public static void setNbCharactersInGame(int nbCharactersInGame) {
        Character.nbCharactersInGame = nbCharactersInGame;
    }
    
    public void setTurnRanking(int turnRanking) {
        this.turnRanking = turnRanking;
    }
    
    public void setDead(boolean dead) {
        this.dead = dead;
    }
    
    public void setStrenghtPoints(int strenghtPoints) {
        this.strenghtPoints = strenghtPoints;
    }
    
    public void setMaxMovement(int maxMovement) {
        this.maxMovement = maxMovement;
    }
    
    public void setSafeZoneDirection(Direction safeZoneDirection) {
        this.safeZoneDirection = safeZoneDirection;
    }
    
    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }
    
    public void setDefensivePoints(int defensivePoints) {
        this.defensivePoints = defensivePoints;
    }
    
    
    /*
    Methods
    */
    
    /**
     * 
     * 
     * ATTENTION : TESTER SI LES PERSOS SONT TOUJOURS COTE A COTE DANS LE WHILE :
     * Déplacement dans la fonction escape doit faire sortir du combat.
     * 
     * 
     * Function to do a fight between two characters opposed ;
     * @param target : Character who is the opponent of the character that have
     * launched the function ;
     */
    public void fight(Character target)
    {
        //Variable of the limit value of energie to get to act in fight ;
        int limitTurnChoiceEnergie=0;
        //Count of turns ;
        int count=1;
        
        System.out.println("========================================================================\n"
                + "=========================FIGHT=====================\n");
        /*
        The character who has met the other (the one who was moving) attacks first
        and then, his opponent chose between escaping or attacking ;
        After the first turn, the characters attack while they have enough life and energy;
        When they lack of one of them, the chose randomly to attack or escape from the fight;
        Tests if the characters are still side to side.
        */
        while((target.isDead()!=true
                && this.isDead()!=true)
                && target.isEtatFatigue()!=true
                && this.isEtatFatigue()!=true
                && target.getpEnergie()>=limitTurnChoiceEnergie
                && this.getpEnergie()>=limitTurnChoiceEnergie )
        {
            /*
            First turn of the attacking character ;
            */
            if(count==1)
            {
                System.out.println(this.getNom()+" engage his opponent! The fight begins!\n"
                        + "*************Turn n°"+count+"***************");
                System.out.println("\n-----------"+this.getNom()+"------------");
                //Attack the character targetted ;
                this.attack(target);
            }
            /*
            Turn with enough PVs and PEs for the attacking character ;
            */
            else if (count!=1
                    && this.getpVie()/this.getpVieMax()>=0.2 
                    && this.getpEnergie()/this.getpEnergieMax()>=0.2)
            {
                System.out.println("*************Turn n°"+count+"***************");
                System.out.println("\n-----------"+this.getNom()+"------------");
                //Attack the character targetted ;
                this.attack(target);
            }
            /*
            Turn with lack of PVs and PEs for the attacking character;
            */
            else if (count!=1
                    && (this.getpVie()/this.getpVieMax()<0.2 
                    || this.getpEnergie()/this.getpEnergieMax()<0.2)
                    && this.isDead()==false)
            {
                System.out.println("*************Turn n°"+count+"***************");
                System.out.println("\n-----------"+this.getNom()+"------------");
                //Throws a random number to chose between escaping or fighting ;
                int random_1=RandomElement.randomThrow(2, 0);
                System.out.println("Random decision for "+this.getNom()+" : "+random_1);
                //Check the PEs of the character ;
                this.checkPECharacter();
                switch(random_1)
                {
                    case 0:
                        this.attack(target);
                        break;
                    
                    case 1:
                        this.tryToEscape(target);
                }
            }
            System.out.println("\n\n-----------"+target.getNom()+"------------");
            /*
            Turn with enough PVs and PEs for the target one ;
            */
            if ( target.getpVie()/target.getpVieMax()>=0.2 
                    && target.getpEnergie()/target.getpEnergieMax()>=0.2)
            {
                target.attack(this);
            }
            /*
            Turn with lack of PVs and PEs for the attacking character;
            */
            else if ( (target.getpVie()/target.getpVieMax()<0.2 
                    || target.getpEnergie()/target.getpEnergieMax()<0.2)
                    && target.isDead()==false)
            {
                //Throws a random number to chose between escaping or fighting ;
                int random_2=RandomElement.randomThrow(2, 0);
                System.out.println("Random decision for "+target.getNom()+" : "+random_2);
                //Check the PEs of the character ;
                target.checkPECharacter();
                switch(random_2)
                {
                    case 0:
                        target.attack(this);
                        break;
                    
                    case 1:
                        target.tryToEscape(target);
                }
            }
            System.out.println("************************************\n\n");
            count++;
        }
        /*
        Test of each breaking situation : death or tiredness for each character ;
        If the character is tired and alive, the opponent kill him with attacking.
        */
        if(this.isDead()==true)
        {
            System.out.println(this.getNom()+" died during the fight. Make him rest in peace.");
        }
        else if(target.isDead()==true)
        {
            System.out.println(this.getNom()+" destroyed "+target.getNom()+" during this fight. Glory for the winner.");
        }
        else if(this.isEtatFatigue()==true)
        {
            System.out.println(this.getNom()+" is out of breath."+target.getNom()+" decides to kill him to close the fight. Glory for the winner.");
            target.attack(this);
        }
        else if(target.isEtatFatigue()==true)
        {
            System.out.println(target.getNom()+" is out of breath."+this.getNom()+" decides to kill him to close the fight. Glory for the winner.");
            this.attack(target);
        }
        /*
        Check the state of each character ;
        */
        target.checkPECharacter();
        target.checkPVCharacter();
        this.checkPECharacter();
        this.checkPVCharacter();
        System.out.println("===========================END OF THE FIGHT===============================\n"
                + "=======================================================================");
    }
    
    
    /**
     * Function to check the remaining points of energie of the character
     * and to set the variable to the right way ;
     */
    public void checkPECharacter()
    {
        /*
        Check if the character has always some energie to continue to act in the simulation ;
        If it is 0 or less, the character is tired ;
        If it is 20% or less of energie but it remain some of it,
        it is urgent to return to the safezone.
        Else, nothing to signal ;
        */
        if(this.getpEnergie()<=0)
        {
            /*
            Set all variables that indicate the character is tired ;
            */
            this.setEtatFatigue(true);
            this.setpEnergie(0);
            System.out.println(this.getNom()+" is tired.");
        }
        else if (this.getpEnergie()/this.getpEnergieMax()<=0.2 && this.getpEnergie()>0)
        {
            //this.setEtatFatigue(false);
            //Informs of the critical state of the energie of the character ;
            System.out.println(this.getNom()+" is losing his breathe.");
        }
        else
        {
            //this.setEtatFatigue(false);
            //Informs that it is all right ;
            System.out.println(this.getNom()+" is dynamic.");
        }
    }
    
    /**
     * Function to test and set correctly PVs of the characters in fight
     * or in any situation where characters can lose life ;
     */
    public void checkPVCharacter()
    {
        /*
        Check if character still has PVs ;
        */
        if(this.getpVie()<=0)
        {
            //Setting the dead boolean to indicate that the character is dead ;
            this.setDead(true);
            //Setting of the PVs to 0 to normalize it ;
            this.setpVie(0);
            //Setting the Energie to 0 to avoid any action by a dead character ;
            this.setpEnergie(0);
            //Informatrion of the user ;
            System.out.println(this.getNom()+" is dead.");
        }
        else
        {
            //No changes the character is still alive ;
            System.out.println(this.getNom()+" is still alive.");
        }
    }
    /**
     * Function that allows to calculate and set the final number of PEs of a character ;
     * @param value : Value of the PE to add or put away ;
     */
    public void doCalculationPE(int value)
    {
        System.out.println("PEs of "+nom+" were of "+pEnergie+"/"+pEnergieMax+" PE.");       
        //Checks the value after calculation
        if(pEnergie+value>=pEnergieMax) {
            pEnergie=pEnergieMax;
        }
        else if(pEnergie+value<0) {
            pEnergie=0;
        }
        else {
            pEnergie+=value;
            this.etatFatigue=true;
        }
        System.out.println("They are now of "+pEnergie+"/"+pEnergieMax+" PE.");
        
        
    }
    
    /**
     * Function that allows to calculate and set the final number of PVs of a character ;
     * @param value : Value of the PE to add or put away ;
     */
    public void doCalculationPV(int value)
    {
        System.out.println("PVs of "+nom+" were of "+pVie+"/"+pVieMax+" PV.");
        if(pVie+value>=pVieMax) {
            pVie=pVieMax;
        }
        else if(pVie+value<0) {
            pVie=0;
            setDead(true);
        }
        else {
            pVie+=value;
        }
        System.out.println("They are now of "+pVie+"/"+pVieMax+" PV.");
    }
}
