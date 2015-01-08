/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springmvc.service;

import java.util.List;
import springmvc.domain.Person;
        

/**
 *
 * @author Hoxmark
 */
public interface PersonService{
    
    public Person getPerson(String email);
    
    //public boolean oppdaterPerson(Person p);
    
    public boolean registrerPerson(Person p);
    
    //public boolean slettPersoner(List<Person> personListe);
    
    public String hash(String password);
    
    public boolean generateNewPassword(Person p);

    
    public boolean changePassword(Person p, String oldPw, String newPw, String confirm); 


    
}
