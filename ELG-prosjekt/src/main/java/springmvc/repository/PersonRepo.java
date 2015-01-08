package springmvc.repository;

import java.util.List;
import springmvc.domain.Person;

public interface PersonRepo {
     
    public Person getPerson(String personNr);

    //public List<Person> getAllPersonsInAClass() ;

    public boolean registerPerson(Person p) ;

    public boolean updatePerson(Person p) ;

    //public boolean deletePerson(Person p) ;
}
