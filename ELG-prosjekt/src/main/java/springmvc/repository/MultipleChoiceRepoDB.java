/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springmvc.repository;

import java.sql.Connection;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import springmvc.domain.Exercise;
import springmvc.domain.MultiChoice;
import springmvc.domain.MultiChoiceInfo;
import springmvc.domain.MultiResult;
import springmvc.repository.mappers.MultiChoiceExerciseMapper;
import springmvc.repository.mappers.MultiChoiceInfoMapper;
import springmvc.repository.mappers.MultiChoiceMapper;
import springmvc.repository.mappers.MultiResultMapper;
/**
 *
 * @author eiriksandberg
 */
public class MultipleChoiceRepoDB implements MultiChoiceRepository {

    private Connection forbindelse;
    private DataSource dataSource;
    JdbcTemplate jdbcTemplateObject;
    //SQL setninger:
    private final String sqlGetGame = "select * from multichoicegame where gamename = ?";
    private final String sqlGetExercises = "select * from multiexercise where idGame = ?";
    private final String sqlGetAllMultiChoiceGames = "select * from multichoicegame"; 
    private final String sqlGetAllMultiChoiceGamesFromOving = "select * from multichoicegame where multichoicegame.idgame in (select idgamemulti from ovingmultigame where isextra = 0)"; 
    private final String sqlGetAllMultiChoiceGamesFromOvingExtra = "select * from multichoicegame where multichoicegame.idgame in (select idgamemulti from ovingmultigame where isextra = 1)"; 
    private final String sqlGetIdgameFromMultiChoiseWithGameNameAndEmail = "SELECT * FROM ELGUSER.MULTIRESULT WHERE idgame =(SELECT idgame FROM multichoicegame WHERE gamename = ?) AND email= ? ";
    public MultipleChoiceRepoDB() {}
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        System.out.println(" Database.setDataSource " + dataSource);
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    
    public MultiChoice getMultiChoice(String gamename){ 
        MultiChoice game = (MultiChoice)jdbcTemplateObject.queryForObject(sqlGetGame, new Object[]{gamename}, new MultiChoiceMapper());
        ArrayList<Exercise> exercises = getExercises(game.getGameid());
        game.setExercises(exercises);
        return game;
    }
    
    public ArrayList<MultiChoice> getAllMultiChoiceGames(){
        ArrayList<MultiChoice> allGames = (ArrayList<MultiChoice>) jdbcTemplateObject.query(sqlGetAllMultiChoiceGames, new MultiChoiceMapper()); 
        
        for(MultiChoice m : allGames){
            m.setExercises(getExercises(m.getGameid()));
        }
        return allGames; 
    }
    
    public ArrayList<Exercise> getExercises(int gameid){
        return (ArrayList<Exercise>) jdbcTemplateObject.query(sqlGetExercises, new Object[]{gameid}, new MultiChoiceExerciseMapper());
    }
    
    public MultiChoiceInfo getMultiChoiceInfo(String gameName){
        return jdbcTemplateObject.queryForObject(sqlGetGame, new Object[]{gameName}, new MultiChoiceInfoMapper()); 
    }
    
    public ArrayList<MultiChoiceInfo> getAllMultiChoiceInfo(){
        return (ArrayList<MultiChoiceInfo>)jdbcTemplateObject.query(sqlGetAllMultiChoiceGames, new MultiChoiceInfoMapper()); 
    }
    
    public ArrayList<MultiChoiceInfo> getAllMultiChoiceInfoFromOving(){
        System.out.println("** MultipleChoiceRepoDB: getAllMultiChoiceInfoFromOving  ** ");
        return (ArrayList<MultiChoiceInfo>)jdbcTemplateObject.query(sqlGetAllMultiChoiceGamesFromOving, new MultiChoiceInfoMapper()); 
    }
    
    public ArrayList<MultiChoiceInfo> getAllMultiChoiceInfoFromOvingExtra(){
        System.out.println("** MultipleChoiceRepoDB: getAllMultiChoiceInfoFromOving  ** ");
        return (ArrayList<MultiChoiceInfo>)jdbcTemplateObject.query(sqlGetAllMultiChoiceGamesFromOvingExtra, new MultiChoiceInfoMapper()); 
    }
    
    public MultiResult getMultiChoiceAndUsername(String gamename, String email){ 
        System.out.println("runnign now getMultiChoiceAndUsername");
        MultiResult multiResult = null;
        try {
             multiResult = (MultiResult)jdbcTemplateObject.queryForObject(
                sqlGetIdgameFromMultiChoiseWithGameNameAndEmail, 
                new Object[]{gamename, email}, new MultiResultMapper());
        
        System.out.println(multiResult.getScore());
        
        }
        
        catch(Exception e){
            System.out.println("Erreoren er : "+e);
            
        }
        
        
        return multiResult;
    }
}