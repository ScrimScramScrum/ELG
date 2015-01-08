/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import springmvc.domain.MultiChoiceInfo;

/**
 *
 * @author Jorgen
 */
public class MultiChoiceInfoMapper implements RowMapper<MultiChoiceInfo>{
    
    @Override
    public MultiChoiceInfo mapRow(ResultSet rs, int i) throws SQLException{
        MultiChoiceInfo mci = new MultiChoiceInfo(); 
        mci.setName(rs.getString("gamename"));
        mci.setLearningGoal(rs.getString("learing_goals"));
        mci.setInfo(rs.getString("info"));
        mci.setDifficulty(rs.getInt("difficulty"));
        return mci; 
    }
}
