/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import springmvc.domain.MultiChoice;
import springmvc.domain.Person;
import springmvc.repository.ResultRepo;

/**
 *
 * @author eiriksandberg
 */
public interface ResultService {
    
    private ResultRepo = repo;
    
    @Autowired
        public void setRepository(ResultRepo repo){
        System.out.println("resultRepo.mock" + repo);
        this.repo=repo;
    }
    
    public boolean regMultiChoiceRes(Person p, Double score, MultiChoice game);
}
