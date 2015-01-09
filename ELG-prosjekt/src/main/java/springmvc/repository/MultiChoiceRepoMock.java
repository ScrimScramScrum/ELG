/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springmvc.repository;

import java.util.ArrayList;
import java.util.List;
import springmvc.domain.Exercise;
import springmvc.domain.MultiChoice;
import springmvc.domain.MultiChoiceInfo;

/**
 *
 * @author eiriksandberg
 */
public class MultiChoiceRepoMock implements MultiChoiceRepository {
    
    //Spill 1
    private String[] alt = {"Alt 1", "Alt 2", "Alt 3", "Alt 4"};
    private Exercise e1 = new Exercise(alt, "Alt 1", "Oppgavetekst her (Alt 1 er riktig)");
    private Exercise e2 = new Exercise(alt, "Alt 2", "Oppgavetekst her (Alt 2 er riktig)");
    private Exercise e3 = new Exercise(alt, "Alt 3", "Oppgavetekst her (Alt 3 er riktig)");
    private Exercise e4 = new Exercise(alt, "Alt 4", "Oppgavetekst her (Alt 4 er riktig)");
    private ArrayList<Exercise> e = new ArrayList<>();
    private MultiChoice game = new MultiChoice(e, "Spill 1");
    private ArrayList<MultiChoice> games = new ArrayList<>();
    
    //Spill 2
    private String[] alternatives = {"Hei", "Hva skjer?", "Fett!", "Yeah"};
    private Exercise exer1 = new Exercise(alternatives, "Hva skjer?", "Hvem er riktig? ('Hva skjer?' er riktig)");
    private String[] alternatives2 = {"Hola", "Que pasa?", "Cerveza!", "Ay Caramba!"};
    private Exercise exer2 = new Exercise(alternatives2, "Cerveza!", "Mitt spanske favoritt ord er? ('Cerveza' er riktig)");
    private ArrayList<Exercise> exercise2 = new ArrayList<>();
    private MultiChoice game2 = new MultiChoice(exercise2, "Spill 2");
    
    public MultiChoiceRepoMock(){
        e.add(e1);
        e.add(e2);
        e.add(e3);
        e.add(e4);
        exercise2.add(exer1);
        exercise2.add(exer2);
        
        games.add(game);
        games.add(game2);
    }
    
    
    public MultiChoice getMultiChoice(String name){
        for(int i = 0; i < games.size(); i++){
            if (games.get(i).getName().equals(name)){
                return games.get(i);
            }
        }
        return null;
    
    }
    
    public ArrayList<MultiChoice> getGames(){
        return games;
    }

    @Override
    public ArrayList<MultiChoice> getAllMultiChoiceGames() {
        return games; 
    }

    @Override
    public ArrayList<MultiChoiceInfo> getAllMultiChoiceInfo() {
        return null;  
    }

    @Override
    public MultiChoiceInfo getMultiChoiceInfo(String gameId) {
        return null; 
    }
}
