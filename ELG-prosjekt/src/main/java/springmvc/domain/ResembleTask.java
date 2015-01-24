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
    private String taskText;
    private String solutionHTML; 
    private String solutionCSS; 
    private String startingHTML; 
    private String startingCSS; 
    private int width; 
    private int height; 
    
    public ResembleTask() {
        
    }
    
    public ResembleTask(int taskNumber, String taskText, String solutionHTML, String solutionCSS, String startingHTML, String startingCSS,  int width, int height) {
        this.taskNumber = taskNumber;
        this.taskText = taskText;
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

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        if(width > 200) this.width = 200; 
        else this.width = width;
    }

    public int getHeight() {
        return this.height;
    }
    
    public void setHeight(int height) {
        if(height > 150) this.height = 150;
        else this.height = height;
    }
    
    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }
}
