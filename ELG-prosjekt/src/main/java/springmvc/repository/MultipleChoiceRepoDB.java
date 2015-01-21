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
    private final String sqlSelectAllMultiGamesNotInOving = "Select * from multichoicegame where multichoicegame.idgame NOT IN (Select idgamemulti from ovingmultigame)";

    private final String sqlGetIdgameFromMultiChoiseWithGameNameAndEmail = "SELECT * FROM ELGUSER.MULTIRESULT WHERE idgame =(SELECT idgame FROM multichoicegame WHERE gamename = ?) AND email= ? ";
    private final String sqlHasUserVotedMultiGame = "select COUNT(idPerson) FROM person_multi_vote WHERE idPerson = ? AND idmultigame IN(SELECT idgame FROM multichoicegame WHERE gamename = ?)"; 
    private final String sqlGetVoteCountByGameId = "select COUNT(idmultigame) from person_multi_vote WHERE idmultigame IN(SELECT idgame FROM multichoicegame WHERE gamename = ?)";

    private final String sqlRegisterMultiGameVote = "insert into person_multi_vote VALUES((SELECT idgame FROM multichoicegame WHERE gamename = ?), ?)";
    private final String sqlRegGame = "insert into multichoicegame values(default, ?, ?, ?, ?, ?)";
    private final String sqlRegTasks = "insert into multiexercise values(default,?, ?, ?, ?, ?, ?, ?)";
    private final String sqlMakeMultiGameExercise = "insert into ovingmultigame values (?, (SELECT idgame FROM multichoicegame WHERE gamename = ?), ?)";
    private final String sqlRemoveMultiGameFromExercise = "delete from ovingmultigame WHERE idgamemulti IN(SELECT idgame FROM multichoicegame WHERE gamename = ?)"; 
    

    public MultipleChoiceRepoDB() {
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        System.out.println(" Database.setDataSource " + dataSource);
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public MultiChoice getMultiChoice(String gamename) {
        MultiChoice game = new MultiChoice();
        try{
        game = (MultiChoice) jdbcTemplateObject.queryForObject(sqlGetGame, new Object[]{gamename}, new MultiChoiceMapper());
        ArrayList<Exercise> exercises = getExercises(game.getGameid());
        game.setExercises(exercises);
        } catch (Exception e){
            System.out.println("***Spillet finnes ikke*** getMultiChoice" + e);
            game = null;
        }
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
            int u = (int) jdbcTemplateObject.queryForInt("select idgame from multichoicegame where gamename = ? and creator_id = ?", new Object[]{game.getName(), game.getCreator()});
            for (int i = 0; i < e.size(); i++) {
                jdbcTemplateObject.update(sqlRegTasks, new Object[]{
                    e.get(i).getAlternativeIndex(0),
                    e.get(i).getAlternativeIndex(1),
                    e.get(i).getAlternativeIndex(2),
                    e.get(i).getAlternativeIndex(3),
                    e.get(i).getSolution(),
                    e.get(i).getTaskText(),
                    u
            });
        }
        } catch (Exception e){
            System.out.println("FEIL! i regMultiTask() " + e);
            return false;
        }
            return true;
}

    @Override
    public boolean registerMultiGameVote(String usermail, String gameId) {
        this.jdbcTemplateObject.update(sqlRegisterMultiGameVote, new Object[]{gameId, usermail}); 
        return true; 
    }

    @Override
    public int hasUserVotedMultiGame(String usermail, String gameId) {
        return this.jdbcTemplateObject.queryForObject(sqlHasUserVotedMultiGame, new Object[]{usermail, gameId}, Integer.class);
    }

    @Override
    public boolean updateUserMultiVote(String usermail, String gameId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getVoteCountByGameId(String gameId) {
        return this.jdbcTemplateObject.queryForObject(sqlGetVoteCountByGameId, new Object[]{gameId}, Integer.class); 
    }

    @Override
    public ArrayList<MultiChoiceInfo> getAllMultiGamesNotInOving() {
        return (ArrayList<MultiChoiceInfo>)jdbcTemplateObject.query(sqlSelectAllMultiGamesNotInOving, new MultiChoiceInfoMapper());
    }

    @Override
    public boolean makeMultiGameExercise(String gameId){
        this.jdbcTemplateObject.update(sqlMakeMultiGameExercise, new Object[]{1, gameId, 0});
        return true; 
    }
    
    @Override
    public boolean makeMultiGameExerciseExtra(String gameId){
        this.jdbcTemplateObject.update(sqlMakeMultiGameExercise, new Object[]{1, gameId, 1});
        return true; 
    }
    
    @Override
    public boolean removeMultiGameFromExercise(String gameId){
        this.jdbcTemplateObject.update(sqlRemoveMultiGameFromExercise, new Object[]{gameId});
        return true; 
    }
}
