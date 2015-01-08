/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springmvc.domain;

/**
 *
 * @author eiriksandberg
 */
public class Exercise {
    private String[] alternatives = new String[4];
    private String solution;
    private String taskText;
    
    public Exercise(){};
    
    public Exercise(String[] alternatives, String solution, String taskText){
        this.alternatives = alternatives;
        this.solution = solution;
        this.taskText = taskText;
    }
    
    public boolean checkAnswer(String chosen){
        if (chosen.equals(solution)){
            return true;
        }
        return false;
    }

    public String[] getAlternatives() {
        return alternatives;
    }
    
    public String getAlternativeIndex(int index) {
        return this.alternatives[index];
    }

    public String getSolution() {
        return solution;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setAlternatives(String[] alternatives) {
        this.alternatives = alternatives;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }
    
    
    
}
