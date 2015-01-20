/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springmvc.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import springmvc.domain.HighscoreDisplay;

/**
 *
 * @author eiriksandberg
 */
    public class GameIDMapper implements RowMapper<Integer>{
    
        public Integer mapRow(ResultSet rs, int i) throws SQLException{
        return rs.getInt("idgamemulti");
    }
}