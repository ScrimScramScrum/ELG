
package springmvc.repository;

import java.util.List;
import springmvc.domain.Person;

/**
 *
 * @author tomash
 */
public interface PersonRepository {
     
    public Person getPerson(String personNr);

    public List<Person> getAllePersoner() ;

    public boolean registrerPerson(Person p) ;

    public boolean oppdaterPerson(Person p) ;

    public boolean slettPerson(Person p) ;
}
