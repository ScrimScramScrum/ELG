/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springmvc.service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import springmvc.domain.Exercise;
import springmvc.domain.MultiChoice;
import springmvc.repository.MultiChoiceRepository;


public class MultiChoiceService {
    
    private MultiChoiceRepository repo;
    
    @Autowired
    public void setRepository(MultiChoiceRepository repo){
        this.repo=repo;
    }
    
    public MultiChoice getMultiChoice(String name){
        MultiChoice m = repo.getMultiChoice(name); // returnerer et game = null.
        
        if (m == null){
            return m;
        } else {
            ArrayList<Exercise> exercises = m.getExercises();
            for(Exercise e : exercises) {
                String[] a = e.getAlternatives();
                String[] temp = new String[a.length];
                for(int i = 0; i < a.length; i++) {
                    temp[i] = a[i].replace("\"", "\'");
                }
                e.setAlternatives(temp);
            }
            return m;
        }
    }
    
    public boolean regMultiChoiceGame(MultiChoice game){
        return repo.regMultiChoiceGame(game);
    }
  
}
