/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_concept_objet_simu_multi_agents;

import java.util.ArrayList;

/**
 *
 * @author ISEN
 */
public abstract class Team {
    
    private ArrayList<Character> listCharacters;
    private int lifePointTeam;
    private int totalLifePointTeam;
    private int xpTeam;
    private int totalCharacterTeam;
    private int energyPointTeam;
    private int totalEnergyPoint;

    
    /*Getters*/
    public ArrayList<Character> getListCharacters() {
        return listCharacters;
    }
    
    public int getLifePointTeam() {
        return lifePointTeam;
    }
    
    public int getTotalLifePointTeam() {
        return totalLifePointTeam;
    }

    public int getXpTeam() {
        return xpTeam;
    }
    
    public int getTotalCharacterTeam() {
        return totalCharacterTeam;
    }
    
    public int getTotalEnergyPoint() {
        return totalEnergyPoint;
    }

    public int getEnergyPointTeam() {
        return energyPointTeam;
    }
        
    
    
    /*Setters*/
    public void setListCharacters(ArrayList<Character> listCharacters) {
        this.listCharacters = listCharacters;
    }

    public void setLifePointTeam(int lifePointTeam) {
        this.lifePointTeam = lifePointTeam;
    }

    public void setTotalLifePointTeam(int totalLifePointTeam) {
        this.totalLifePointTeam = totalLifePointTeam;
    }

    public void setXpTeam(int xpTeam) {
        this.xpTeam = xpTeam;
    }

    public void setTotalCharacterTeam(int totalCharacterTeam) {
        this.totalCharacterTeam = totalCharacterTeam;
    }

    public void setTotalEnergyPoint(int totalEnergyPoint) {
        this.totalEnergyPoint = totalEnergyPoint;
    }
    
    public void setEnergyPointTeam(int energyPointTeam) {
        this.energyPointTeam = energyPointTeam;
    }
    
     
    
}
