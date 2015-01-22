package springmvc.repository;

import java.util.ArrayList;
import java.util.List;
import springmvc.domain.Person;


public class PersonRepoMock implements PersonRepo{
    
    @Override
    public Person getPerson(String email){
    
        if (email.equals("test@gmail.com")){
            Person dummy = new Person("test@gmail.com", "Lars", "Garberg"); 
            String passord = "testpassord"; 
            passord = "3fc7d21958618ef7f272a86056e016ca7f3daa0c65f4f914914314d33573524a1f0b1d73c402941e8563a6aca86f1d837116b2f0339ee1d8acb819facb5ad868";
            dummy.setHashedPassword(passord);
            
            return dummy; 
        }
        return null;    
     
    }

    
    @Override
    public boolean registerPerson(Person p){
        
        //registrerer til DB
        
        
        return true;
    }
 
    @Override
    public boolean updatePerson(Person p){
        //oppdaterer i DBen
        
        return true;
        
    }

    @Override
    public boolean insert(ArrayList<String> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
