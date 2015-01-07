/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springmvc.service;
import springmvc.domain.Person;
import springmvc.service.*;
import springmvc.domain.Login;
/**
 *
 * @author Hoxmark
 */
public class LoginService {
    
    
    public boolean compareInformation(Login login){
        PersonService personService = new PersonServiceTesting(); // TODO: Endre til database.. 
        Person person = getPersonFromDB(login.getEmail());
        String hashPw = personService.hash(login.getPassword()); 
        
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
    
    private Person getPersonFromDB(String email){  
        PersonService personService = new PersonServiceTesting();
        
        return personService.getPerson(email);
    }
}
