package springmvc.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import springmvc.domain.Person;

public class PersonMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet rs, int i) throws SQLException {
        Person person = new Person();
        person.setEmail(rs.getString("email"));
        person.setFname(rs.getString("fname"));
        person.setLname(rs.getString("lname"));  
        person.setHashedPassword(rs.getString("hashPassword"));
        person.setClassId(rs.getString("classId"));        
        return person;
    }
}
