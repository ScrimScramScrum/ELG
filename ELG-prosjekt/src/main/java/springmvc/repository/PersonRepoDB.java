package springmvc.repository;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import springmvc.domain.Person;

public class PersonRepoDB implements PersonRepo{
    
    private Connection forbindelse;
    private final String sqlSelectPerson = "Select * from person where email = ?";    
    private final String sqlInsertPerson = "insert into person values(?,?,?,?,?)";
    private final String sqlUpdatePerson = "update person set fname=?, lname = ?, hashPassword = ?, administrator = ? where email = ?";

    private DataSource dataSource;
    JdbcTemplate jdbcTemplateObject;
    
    public PersonRepoDB() {}
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    
    @Override
    public Person getPerson(String email ){   
        Person foundPerson= new Person();        
        try{
            foundPerson = (Person)jdbcTemplateObject.queryForObject(sqlSelectPerson, new Object[]{email}, new PersonMapper());
        }catch(Exception e){
            return null;
        }        
        return foundPerson;
    }
    
    public boolean updatePerson(Person person){
        jdbcTemplateObject.update(sqlUpdatePerson, 
            new Object[]{                 
                person.getFname(), 
                person.getLname(),
                person.getHashedPassword(),
                person.getTeacher(),
                person.getEmail()
        });
                
        return true;
    }
    
    public boolean registerPerson(Person person){  
        jdbcTemplateObject.update(sqlInsertPerson, 
            new Object[]{
                person.getEmail(), 
                person.getFname(), 
                person.getLname(),
                person.getHashedPassword(),
                person.getTeacher()
        });
                
        return true;
    }
    
    public boolean insert(ArrayList<String> list){
        for (int i = 0; i < list.size(); i++){
                jdbcTemplateObject.update(list.get(i), new Object[]{});
        }
        return true;
    }
}
