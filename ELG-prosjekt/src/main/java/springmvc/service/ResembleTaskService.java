/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import springmvc.domain.ResembleTask;
import springmvc.repository.ResembleTaskRepo;

public class ResembleTaskService {  
    private ResembleTaskRepo repo; 
    
    @Autowired
    public void setResembleTaskRepo(ResembleTaskRepo repo){
        System.out.println("Autowired resembletaskrepo: ");
        this.repo = repo; 
    }
    
    public ResembleTask getResembleTask(int taskNumber){
        return repo.getResembleTask(taskNumber);
    }
}
