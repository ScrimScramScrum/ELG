package springmvc.service;

import java.util.List;
import springmvc.domain.Person;

public interface PersonService{
    public Person getPerson(String email);
    public boolean registrerPerson(Person p);
    public String hash(String password);

    
    public int generateNewPassword(Person p);

    
    public boolean changePassword(Person p, String oldPw, String newPw, String confirm); 
    public boolean setClassId(Person s, String addClass);
    public boolean makeAdmin(Person p, String password); 
}
