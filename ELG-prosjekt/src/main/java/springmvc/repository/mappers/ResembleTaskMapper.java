/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import springmvc.domain.ResembleTask;

/**
 *
 * @author Jorgen
 */
public class ResembleTaskMapper implements RowMapper<ResembleTask>{
    
    @Override
    public ResembleTask mapRow(ResultSet rs, int i) throws SQLException{
        ResembleTask rt = new ResembleTask(); 
        rt.setTaskText(rs.getString("taskText"));
        rt.setSolutionHTML(rs.getString("solutionHTML"));
        rt.setSolutionCSS(rs.getString("solutionCSS"));
        rt.setStartingHTML(rs.getString("startingHTML"));
        rt.setStartingCSS(rs.getString("startingCSS"));
        rt.setWidth(rs.getInt("width"));
        rt.setHeight(rs.getInt("height"));
        rt.setTaskNumber(rs.getInt("idTask"));
        return rt; 
    }
}
