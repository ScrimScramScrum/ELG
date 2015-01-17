/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springmvc.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import springmvc.domain.MultiResult;
import springmvc.domain.ResembleGame;


public class MultiResultMapper implements RowMapper<MultiResult>{
    @Override
    public MultiResult mapRow(ResultSet rs, int i) throws SQLException{
        MultiResult multiResult = new MultiResult();
        multiResult.setIdResult(rs.getInt("idresult"));
         multiResult.setScore(rs.getInt("score"));
        multiResult.setEmail(rs.getString("email"));
        multiResult.setIdGame(rs.getInt("idgame"));
       
        return multiResult; 
    }
}

