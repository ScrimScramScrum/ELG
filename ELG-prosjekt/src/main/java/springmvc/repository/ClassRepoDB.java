    package springmvc.repository;

import java.sql.Connection;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import springmvc.ui.AddNewClassId;

public class ClassRepoDB {
    
    
    private Connection forbindelse;
    private final String sqlSelectClassId = "Select * from classes where classname = ?";    
    private final String sqlInsertClassId = "insert into classes values(?)";
    private final String sqlInsertStudentIntoClass = "insert into personclass values(?, ?)";
    private final String sqlUpdateStudentInClass = "update personclass set classname= ? where email = ?";

    
    private DataSource dataSource;
    JdbcTemplate jdbcTemplateObject;
    
    public ClassRepoDB() {}
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    
    public AddNewClassId getClassId(String classId){  
        AddNewClassId foundAddNewClassId= new AddNewClassId();        
        try{
            foundAddNewClassId = (AddNewClassId)jdbcTemplateObject.queryForObject(sqlSelectClassId, new Object[]{classId}, new ClassMapper());
        }catch(Exception e){
            return null;
        }        
        return foundAddNewClassId;
    }
    
    public boolean registerNewClassId(String addNewClassId){  
        try{
            jdbcTemplateObject.update(sqlInsertClassId, new Object[]{addNewClassId});
            return true; 
        } catch (Exception e){
            return false; 
        }
    }
    
    
    
    public boolean registerStudentIntoAClass(String emailStudent, String classId){          
        try{jdbcTemplateObject.update(sqlInsertStudentIntoClass, 
            new Object[]{
                emailStudent,
                classId
        });
        
        return true;
            
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean updateStudentInClass(String emailStudent, String classId){
        try{jdbcTemplateObject.update(sqlUpdateStudentInClass, 
            new Object[]{
                classId,                 
                emailStudent
                
        });
        
        return true;
            
        } catch (Exception e) {
            return false;
        }
    } 
}
