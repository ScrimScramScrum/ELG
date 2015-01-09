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
import springmvc.repository.mappers.HighscoreMapper;
import springmvc.repository.mappers.MultiChoiceMapper;


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
    public ResultRepoDB() {}
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        System.out.println(" Database.setDataSource " + dataSource);
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    
    public boolean regMultiChoiceRes(String email, Double score, MultiChoice game){ 
        try{
            jdbcTemplateObject.update(sqlSetResult, 
            new Object[]{
                score, 
                email, 
                game.getGameid()
        });
        } catch (Exception e){
            return false;
        }
        return true;
    }
    
        public int getMultiChoiceRes(String email, MultiChoice game){
            int i;
           try{
               i =  (int)jdbcTemplateObject.queryForInt(sqlGetResult, new Object[]{email, game.getGameid()});
               
           } catch(Exception e){
               System.out.println("Ingen score registrert");
               i = 0;
           }
            return i;
        }
        
        public boolean updateMultiResult(String email, double score, MultiChoice game){
            jdbcTemplateObject.update(sqlUpdate, new Object[]{
                score, 
                email, 
                game.getGameid()
        });
            return true;
     }
        
        public ArrayList<HighscoreDisplay> highscoreMC(MultiChoice game){
            ArrayList<HighscoreDisplay> l = new ArrayList<HighscoreDisplay>();
            try{
            l = (ArrayList<HighscoreDisplay>)jdbcTemplateObject.query(sqlGetHighscore, new Object[]{game.getGameid()}, new HighscoreMapper());
                //System.out.println("har laget highscoreliste" + l.get(0).getFname());
            } catch(Exception e){
                System.out.println("Feilxxxxxxxxxxx: " + e);
            }
            return l;
        }
}
