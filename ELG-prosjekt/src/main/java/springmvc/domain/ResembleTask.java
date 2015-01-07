/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.domain;

/**
 *
 * @author Jorgen
 */
public class ResembleTask {
    private int taskNumber; 
    private String solutionHTML; 
    private String solutionCSS; 
    private String startingHTML; 
    private String startingCSS; 
    private int width; 
    private int height; 

    public ResembleTask(int taskNumber, String solutionHTML, String solutionCSS, String startingHTML, String startingCSS,  int width, int height) {
        this.taskNumber = taskNumber;
        this.solutionHTML = solutionHTML;
        this.solutionCSS = solutionCSS;
        this.startingHTML = startingHTML;
        this.startingCSS = startingCSS; 
        this.width = width; 
        this.height = height; 
        
    }
 
    public int getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String getSolutionHTML() {
        return solutionHTML;
    }

    public void setSolutionHTML(String solutionHTML) {
        this.solutionHTML = solutionHTML;
    }

    public String getSolutionCSS() {
        return solutionCSS;
    }

    public void setSolutionCSS(String solutionCSS) {
        this.solutionCSS = solutionCSS;
    }

    public String getStartingHTML() {
        return startingHTML;
    }

    public void setStartingHTML(String startingHTML) {
        this.startingHTML = startingHTML;
    }

    public String getStartingCSS() {
        return startingCSS;
    }

    public void setStartingCSS(String startingCSS) {
        this.startingCSS = startingCSS;
    }
    
    
    
}
