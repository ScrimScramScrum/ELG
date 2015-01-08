/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import springmvc.domain.MultiChoice;

/**
 *
 * @author Jorgen
 */
public class MultiChoiceMapper implements RowMapper<MultiChoice>{
    public MultiChoice mapRow(ResultSet rs, int i) throws SQLException{
        MultiChoice multiChoice = new MultiChoice();
        return multiChoice; 
    }
}
