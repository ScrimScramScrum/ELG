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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import springmvc.domain.HighscoreDisplay;
import springmvc.domain.MultiChoice;
import springmvc.domain.ResembleGame;
import springmvc.repository.mappers.CompletionMapper;
import springmvc.repository.mappers.HighscoreMapper;
import springmvc.repository.mappers.MultiChoiceMapper;
import springmvc.repository.mappers.OvingMapper;
import springmvc.repository.mappers.ClassMapper;
/**
 *
 * @author eiriksandberg
 */
public class ResultRepoDB implements ResultRepo {

    private Connection forbindelse;
    private DataSource dataSource;
    JdbcTemplate jdbcTemplateObject;
    //SQL setninger:
    private final String sqlSetResult = "insert into multiresult values(default, ?, ?, ?)";
    private final String sqlGetResult = "select score from multiresult where email = ? and idGame = ?";
    private final String sqlUpdate = "update multiresult set score = ? where email = ? and idGame = ?";
    private final String sqlGetHighscore = "select score, fname, lname from multiresult join person on multiresult.email = person.email where idGame = ? order by score desc fetch first 10 rows only";
    private final String sqlSetResultResemble = "insert into resembleresult values(default, ?, ?, ?)";
    private final String sqlGetResultResemble = "select score from resembleresult where email = ? and idGame = ?";
    private final String sqlUpdateResemble = "update resembleresult set score = ? where email = ? and idGame = ?";
    private final String sqlGetHighscoreResemble = "select score, fname, lname from resembleresult join person on resembleresult.email = person.email where idGame = ? order by score desc fetch first 10 rows only";
    private final String sqlGetCompletion = "select distinct fname, lname from oving " +
                                            "join ovingmultigame on oving.IDOVING = ovingmultigame.IDOVING " +
                                            "join multiresult on multiresult.IDGAME = ovingmultigame.IDGAMEMULTI " +
                                            "join personclass on personclass.EMAIL = multiresult.EMAIL " +
                                            "join person on person.EMAIL = personclass.EMAIL " +
                                            "where classname = ? and score > 79 and ((select count(ovingmultigame.IDGAMEMULTI) from ovingmultigame)*(select count (distinct multiresult.EMAIL) from oving " +
                                            "join ovingmultigame on oving.IDOVING = ovingmultigame.IDOVING " +
                                            "left join multiresult on multiresult.IDGAME = ovingmultigame.IDGAMEMULTI " +
                                            "join personclass on personclass.EMAIL = multiresult.EMAIL " +
                                            "join person on person.EMAIL = personclass.EMAIL " +
                                            "where score > 79 and classname = ?)) = (select count (multiresult.email) from oving " +
                                            "join ovingmultigame on oving.IDOVING = ovingmultigame.IDOVING " +
                                            "join multiresult on multiresult.IDGAME = ovingmultigame.IDGAMEMULTI " +
                                            "join personclass on personclass.EMAIL = multiresult.EMAIL " +
                                            "join person on person.EMAIL = personclass.EMAIL " +
                                            "where score > 79 and classname = ?)";

    
    private final String sqlGetCompletionResemble ="select distinct fname, lname from oving\n" +
                                                    "join ovingresemblegame on oving.IDOVING = ovingresemblegame.IDOVING\n" +
                                                    "join resembleresult on resembleresult.IDGAME = ovingresemblegame.IDGAMERESEMBLE\n" +
                                                    "join personclass on personclass.EMAIL = resembleresult.EMAIL\n" +
                                                    "join person on person.EMAIL = personclass.EMAIL \n" +
                                                    "where classname = ? and score > 79 and ((select count(ovingresemblegame.IDGAMERESEMBLE) from ovingresemblegame)*(select count (distinct resembleresult.EMAIL) from oving\n" +
                                                    "join ovingresemblegame on oving.IDOVING = ovingresemblegame.IDOVING\n" +
                                                    "left join resembleresult on resembleresult.IDGAME = ovingresemblegame.IDGAMERESEMBLE\n" +
                                                    "join personclass on personclass.EMAIL = resembleresult.EMAIL\n" +
                                                    "join person on person.EMAIL = personclass.EMAIL\n" +
                                                    "where score > 79 and classname = ?)) = (select count (resembleresult.email) from oving\n" +
                                                    "join ovingresemblegame on oving.IDOVING = ovingresemblegame.IDOVING\n" +
                                                    "join resembleresult on resembleresult.IDGAME = ovingresemblegame.IDGAMERESEMBLE\n" +
                                                    "join personclass on personclass.EMAIL = resembleresult.EMAIL\n" +
                                                    "join person on person.EMAIL = personclass.EMAIL\n" +
                                                    "where score > 79 and classname = ?)";
    
    private final String sqlGetAllOvinger = "select ovingname from oving";
    private final String sqlGetAllClasses = "select classes.classname from classes join personclass on classes.classname = personclass.classname where email = ?";
    
