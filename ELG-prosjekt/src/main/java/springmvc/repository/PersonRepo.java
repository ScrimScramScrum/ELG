package springmvc.repository;

import java.util.ArrayList;
import java.util.List;
import springmvc.domain.Person;

public interface PersonRepo {
    public Person getPerson(String personNr);
    public boolean registerPerson(Person p) ;
    public boolean updatePerson(Person p) ;
    public boolean insert(ArrayList<String> list);;
}
