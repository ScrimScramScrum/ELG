package springmvc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import springmvc.service.ResembleGameService;

public class ResembleGame implements Serializable{
    private int gameId; 
    private ArrayList<Integer> taskNumbers; 
    private int currentTask; 

    public ResembleGame(ArrayList<Integer> taskNumbers) {
        this.taskNumbers = taskNumbers;
        this.currentTask = taskNumbers.get(0);
    }
    
    public ArrayList<Integer> getTaskNumbers() {
        return taskNumbers;
    }

    public void setTaskNumbers(ArrayList<Integer> taskNumbers) {
        this.taskNumbers = taskNumbers;
    }

    public int getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(int currentTask) {
        this.currentTask = currentTask;
    }
    
    public int getNextTask(){
        return isCurrentTaskLast() ? -1 : taskNumbers.get(taskNumbers.indexOf(currentTask)+1);
    }
   
    public boolean isCurrentTaskLast(){
        return taskNumbers.indexOf(currentTask)==taskNumbers.size();
    }
    
}
