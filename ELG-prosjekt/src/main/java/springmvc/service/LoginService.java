/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springmvc.service;
import org.springframework.beans.factory.annotation.Autowired;
import springmvc.domain.Person;
import springmvc.service.*;
import springmvc.domain.Login;
import springmvc.repository.PersonRepoDB;
/**
 *
 * @author Hoxmark
 */
public class LoginService {
    
    @Autowired
    private PersonService personService;  
    
    
    
    public boolean compareInformation(Login login){
        //PersonService personService = new PersonServiceTesting(); // TODO: Endre til database..         
        Person person = personService.getPerson(login.getEmail());
        String hashPw = personService.hash(login.getPassword()); 
        
        System.out.println(hashPw);
        
        if (person == null){
            System.out.println("Login failed. login-data = null. ");
            return false; // Brukernavn finnes ikke.  
        } else if (person.getHashedPassword().equals(hashPw)){ // 
            System.out.println("Login ok. ");
            return true; // Personen finnes.             
        } else {
            return false;
        }
        
    }
    
  
}
