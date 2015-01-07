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

/**
 *
 * @author eiriksandberg
 */
public class MultiChoiceRepoMock implements MultiChoiceRepository {
    private String[] alt = {"Alt 1", "Alt 2", "Alt 3", "Alt 4"};
    private Exercise e1 = new Exercise(alt, "Alt 2", "Hvem er riktig?");
    private ArrayList<Exercise> e = new ArrayList<>();
    private MultiChoice game = new MultiChoice(e, "Spill 1");
    private ArrayList<MultiChoice> games = new ArrayList<>();
    
    public MultiChoiceRepoMock(){
        e.add(e1);
        games.add(game);
    }
    
    
    public MultiChoice getMultiChoice(String name){
        for(int i = 0; i < games.size(); i++){
            if (games.get(i).getName().equals(name)){
                return games.get(i);
            }
        }
        return null;
    
    }
}
