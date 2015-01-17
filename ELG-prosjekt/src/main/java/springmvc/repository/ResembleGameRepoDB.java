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
    private final String sqlSelectGameByName = "Select * from resemblegame where gamename = ?"; 
    private final String sqlSelectAllResembleGames = "Select * from resemblegame"; 
    private final String sqlInsertGame = "insert into resemblegame values (DEFAULT, ?, ?, ?, ?, ?)"; 
    
    private DataSource dataSource;
    JdbcTemplate jdbcTemplateObject;
    
    
    public ResembleGameRepoDB(){
    }
        
    @Autowired
    public void setDataSource(DataSource dataSource){
        System.out.println(" Database.setDataSource " + dataSource);
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    
    @Override
    public ResembleGame getResembleGame(int idGame){
        ResembleGame game = (ResembleGame)jdbcTemplateObject.queryForObject(sqlSelectGame, new Object[]{idGame}, new ResembleGameMapper());
        return game; 
    }
    
    public ResembleGame getResemleGameByName(String gameName){
        ResembleGame game = (ResembleGame)jdbcTemplateObject.queryForObject(sqlSelectGameByName, new Object[]{gameName}, new ResembleGameMapper());
        return game; 
    }
    
    public ArrayList<ResembleGame> getAllResembleGames(){
        ArrayList<ResembleGame> hei =  (ArrayList<ResembleGame>) jdbcTemplateObject.query(sqlSelectAllResembleGames, new ResembleGameMapper());
        return hei; 
    }
    
    public boolean insertResembleGame(String gameName, String info, String learningGoals, String difficulty, String creatorId){
        this.jdbcTemplateObject.update(sqlInsertGame, new Object[]{gameName, info, learningGoals, difficulty, creatorId});
        return true; 
    }
}
