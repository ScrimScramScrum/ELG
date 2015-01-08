package springmvc.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import springmvc.domain.MultiChoiceInfo;

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
