/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springmvc.repository;

import java.sql.Connection;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import springmvc.domain.Person;




public class PersonRepoDB implements PersonRepo{
    
    private Connection forbindelse;
    //private final String sqlDeletePerson = "Delete from person where personnr = ?";
    private final String sqlSelectPerson = "Select * from person where email = ?";
    //private final String sqlSelectAllePersoner = "Select * from person";
    
    private final String sqlInsertPerson = "insert into person values(?,?,?,?,?)";
    private final String sqlUpdatePerson = "update person set fname=?, lname = ?, hashPassword = ?, classId = ? where email = ?";

    
    private DataSource dataSource;
    JdbcTemplate jdbcTemplateObject;
    
    public PersonRepoDB() {}
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        System.out.println(" Database.setDataSource " + dataSource);
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    
    @Override
    public Person getPerson(String email ){   
        System.out.println("i DBen for å getPerson"+email);
        Person foundPerson= new Person();        
        try{
            foundPerson = (Person)jdbcTemplateObject.queryForObject(sqlSelectPerson, new Object[]{email}, new PersonMapper());
        }catch(Exception e){
            return null;
        }        
        return foundPerson;
    }
    
    /*
    public List<Person> getAllePersoner(){
        return jdbcTemplateObject.query(sqlSelectAllePersoner, new PersonMapper());
    }*/

    /*
    public boolean slettPerson(Person person) {
        jdbcTemplateObject.update(sqlDeletePerson, person.getPersonnr() );
        return true;
    }
    */
    
    public boolean updatePerson(Person person){
        jdbcTemplateObject.update(sqlUpdatePerson, 
            new Object[]{                 
                person.getFname(), 
                person.getLname(),
                person.getHashedPassword(),
                person.getClassId(),
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
                person.getClassId()
        });
                
        return true;
    }
}
