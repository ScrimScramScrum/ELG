/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springmvc.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.jdbc.core.RowMapper;
import springmvc.domain.Exercise;
import springmvc.domain.MultiChoice;

/**
 *
 * @author eiriksandberg
 */
    public class MultiChoiceExerciseMapper implements RowMapper<Exercise>{
    
    public Exercise mapRow(ResultSet rs, int i) throws SQLException{
        Exercise e = new Exercise();
        String[] alternatives = {rs.getString("alternative_1"), rs.getString("alternative_2"), rs.getString("alternative_3"), rs.getString("alternative_4")};
        e.setAlternatives(alternatives);
        e.setSolution(rs.getString("solution"));
        e.setTaskText(rs.getString("task_text"));
        return e;
    }
}
