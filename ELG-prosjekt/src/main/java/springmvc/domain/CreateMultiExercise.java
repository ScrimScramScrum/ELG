/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springmvc.domain;

import java.io.Serializable;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author eiriksandberg
 */
public class CreateMultiExercise implements Serializable {
    @NotEmpty 
    private String alt1;
    @NotEmpty 
    private String alt2;
    @NotEmpty 
    private String alt3;
    @NotEmpty 
    private String alt4;
    @NotEmpty 
    private String answer;
    @NotEmpty 
    private String taskText;

    public CreateMultiExercise() {
    }

    public String getAlt1() {
        return alt1;
    }

    public String getAlt2() {
        return alt2;
    }

    public String getAlt3() {
        return alt3;
    }

    public String getAlt4() {
        return alt4;
    }

    public String getAnswer() {
        return answer;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setAlt1(String alt1) {
        this.alt1 = alt1;
    }

    public void setAlt2(String alt2) {
        this.alt2 = alt2;
    }

    public void setAlt3(String alt3) {
        this.alt3 = alt3;
    }

    public void setAlt4(String alt4) {
        this.alt4 = alt4;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }
    
    
    
}