    public ResultRepoDB() {
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        System.out.println(" Database.setDataSource " + dataSource);
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public boolean regMultiChoiceRes(String email, Double score, MultiChoice game) {
        try {
            jdbcTemplateObject.update(sqlSetResult,
                    new Object[]{
                        score,
                        email,
                        game.getGameid()
                    });
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public int getMultiChoiceRes(String email, MultiChoice game) {
        int i;
        try {
            i = (int) jdbcTemplateObject.queryForInt(sqlGetResult, new Object[]{email, game.getGameid()});

        } catch (Exception e) {
            System.out.println("Ingen score registrert");
            i = 0;
        }
        return i;
    }

    public boolean updateMultiResult(String email, double score, MultiChoice game) {
        jdbcTemplateObject.update(sqlUpdate, new Object[]{
            score,
            email,
            game.getGameid()
        });
        return true;
    }

    public ArrayList<HighscoreDisplay> highscoreMC(MultiChoice game) {
        ArrayList<HighscoreDisplay> l = new ArrayList<HighscoreDisplay>();
        try {
            l = (ArrayList<HighscoreDisplay>) jdbcTemplateObject.query(sqlGetHighscore, new Object[]{game.getGameid()}, new HighscoreMapper());
            //System.out.println("har laget highscoreliste" + l.get(0).getFname());
        } catch (Exception e) {
            System.out.println("Feilxxxxxxxxxxx: " + e);
        }
        return l;
    }

    public boolean regResembleGameRes(String email, Double score, ResembleGame game) {
        try {
            jdbcTemplateObject.update(sqlSetResultResemble,
                    new Object[]{
                        score,
                        email,
                        game.getGameId()
                    });
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public int getResembleGameRes(String email, ResembleGame game) {
        int i;
        try {
            i = (int) jdbcTemplateObject.queryForInt(sqlGetResultResemble, new Object[]{email, game.getGameId()});

        } catch (Exception e) {
            System.out.println("Ingen score registrert");
            i = 0;
        }
        return i;
    }

    public boolean updateResembleResult(String email, double score, ResembleGame game) {
        jdbcTemplateObject.update(sqlUpdateResemble, new Object[]{
            score,
            email,
            game.getGameId()
        });
        return true;
    }

    public ArrayList<HighscoreDisplay> highscoreRG(ResembleGame game) {
        ArrayList<HighscoreDisplay> l = new ArrayList<HighscoreDisplay>();
        try {
            l = (ArrayList<HighscoreDisplay>) jdbcTemplateObject.query(sqlGetHighscoreResemble, new Object[]{game.getGameId()}, new HighscoreMapper());
            //System.out.println("har laget highscoreliste" + l.get(0).getFname());
        } catch (Exception e) {
            System.out.println("Feilxxxxxxxxxxx: " + e);
        }
        return l;
    }
    
    public ArrayList <HighscoreDisplay> getCompletion(String classname){
            String c2 = classname;
            String c3 = classname;
                  
                ArrayList<HighscoreDisplay> l = new ArrayList<HighscoreDisplay>();
        try {
            l = (ArrayList<HighscoreDisplay>) jdbcTemplateObject.query(sqlGetCompletion, new Object[]{classname, c2, c3}, new CompletionMapper());
            //System.out.println("har laget highscoreliste" + l.get(0).getFname());
        } catch (Exception e) {
            System.out.println("Feilxxxxxxxxxxx: " + e);
        }
        return l;
    }
    
    public ArrayList <HighscoreDisplay> getCompletionRG(String classname){
            String c2 = classname;
            String c3 = classname;
        ArrayList<HighscoreDisplay> l = new ArrayList<HighscoreDisplay>();
        try {
            l = (ArrayList<HighscoreDisplay>) jdbcTemplateObject.query(sqlGetCompletionResemble, new Object[]{classname, c2, c3}, new CompletionMapper());
            //System.out.println("har laget highscoreliste" + l.get(0).getFname());
        } catch (Exception e) {
            System.out.println("Feilxxxxxxxxxxx: " + e);
        }
        return l;
    }
    
    public ArrayList <String> getAllOvinger(){
        ArrayList<String> l = new ArrayList<String>();
        try {
            l = (ArrayList<String>) jdbcTemplateObject.query(sqlGetAllOvinger, new Object[]{}, new OvingMapper());

        } catch (Exception e) {
            System.out.println("Kan ikke hente øvinger");
        }
        return l;
    }
    
    public ArrayList <String> getAllClasses(String email){
        ArrayList<String> l = new ArrayList<String>();
        try {
            l = (ArrayList<String>) jdbcTemplateObject.query(sqlGetAllClasses, new Object[]{email}, new ClassMapper());

        } catch (Exception e) {
            System.out.println("Kan ikke hente klasser");
        }
        return l;
    }
}
