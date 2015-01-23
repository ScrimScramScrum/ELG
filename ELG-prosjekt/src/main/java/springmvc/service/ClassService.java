package springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import springmvc.repository.ClassRepoDB;
import springmvc.ui.AddNewClassId;

public class ClassService {
    
    @Autowired
    private ClassRepoDB classRepoDB;  
    
    public AddNewClassId getClassId(String classId){
        return classRepoDB.getClassId(classId);        
    }
    
    public boolean registrateNewClassId(String addNewClassId){
        if(classRepoDB.registerNewClassId(addNewClassId)){           
            return true;
        } else {
            return false;
        }
    } 
    
    public boolean setStudentToAClass(String emailStudent, String theClass ){        
        if (classRepoDB.registerStudentIntoAClass(emailStudent, theClass)){
            return true;
        } else {
            return false; 
        }
    }
    
    public boolean updateStudentToAClass(String emailStudent, String theClass ){        
        if (classRepoDB.updateStudentInClass(emailStudent, theClass)){
            return true;
        } else {
            return false; 
        }
    }   
}