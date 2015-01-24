package springmvc.repository;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import springmvc.domain.ResembleTask;
import springmvc.repository.mappers.ResembleTaskMapper;

public class ResembleTaskRepoDB implements ResembleTaskRepo{
    private Connection forbindelse;
    private final String sqlDeleteTask = "Delete from resembletask where idTask = ?";
    private final String sqlSelectTask = "Select * from resembletask where idTask = ? ";
    private final String sqlSelectAllTasks = "Select * from resembletask";
    private final String sqlSelectTasksByGameId = "Select * from resembletask where idGame = ?";
    
    private final String sqlInsertTask = "insert into resembletask values(DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?)";
   // private final String sqlUpdateTask = "update person set fornavn=?, etternavn = ? where personnr = ?";

    
    private DataSource dataSource;
    JdbcTemplate jdbcTemplateObject;
    
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    
    public ResembleTask getResembleTask(int idTask){
        return (ResembleTask)jdbcTemplateObject.queryForObject(sqlSelectTask, new Object[]{idTask}, (RowMapper<ResembleTask>)new ResembleTaskMapper());
    }
    
    public void deleteResembleTask(int idTask){
        jdbcTemplateObject.update(sqlDeleteTask, idTask);
    }
    
    public ArrayList<ResembleTask> getResembleTasks(ArrayList<Integer> taskNumbers){
        ArrayList<ResembleTask> tasks = new ArrayList<>(); 
        ResembleTask task = null; 
        for(Integer i : taskNumbers){
            task = getResembleTask(i); 
            if(task !=null){
                tasks.add(task); 
            }
        }
        return tasks;
        //return (ArrayList<ResembleTask>)jdbcTemplateObject.query(sqlSelectTask, new Object[]{taskNumbers}, new ResembleTaskMapper());
    }
    
    public ArrayList<ResembleTask> getResembleTasksByGameId(int gameId){
        return (ArrayList<ResembleTask>) jdbcTemplateObject.query(sqlSelectTasksByGameId, new Object[]{gameId}, new ResembleTaskMapper()); 
    }

    public boolean insertResembleTask(String taskText, String solutionHTML, String solutionCSS, String startingHTML, String startingCSS,  int width, int height, int idGame){
        this.jdbcTemplateObject.update(sqlInsertTask, new Object[]{taskText, solutionHTML, solutionCSS, startingHTML, startingCSS, width, height, idGame});
        return true; 
    }
}
