package springmvc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;

public class ResembleGame implements Serializable{
    private int gameId; 
    private ArrayList<Integer> taskNumbers; 
    private int currentTask; 
    private double[] taskScores; 

    public ResembleGame(ArrayList<Integer> taskNumbers, int gameId) {
        this.gameId = gameId; 
        this.taskNumbers = taskNumbers;
        this.currentTask = taskNumbers.get(0);
        this.taskScores = new double[taskNumbers.size()];
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
        return taskNumbers.indexOf(currentTask)==taskNumbers.size()-1;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
    
    public boolean setTaskNumberScore(int taskNumber, double score){
        if(this.taskNumbers.contains(taskNumber)){
            this.taskScores[this.taskNumbers.indexOf(taskNumber)] = score; 
            return true; 
        }
        return false; 
    }
    
    public double getTotalScore(){
        int sum = 0; 
        for(Double d : this.taskScores){
            sum+=d; 
        }
        return sum; 
    }

    public double[] getTaskScores() {
        return taskScores;
    }

    public void setTaskScores(double[] taskScores) {
        this.taskScores = taskScores;
    }
    
    
}