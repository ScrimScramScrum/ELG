/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springmvc.repository;

import java.util.ArrayList;
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
    private MultiChoice game = new MultiChoice(e);
    
    
    public MultiChoice getMultiChoice(MultiChoice game){
        return null;
    }
}
