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
        Human.weakness=Species.Orc;
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
     * @return : team : ArrayList of Humans containning all characters of the team ;
     */
    public static ArrayList<Human> createHumanTeam(int nbPerso)
    {
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
            }
            else
            {
                /*
                Instantiation of a Priest, increasing of the number of instance and setting of its name ;
                */
                newPerso = new Priest();
                nbPriests++;
                newPerso.setNom("Priest_"+nbPriests);
            }
            team.add(newPerso);
        }
        //Returns the list of Human team mates;
        return(team);
    }
    
    
}
