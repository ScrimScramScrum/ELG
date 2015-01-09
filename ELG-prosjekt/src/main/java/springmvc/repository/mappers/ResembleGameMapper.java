/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import springmvc.domain.ResembleGame;

/**
 *
 * @author Jorgen
 */
public class ResembleGameMapper implements RowMapper<ResembleGame>{
    
    @Override
    public ResembleGame mapRow(ResultSet rs, int i) throws SQLException{
        ResembleGame resembleGame = new ResembleGame(); 
        resembleGame.setGameId(rs.getInt("idGame"));
        resembleGame.setLearningGoal(rs.getString("learning_goals"));
        resembleGame.setInfo(rs.getString("info"));
        resembleGame.setDifficulty(rs.getInt("difficulty"));
        //TASKNUMBERS?!?!?!?!        
        return resembleGame; 
    }
}
