/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springmvc.service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import springmvc.domain.MultiChoice;
import springmvc.repository.MultiChoiceRepository;


public class MultiChoiceService {
    
    private MultiChoiceRepository repo;
    
    @Autowired
    public void setRepository(MultiChoiceRepository repo){
        System.out.println("MultiChoiceService.mock" + repo);
        this.repo=repo;
    }
    
    
    public MultiChoice getMultiChoice(String name){
        if (repo == null){
            System.out.println("***** hei ********");
        }
        return repo.getMultiChoice(name);
    }
    
    public boolean regMultiChoiceGame(MultiChoice game){
        return repo.regMultiChoiceGame(game);
    }
  
}
