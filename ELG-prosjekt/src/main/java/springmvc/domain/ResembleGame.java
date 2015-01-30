package springmvc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;


public class ResembleGame implements Serializable, Comparable<ResembleGame>{
    private int gameId; 
    private ArrayList<Integer> taskNumbers; 
    private int currentTask; 
    private double[] taskScores; 
    @NotEmpty
    private String learningGoal; 
    @NotEmpty
    private String info; 
    @Min(1)
    @Max(3)
    private int difficulty;
    @NotEmpty
    private String gamename;
    private String creatorId; 
    private int approved = -1;
    private int votes; 

    public ResembleGame(ArrayList<Integer> taskNumbers, int gameId, String learningGoal, String info, int difficulty) {
        this.gameId = gameId; 
        this.taskNumbers = taskNumbers;
        this.currentTask = taskNumbers.get(0);
        this.taskScores = new double[taskNumbers.size()];
        this.learningGoal = learningGoal; 
        this.info = info; 
        this.difficulty = difficulty; 
    }
    
    public ResembleGame(){
    }
    
    public ArrayList<Integer> getTaskNumbers() {
        return taskNumbers;
    }
    
    public int numberOfTasks(){
        return taskNumbers.size();
    }

    public void setTaskNumbers(ArrayList<Integer> taskNumbers) {
        this.taskNumbers = taskNumbers;
        this.currentTask=taskNumbers.get(0);
        this.taskScores = new double[taskNumbers.size()];
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
            if(score>100) score = 0;
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

    public String getLearningGoal() {
        return learningGoal;
    }

    public void setLearningGoal(String learningGoal) {
        this.learningGoal = learningGoal;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getGamename() {
        return gamename;
    }

    public void setGamename(String gamename) {
        this.gamename = gamename;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
    
    @Override
    public int compareTo(ResembleGame spill){
        int verdi = 0;
        if (this.getVotes() < spill.getVotes()){
            verdi = 1;
        }
        else if (this.getVotes() == spill.getVotes()){
            verdi = 0;
        }
        else{
            verdi = -1;
        }
        return verdi;
    }

}