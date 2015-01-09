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
import springmvc.domain.MultiChoice;
import springmvc.repository.mappers.MultiChoiceMapper;

/**
 *
 * @author eiriksandberg
 */
public class ResultRepoDB {
    private Connection forbindelse;
    private DataSource dataSource;
    JdbcTemplate jdbcTemplateObject;
    //SQL setninger:
    private final String sqlSetResult = "insert into multiresult values(default, ?, ?, ?)";
    
    public ResultRepoDB() {}
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        System.out.println(" Database.setDataSource " + dataSource);
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    
    public boolean setResult(String email, Double score, MultiChoice game){ 
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
}
