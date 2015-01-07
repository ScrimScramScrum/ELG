/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springmvc.domain;

/**
 *
 * @author eiriksandberg
 */
public class Oppgave {
    private String[] alternativer = new String[4];
    private String fasit;
    private String oppgaveTekst;
    
    public Oppgave(String[] alternativer, String fasit, String oppgaveTekst){
        this.alternativer = alternativer;
        this.fasit = fasit;
        this.oppgaveTekst = oppgaveTekst;
    }
    
    public boolean sjekkSvar(String valgt){
        if (valgt.equals(fasit)){
            return true;
        }
        return false;
    }

    public String[] getAlternativer() {
        return alternativer;
    }

    public String getFasit() {
        return fasit;
    }

    public String getOppgaveTekst() {
        return oppgaveTekst;
    }
    
    
}
