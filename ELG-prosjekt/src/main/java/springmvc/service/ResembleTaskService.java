/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.service;

import java.util.ArrayList;
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
        ResembleTask task =  repo.getResembleTask(taskNumber);
        task.setSolutionHTML(task.getSolutionHTML().replace("\"", "\'"));
        return task; 
    }
    
    public boolean insertResembleTask(String taskText, String solutionHTML, String solutionCSS, String startingHTML, String startingCSS,  int width, int height, int idGame){
        return this.repo.insertResembleTask(taskText, solutionHTML, solutionCSS, startingHTML, startingCSS, width, height, idGame);
    }
    
    public boolean insertResembleTasks(ArrayList<ResembleTask> resembleTasks, int gameId){
        for(ResembleTask rt : resembleTasks){
            insertResembleTask(rt.getTaskText(), rt.getSolutionHTML(), rt.getSolutionCSS(), rt.getStartingHTML(), rt.getStartingCSS(), rt.getWidth(), rt.getHeight(), gameId); 
        }
        return true; 
    }
}
