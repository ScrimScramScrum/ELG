/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springmvc.ui;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class AddClassId {
    @NotNull
    @Size(min=5)
    String classId;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }
    
    
    
}
