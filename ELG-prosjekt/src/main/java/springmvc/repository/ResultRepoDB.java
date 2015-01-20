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
import springmvc.repository.mappers.ClassMapper;
import springmvc.repository.mappers.CompletionMapper;
import springmvc.repository.mappers.EmailMapper;
import springmvc.repository.mappers.GameIDMapper;
import springmvc.repository.mappers.HighscoreMapper;
import springmvc.repository.mappers.MultiChoiceMapper;
import springmvc.repository.mappers.OvingMapper;

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

    private final String sqlGetAllOvinger = "select ovingname from oving";
    private final String sqlGetAllClasses = "select classes.classname from classes join personclass on classes.classname = personclass.classname where email = ?";
    private final String sqlGetNumberInClass = "select count(personclass.email) from personclass join person on person.EMAIL=personclass.EMAIL where classname = ? and administrator = 0";
    //Sql completion
    private final String sqlGetGamesInOving = "select idgamemulti from oving join ovingmultigame on oving.IDOVING = ovingmultigame.IDOVING";
    private final String sqlGetPassedExercise = "select email from multiresult where idgame = ? and score > 79";
    private final String sqlGetCompletedNames = "select fname, lname from person join personclass on person.EMAIL= personclass.EMAIL where personclass.email = ? and classname = ? and administrator = 0";

    private final String sqlGetGamesInOvingResemble = "select idgameresemble from oving join ovingresemblegame on oving.IDOVING = ovingresemblegame.IDOVING";
    private final String sqlGetPassedExerciseResemble = "select email from resembleresult where idgame = ? and score > 79";
    private final String sqlGetCompletedNamesResemble = "select fname, lname from person join personclass on person.EMAIL= personclass.EMAIL where personclass.email = ? and classname = ? and administrator = 0";

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

    public ArrayList<String> getAllOvinger() {
        ArrayList<String> l = new ArrayList<String>();
        try {
            l = (ArrayList<String>) jdbcTemplateObject.query(sqlGetAllOvinger, new Object[]{}, new OvingMapper());

        } catch (Exception e) {
            System.out.println("Kan ikke hente øvinger");
        }
        return l;
    }

    public ArrayList<String> getAllClasses(String email) {
        ArrayList<String> l = new ArrayList<String>();
        try {
            l = (ArrayList<String>) jdbcTemplateObject.query(sqlGetAllClasses, new Object[]{email}, new ClassMapper());

        } catch (Exception e) {
            System.out.println("Kan ikke hente klasser");
        }
        return l;
    }

    public int getNumberInClass(String classname) {
        int i;
        try {
            i = (int) jdbcTemplateObject.queryForInt(sqlGetNumberInClass, new Object[]{classname});

        } catch (Exception e) {
            System.out.println("Ingen går i denne klassen");
            i = 0;
        }
        return i;
    }

    public ArrayList<HighscoreDisplay> getNewCompletionlistMulti(String classname) {
        ArrayList<Integer> gamesInOving = new ArrayList<Integer>();
        ArrayList<String> passedExercise = new ArrayList<String>();
        ArrayList<String> completionlist = new ArrayList<String>();
        ArrayList<HighscoreDisplay> nameList = new ArrayList<>();
        try {
            gamesInOving = (ArrayList<Integer>) jdbcTemplateObject.query(sqlGetGamesInOving, new Object[]{}, new GameIDMapper());
            System.out.println("gamesInOving.size() = " + gamesInOving.size()); // ******TEST******
            for (int i = 0; i < gamesInOving.size(); i++) {
                if (i == 0) {
                    completionlist = (ArrayList<String>) jdbcTemplateObject.query(sqlGetPassedExercise, new Object[]{gamesInOving.get(i)}, new EmailMapper());
                } else {
                    passedExercise = (ArrayList<String>) jdbcTemplateObject.query(sqlGetPassedExercise, new Object[]{gamesInOving.get(i)}, new EmailMapper());
                }
                if (i != 0) {
                    for (int c = 0; c < completionlist.size(); c++) {
                        boolean found = false;
                        for (int u = 0; u < passedExercise.size(); u++) {
                            if (completionlist.get(c).equals(passedExercise.get(u))) {
                                found = true;
                            }
                        }
                        if (found == false) {
                            completionlist.remove(c);
                        }
                    }
                }
            }
            System.out.println("Lengde på completionlist= " + completionlist.size());
            for (int i = 0; i < completionlist.size(); i++) {
                HighscoreDisplay person = (HighscoreDisplay) jdbcTemplateObject.queryForObject(sqlGetCompletedNames, new Object[]{completionlist.get(i), classname}, new CompletionMapper());
                nameList.add(person);
            }
        } catch (Exception e) {
            System.out.println("Feilxxxxxxxxxxx: " + e);
        }
        return nameList;
    }

    public ArrayList<HighscoreDisplay> getNewCompletionlistResemble(String classname) {
        ArrayList<Integer> gamesInOving = new ArrayList<Integer>();
        ArrayList<String> passedExercise = new ArrayList<String>();
        ArrayList<String> completionlist = new ArrayList<String>();
        ArrayList<HighscoreDisplay> nameList = new ArrayList<>();
        try {
            gamesInOving = (ArrayList<Integer>) jdbcTemplateObject.query(sqlGetGamesInOvingResemble, new Object[]{}, new GameIDMapper());
            System.out.println("gamesInOving.size() = " + gamesInOving.size()); // ******TEST******
            for (int i = 0; i < gamesInOving.size(); i++) {
                if (i == 0) {
                    completionlist = (ArrayList<String>) jdbcTemplateObject.query(sqlGetPassedExerciseResemble, new Object[]{gamesInOving.get(i)}, new EmailMapper());
                } else {
                    passedExercise = (ArrayList<String>) jdbcTemplateObject.query(sqlGetPassedExerciseResemble, new Object[]{gamesInOving.get(i)}, new EmailMapper());
                }
                if (i != 0) {
                    for (int c = 0; c < completionlist.size(); c++) {
                        boolean found = false;
                        for (int u = 0; u < passedExercise.size(); u++) {
                            if (completionlist.get(c).equals(passedExercise.get(u))) {
                                found = true;
                            }
                        }
                        if (found == false) {
                            completionlist.remove(c);
                        }
                    }
                }
            }
            System.out.println("Lengde på completionlist= " + completionlist.size());
            for (int i = 0; i < completionlist.size(); i++) {
                HighscoreDisplay person = (HighscoreDisplay) jdbcTemplateObject.queryForObject(sqlGetCompletedNamesResemble, new Object[]{completionlist.get(i), classname}, new CompletionMapper());
                nameList.add(person);
            }
        } catch (Exception e) {
            System.out.println("Feilxxxxxxxxxxx: " + e);
        }
        return nameList;
    }
}
