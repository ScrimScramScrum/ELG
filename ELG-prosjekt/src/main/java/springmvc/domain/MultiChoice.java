/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springmvc.domain;

import java.util.ArrayList;

/**
 *
 * @author eiriksandberg
 */
public class MultiChoice {
    private ArrayList<Exercise> oppgaver = new ArrayList<>();
    String name;
    
    public MultiChoice(){};
    
    public MultiChoice(ArrayList<Exercise> oppgaver, String name){
        this.oppgaver = oppgaver;
        this.name = name;
    }
    
    public boolean leggTilOppgave(Exercise opg){
        oppgaver.add(opg);
        return true;
    }
    
    public String getName(){
        return name;
    }
    
}
