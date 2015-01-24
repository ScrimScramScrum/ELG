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
    private final String sqlSelectAllResembleGamesFromOving = "Select * from resemblegame where resemblegame.idgame in (select idgameresemble from ovingresemblegame where isextra = 0)"; 
    //private final String sqlSelectAllResembleGamesFromOving = "Select * from resemblegame where resemblegame.idgame in (select idgameresemble from ovingresemblegame)"; 
    private final String sqlSelectAllResembleGamesFromOvingExtra = "Select * from resemblegame where resemblegame.idgame in (select idgameresemble from ovingresemblegame where isextra = 1)"; 
    private final String sqlSelectAllResembleGamesNotInOving = "Select * from resemblegame where resemblegame.idgame NOT IN (Select idgameresemble from ovingresemblegame)";
    private final String sqlInsertGame = "insert into resemblegame values (DEFAULT, ?, ?, ?, ?, ?)"; 
    private final String sqlGetScoreFromFromResebleGameWithNameAndEmail = "SELECT score FROM resembleresult WHERE idgame =(SELECT idgame FROM resemblegame WHERE gamename = ?) AND email = ?";
    private final String sqlGetVoteCountByGameId = "select COUNT(idResembleGame) from person_resemble_vote WHERE idResembleGame = ?";
    private final String sqlHasUserVotedResembleGame = "select COUNT(idPerson) FROM person_resemble_vote WHERE idPerson = ? AND idresemblegame = ?"; 
    private final String sqlUpdateUserResembleVote = "UPDATE person_resemble_vote SET idResembleGame = ? where idPerson = ?";
    private final String sqlRegisterResembleGameVote = "insert into person_resemble_vote VALUES(?, ?)";
    private final String sqlMakeResembleGameExercise = "insert into ovingresemblegame values (?, ?, ?)";
    private final String sqlRemoveResembleGameFromExercise = "delete from ovingresemblegame WHERE idgameresemble = ?"; 

    private DataSource dataSource;
    JdbcTemplate jdbcTemplateObject;
    
    
    public ResembleGameRepoDB(){
    }
        
    @Autowired
    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    
    @Override
    public ResembleGame getResembleGame(int idGame){
        ResembleGame game = (ResembleGame)jdbcTemplateObject.queryForObject(sqlSelectGame, new Object[]{idGame}, new ResembleGameMapper());
        return game; 
    }
    
    @Override
    public ResembleGame getResemleGameByName(String gameName){
        ResembleGame game = (ResembleGame)jdbcTemplateObject.queryForObject(sqlSelectGameByName, new Object[]{gameName}, new ResembleGameMapper());
        return game; 
    }
    
    @Override
    public ArrayList<ResembleGame> getAllResembleGames(){
        ArrayList<ResembleGame> hei =  (ArrayList<ResembleGame>) jdbcTemplateObject.query(sqlSelectAllResembleGames, new ResembleGameMapper());
        return hei; 
    }
    
    
    @Override
    public ArrayList<ResembleGame> getAllResembleGamesFromOving(){
        ArrayList<ResembleGame> resembleGames =  (ArrayList<ResembleGame>) jdbcTemplateObject.query(sqlSelectAllResembleGamesFromOving, new ResembleGameMapper());
        return resembleGames; 
    }
    
    @Override
    public ArrayList<ResembleGame> getAllResembleGamesFromOvingExtra(){
        ArrayList<ResembleGame> resembleGames =  (ArrayList<ResembleGame>) jdbcTemplateObject.query(sqlSelectAllResembleGamesFromOvingExtra, new ResembleGameMapper());
        return resembleGames; 
    }
    
    @Override
    public boolean insertResembleGame(String gameName, String info, String learningGoals, String difficulty, String creatorId){
        this.jdbcTemplateObject.update(sqlInsertGame, new Object[]{gameName, info, learningGoals, difficulty, creatorId});
        return true; 
    }
    @Override
     public int sqlGetScoreFromFromResebleGameWithNameAndEmail(String gamename, String email){
        int score = -1;
         try{
               score = Integer.parseInt((String)jdbcTemplateObject.queryForObject(sqlGetScoreFromFromResebleGameWithNameAndEmail, new Object[] { gamename, email }, String.class));
        } catch (Exception e){
        }
        return score;   
    }
     
    @Override 
    public ArrayList<ResembleGame> getAllResembleGamesNotInOving(){
        return (ArrayList<ResembleGame>) jdbcTemplateObject.query(sqlSelectAllResembleGamesNotInOving, new ResembleGameMapper());
    }
    
    @Override
    public int getVoteCountByGameId(int gameId){
        return this.jdbcTemplateObject.queryForObject(sqlGetVoteCountByGameId, new Object[]{gameId}, Integer.class); 
    }
    
    @Override
    public boolean registerResembleGameVote(String usermail, int gameId){
        this.jdbcTemplateObject.update(sqlRegisterResembleGameVote, new Object[]{gameId, usermail});
        return true; 
    }
    
    @Override 
    public boolean updateUserResembleVote(String usermail, int gameId){
        this.jdbcTemplateObject.update(sqlUpdateUserResembleVote, new Object[]{gameId, usermail}); 
        return true; 
    }
    
    @Override
    public int hasUserVotedResembleGame(String usermail, int gameId){
        return this.jdbcTemplateObject.queryForObject(sqlHasUserVotedResembleGame, new Object[]{usermail, gameId}, Integer.class); 
    }
    
    @Override
    public boolean makeResembleGameExercise(int gameId){
        this.jdbcTemplateObject.update(sqlMakeResembleGameExercise, new Object[]{1, gameId, 0});
        return true; 
    }
    
    @Override
    public boolean makeResembleGameExerciseExtra(int gameId){
        this.jdbcTemplateObject.update(sqlMakeResembleGameExercise, new Object[]{1, gameId, 1});
        return true; 
    }
    
    @Override
    public boolean removeResembleGameFromExercise(int gameId){
        this.jdbcTemplateObject.update(sqlRemoveResembleGameFromExercise, new Object[]{gameId});
        return true; 
    }
}
