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
    
    public boolean registrateNewClassId(AddNewClassId addNewClassId){
                
        if(classRepoDB.registerNewClassId(addNewClassId)){
            System.out.println("Registered new Class in DB OK");            
            return true;
        } else {
            System.out.println("Error in register class in DB");
            return false;
        }
           
    }
    
    
    
}
