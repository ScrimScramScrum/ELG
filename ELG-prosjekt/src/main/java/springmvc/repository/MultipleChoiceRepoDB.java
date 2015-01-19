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
<<<<<<< HEAD
    private final String sqlGetAllMultiChoiceGames = "select * from multichoicegame";
=======
    private final String sqlGetAllMultiChoiceGames = "select * from multichoicegame"; 
>>>>>>> FETCH_HEAD
    private final String sqlGetAllMultiChoiceGamesFromOving = "select * from multichoicegame where multichoicegame.idgame in (select idgamemulti from ovingmultigame where isextra = 0)"; 
    private final String sqlGetAllMultiChoiceGamesFromOvingExtra = "select * from multichoicegame where multichoicegame.idgame in (select idgamemulti from ovingmultigame where isextra = 1)"; 
    private final String sqlGetIdgameFromMultiChoiseWithGameNameAndEmail = "SELECT * FROM ELGUSER.MULTIRESULT WHERE idgame =(SELECT idgame FROM multichoicegame WHERE gamename = ?) AND email= ? ";
    private final String sqlRegGame = "insert into multichoicegame values(default, ?, ?, ?, ?, ?)";
    private final String sqlRegTasks = "insert into multiexercise values(default,?, ?, ?, ?, ?, ?, ?)";

    public MultipleChoiceRepoDB() {
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        System.out.println(" Database.setDataSource " + dataSource);
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public MultiChoice getMultiChoice(String gamename) {
        MultiChoice game = (MultiChoice) jdbcTemplateObject.queryForObject(sqlGetGame, new Object[]{gamename}, new MultiChoiceMapper());
        ArrayList<Exercise> exercises = getExercises(game.getGameid());
        game.setExercises(exercises);
        return game;
    }

    public ArrayList<MultiChoice> getAllMultiChoiceGames() {
        ArrayList<MultiChoice> allGames = (ArrayList<MultiChoice>) jdbcTemplateObject.query(sqlGetAllMultiChoiceGames, new MultiChoiceMapper());

        for (MultiChoice m : allGames) {
            m.setExercises(getExercises(m.getGameid()));
        }
        return allGames;
    }

    public ArrayList<Exercise> getExercises(int gameid) {
        return (ArrayList<Exercise>) jdbcTemplateObject.query(sqlGetExercises, new Object[]{gameid}, new MultiChoiceExerciseMapper());
    }

    public MultiChoiceInfo getMultiChoiceInfo(String gameName) {
        return jdbcTemplateObject.queryForObject(sqlGetGame, new Object[]{gameName}, new MultiChoiceInfoMapper());
    }

    public ArrayList<MultiChoiceInfo> getAllMultiChoiceInfo() {
        return (ArrayList<MultiChoiceInfo>) jdbcTemplateObject.query(sqlGetAllMultiChoiceGames, new MultiChoiceInfoMapper());
    }

    
    public ArrayList<MultiChoiceInfo> getAllMultiChoiceInfoFromOving(){
        System.out.println("** MultipleChoiceRepoDB: getAllMultiChoiceInfoFromOving  ** ");
        return (ArrayList<MultiChoiceInfo>)jdbcTemplateObject.query(sqlGetAllMultiChoiceGamesFromOving, new MultiChoiceInfoMapper()); 
    }
    
    public ArrayList<MultiChoiceInfo> getAllMultiChoiceInfoFromOvingExtra(){
        System.out.println("** MultipleChoiceRepoDB: getAllMultiChoiceInfoFromOving  ** ");
        return (ArrayList<MultiChoiceInfo>)jdbcTemplateObject.query(sqlGetAllMultiChoiceGamesFromOvingExtra, new MultiChoiceInfoMapper()); 
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
            multiResult = (MultiResult) jdbcTemplateObject.queryForObject(
                    sqlGetIdgameFromMultiChoiseWithGameNameAndEmail,
                    new Object[]{gamename, email}, new MultiResultMapper());

            System.out.println(multiResult.getScore());

        } catch (Exception e) {
            System.out.println("Erreoren er : " + e);

        }

        return multiResult;
    }

    public boolean regMultiChoiceGame(MultiChoice game) {
        try{
        jdbcTemplateObject.update(sqlRegGame, new Object[]{
            game.getName(),
            game.getInfo(),
            game.getLearningGoals(),
            game.getDifficulty(),
            game.getCreator()
        });
        regMultiTask(game);
        } catch(Exception e){
            System.out.println("FEIL! I regMultiChoiceGame" + e);
            return false;
        }
        return true;
    }

    public boolean regMultiTask(MultiChoice game) {
        try{
            ArrayList<Exercise> e = game.getExercises();
            for (int i = 0; i < e.size(); i++) {
                jdbcTemplateObject.update(sqlRegTasks, new Object[]{
                    e.get(i).getAlternativeIndex(0),
                e.get(i).getAlternativeIndex(1),
                e.get(i).getAlternativeIndex(2),
                e.get(i).getAlternativeIndex(3),
                e.get(i).getSolution(),
                e.get(i).getTaskText(),
                jdbcTemplateObject.queryForInt("select idgame from multichoicegame where gamename = ? and creator_id = ?", new Object[]{game.getName(), game.getCreator()})
            });
        }
        } catch (Exception e){
            System.out.println("FEIL! i regMultiTask() " + e);
            return false;
        }
            return true;
}
}
