package springmvc.repository;

import java.sql.Connection;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import springmvc.domain.ResembleGame;
import springmvc.repository.mappers.ResembleGameMapper;

public class ResembleGameRepoDB implements ResembleGameRepo{
    private Connection forbindelse;
    private final String sqlSelectGame = "Select * from resemblegame where idGame = ?"; 
    private final String sqlSelectAllResembleGames = "Select * from resemblegame"; 

    private DataSource dataSource;
    JdbcTemplate jdbcTemplateObject;
        
    @Autowired
    public void setDataSource(DataSource dataSource){
        System.out.println(" Database.setDataSource " + dataSource);
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    
    public ResembleGame getResembleGame(int idGame){
        return (ResembleGame)jdbcTemplateObject.queryForObject(sqlSelectGame, new Object[]{idGame}, new ResembleGameMapper());
    }
    
    public ArrayList<ResembleGame> getAllResembleGames(){
        return (ArrayList<ResembleGame>) jdbcTemplateObject.query(sqlSelectAllResembleGames, new ResembleGameMapper());
    }
}
