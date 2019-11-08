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
    
    /*Attributes*/
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
    protected static int nbCharactersInGame;
    protected Cell currentCell;
    protected Direction safeZoneDirection;
    protected int maxMovement;
    
    
    //Constructor
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
    
    
    
    /*Methods*/
    
    /**
     * Function that allows the character to move and react following
     * what he meet on his way ;
     */
    public void seDeplacer() {
        int remainingCells = RandomElement.randomThrow(maxMovement+1, 1);
        
        Cell nextCell=null;
        Direction chosenDirection=null;
        if(!this.isEtatFatigue()) {
            if((double)this.pEnergie/(double)this.pEnergieMax<=0.2) {
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
                        if(!nextCell.hasObstacle) {
                            //Save the available direction                   
                            chosenDirection=direction;
                            break;
                        }
                    }

                }
            }
            //Movement when enough PEs
            else {
                List<Direction> directions=currentCell.getAvailableDirections();
                //If the character has more than 20% of life, aggressive behaviour
                if((double)this.pVie/(double)this.pVieMax>0.2) {
                    for(Direction direction : directions){
                        //If there is a nearby enemy character, move in his direction
                        Character otherCharacter=direction.applyFrom(currentCell).character;
                        if(otherCharacter!=null) {
                            if(!otherCharacter.isSameSide(this) && !otherCharacter.isInSafeZone()) {
                                chosenDirection=direction;
                                break;
                            }
                        }
                    }
                }
                //If not enough life or no nearby ennemy, random move
                if(chosenDirection==null && directions.size()>0) {
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
                                    doCalculationPE(pEnergieMax);
                                }
                                else {
                                    doCalculationPE(-1);
                                }
                                remainingCells--;
                            }
                            //Meet another character
                            else {
                                Character otherCharacter = nextCell.getCharacter();
                                meet(otherCharacter, remainingCells);
                                remainingCells=0;
                            }
                            
                        }
                        //Reset remaining cells to 0 in case the character hits an obstacle
                        else {
                            remainingCells=0;
                        }
                }     

            }
        }
        //Give 1 PE bakc if the character is tired
        else {
            this.doCalculationPE(1);
        }
        
        
    }
    
    
    /**
     * Move the character to the specified cell
     * @param cell 
     */
    public void moveTo(Cell cell) {
        currentCell.setCharacter(null);
        currentCell=cell;
        currentCell.setCharacter(this);
    }
    
    /**
     * Move away from a character in the best direction available
     * @param character 
     */
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
                    doCalculationPE(-1);
                    break;
                }
            }
        }
        
    }
    
    /**
     * Called a character tries to move to the cell of another one
     * @param otherCharacter character met
     * @param remainingCells number of remaining movement points when meet the character
     */
    public abstract void meet(Character otherCharacter, int remainingCells);
    
    /**
     * Attack another character
     * @param target of the attack
     */
    public abstract void attack(Character target);
    
    /**
     * Try to escape a character
     * @param character to escape
     * @return 
     */
    public abstract boolean tryToEscape(Character character);
    
    /**
     * Check if the character is in the SafeZone ;
     * @return Whether the character is in its own Safezone or not
     */
    public abstract boolean isInSafeZone();
    
    /**
     * Test if two characters are in the same team : Hero or Enemy ;
     * @param character
     * @return Whether the current and specefied characters are in the same team or not
     */
    public abstract boolean isSameSide(Character character);
    
    /**
     * Test the type of two characters ;
     * @param character
     * @return Whether the current and specefied characters belong to the same race or not
     */
    public abstract boolean isSameRace(Character character);
    
    /**
     * Kill the current character and remove him from the map
     */
    public void kill() {
        this.pVie=0;
        this.pEnergie=0;
        this.dead = true;
        this.removeOneCharacter();
        this.currentCell.setCharacter(null);
    }
    
    /**
     * Update the number of characters in the game/faction/race/class when one of them dies
     */
    public abstract void removeOneCharacter();
    
    
    /**
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
        boolean goneAway_1=false;
        boolean goneAway_2=false;
        
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
                && this.getpEnergie()>=limitTurnChoiceEnergie
                && goneAway_1==false
                && goneAway_2==false)
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
                        goneAway_1=this.tryToEscape(target);
                        break;
                }
            }
            System.out.println("\n\n-----------"+target.getNom()+"------------");
            /*
            Turn with enough PVs and PEs for the target one ;
            */
            if ( target.getpVie()/target.getpVieMax()>=0.2 
                    && target.getpEnergie()/target.getpEnergieMax()>=0.2
                    && this.isDead()!=true
                    && goneAway_1!=true)
            {
                target.attack(this);
            }
            /*
            Turn with lack of PVs and PEs for the attacking character;
            */
            else if ( (target.getpVie()/target.getpVieMax()<0.2 
                    || target.getpEnergie()/target.getpEnergieMax()<0.2)
                    && target.isDead()==false
                    && this.isDead()!=true
                    && goneAway_1!=true)
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
                        goneAway_2=this.tryToEscape(target);
                        break;
                }
            }
            System.out.println("************************************\n\n");
            count++;
        }
        /*
        Test of each breaking situation : death, escape or tiredness for each character ;
        If the character is tired and alive, the opponent kill him attacking,
        If he escaped he is safe from is opponent.
        */
        if(this.isDead()==true)
        {
            System.out.println(this.getNom()+" died during the fight. May he rest in peace.");
            target.winXP(this);
        }
        else if(target.isDead()==true)
        {
            System.out.println(this.getNom()+" destroyed "+target.getNom()+" during this fight. Glory to the winner.");
            this.winXP(target);
        }
        else if(this.isEtatFatigue()==true && goneAway_1!=true)
        {
            System.out.println(this.getNom()+" is out of breath."+target.getNom()+" decides to kill him to end the fight. Glory to the winner.");
            target.attack(this);
            target.winXP(this);
        }
        else if(target.isEtatFatigue()==true && goneAway_2!=true)
        {
            System.out.println(target.getNom()+" is out of breath."+this.getNom()+" decides to kill him to end the fight. Glory to the winner.");
            this.attack(target);
            this.winXP(target);
        }
        else if(goneAway_1==true && this.isDead()==false)
        {
            System.out.println(target.getNom()+" doesn't understand what happened. The fight is over. "+this.getNom()+" has escaped himself.");
        }
        else if(goneAway_2==true && target.isDead()==false)
        {
            System.out.println(this.getNom()+" doesn't understand what happened. The fight is over. "+target.getNom()+" has escaped himself.");
        }
        /*
        Check the state of each character ;
        */
        target.checkPVCharacter();
        this.checkPVCharacter();
        System.out.println("===========================END OF THE FIGHT===============================\n"
                + "=======================================================================");
    }
    
    
    /**
     * Function to check the remaining stamina of the character
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
            System.out.println(this.getNom()+" is tired.");
        }
        else if (this.getpEnergie()/this.getpEnergieMax()<=0.2 && this.getpEnergie()>0)
        {
            System.out.println(this.getNom()+" is losing his breathe.");
        }
        else
        {
            System.out.println(this.getNom()+" is dynamic.");
        }
    }
    
    /**
     * Function to check the remaining PVs of the character
     */
    public void checkPVCharacter()
    {
        /*
        Check if character still has PVs ;
        */
        if(this.getpVie()<=0)
        {
            System.out.println(this.getNom()+" is dead.");
        }
        else
        {
            System.out.println(this.getNom()+" is still alive.");
        }
    }
    
    
    /**
     * Function that allows to calculate and set the final number of PEs of a character ;
     * @param value : Value of the PE to add or remove ;
     */
    public void doCalculationPE(int value)
    {
        System.out.println("PEs of "+nom+" were "+pEnergie+"/"+pEnergieMax+" PE.");       
        //Checks the value after calculation
        if(pEnergie+value>=pEnergieMax) {
            pEnergie=pEnergieMax;
        }
        else if(pEnergie+value<=0) {
            pEnergie=0;
            this.etatFatigue=true;
        }
        else {
            pEnergie+=value;
            this.etatFatigue=false;
        }
        System.out.println("They are now "+pEnergie+"/"+pEnergieMax+" PE.");
        
        
    }
    
    
    /**
     * Function that allows to calculate and set the final number of PVs of a character ;
     * @param value : Value of the PE to add or remove ;
     */
    public void doCalculationPV(int value)
    {
        int prevPV=pVie;
        if(pVie+value>=pVieMax) {
            pVie=pVieMax;
        }
        else if(pVie+value<=0) {
            this.kill();
        }
        else {
            pVie+=value;           
        }
        int currentPV=pVie;
        if(currentPV!=prevPV)
        {
            System.out.println("PVs of "+nom+" were of "+prevPV+"/"+pVieMax+" PV.");
            System.out.println("They are now "+pVie+"/"+pVieMax+" PV.");
        }
    }
    
    
    /**
     * Function that allows to win some xp for the winning character of the fight ;
     * @param target : Target who has been killed by the character.
     */
    public void winXP(Character target)
    {
        //Tests if the target is dead ;
        if(target.isDead()==true)
        {
            //If the target is dead, the xp winnig phase can begin ;
            System.out.println("%%%%%%%%%%%%%%%%%%%%\n"
                    + "%%%%%%%XP WON%%%%%%%");
            //We get the existing xp value of the winning character ;
            int xpWinner=this.getXp();
            /*
            We test to which class the target was a part of 
            and we attribute the required value of xps;
            */
            if(target.getClass().getSimpleName().equals("Priest")
                    || target.getClass().getSimpleName().equals("Prophet")
                    || target.getClass().getSimpleName().equals("Sorcerer")
                    || target.getClass().getSimpleName().equals("Shaman"))
            {
                xpWinner+=Wizzard.XP_VALUE;
            }
            else if(target.getClass().getSimpleName().equals("Paladin")
                    || target.getClass().getSimpleName().equals("Hunter")
                    || target.getClass().getSimpleName().equals("Assassin")
                    || target.getClass().getSimpleName().equals("Berserker"))
            {
                xpWinner+=Warrior.XP_VALUE;
            }
            else if(target.getClass().getSimpleName().equals("Admiral")
                    || target.getClass().getSimpleName().equals("TribalChef")
                    || target.getClass().getSimpleName().equals("OrcAlpha")
                    || target.getClass().getSimpleName().equals("TrollPackMaster"))
            {
                xpWinner+=Mediator.XP_VALUE;
            }
            //Print some information about the victime ;
            System.out.println(target.getNom()+" was a(n) "+target.getClass().getSimpleName()+", "+this.getNom()+" wins "+xpWinner+" XP.");
            /*
            We test if the target had some xp points,
            If it is the case, the winner take all xp points from the loser ;
            */
            if(target.getXp()>0)
            {
                System.out.println(target.getNom()+" had "+target.getXp()+" XP, "+this.getNom()+" steal them from him.");
                xpWinner+=target.getXp();
                target.setXp(0);
            }
            //We set the xp points of the winner ;
            this.setXp(xpWinner);
            //We print the result of the match ;
            System.out.println("Total XP won : "+xpWinner+" XP.\n"
                    + this.getNom()+" has now "+this.getXp()+" XP.\n"
                            + "%%%%%%%%%END XP WON%%%%%%%%%%\n"
                            + "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        }
    }
    
    
}
