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
    
    public MultiChoice(ArrayList<Exercise> oppgaver){
        this.oppgaver = oppgaver;
    }
    
    public boolean leggTilOppgave(Exercise opg){
        oppgaver.add(opg);
        return true;
    }
    
}
