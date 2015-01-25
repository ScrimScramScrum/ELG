/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.domain;

/**
 *
 * @author borgarlie
 */
public class MultiChoiceInfo implements Comparable<MultiChoiceInfo> {
    private String name;
    private String learningGoal; 
    private String info;
    private int difficulty; 
    int approved = -1;
    private int votes;
    private String creatorId;
    
    public MultiChoiceInfo(String name, String learningGoal, String info, int difficulty, String creatorId) {
        this.name = name;
        this.learningGoal = learningGoal; 
        this.info = info;
        this.difficulty = difficulty; 
        this.creatorId = creatorId;
    }

    public MultiChoiceInfo(String name, String learningGoal, String info, int difficulty) {
        this.name = name;
        this.learningGoal = learningGoal; 
        this.info = info;
        this.difficulty = difficulty; 
    }
    
    public MultiChoiceInfo() {
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLearningGoal() {
        return learningGoal;
    }

    public void setLearningGoal(String learningGoal) {
        this.learningGoal = learningGoal;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
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

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }
    
    @Override
    public int compareTo(MultiChoiceInfo spill){
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
