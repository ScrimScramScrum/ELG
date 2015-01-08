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
public class MultiChoiceInfo {
    private String name;
    private String learningGoal; 
    private String info;
    private int difficulty; 

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
    
    
}
