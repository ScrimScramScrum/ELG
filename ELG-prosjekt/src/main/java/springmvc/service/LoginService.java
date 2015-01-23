package springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import springmvc.domain.Person;
import springmvc.service.*;
import springmvc.domain.Login;
import springmvc.repository.PersonRepoDB;

public class LoginService {
    
    @Autowired
    private PersonService personService;  
    
    public boolean compareInformation(Login login){         
        Person person = personService.getPerson(login.getEmail());
        String hashPw = personService.hash(login.getPassword()); 
        
        System.out.println(hashPw);
        
        if (person == null){
            return false;  
        } else if (person.getHashedPassword().equals(hashPw)){ 
            return true;             
        } else {
            return false;
        }
    }
}
