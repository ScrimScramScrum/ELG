package springmvc.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.jdbc.core.RowMapper;
import springmvc.domain.Exercise;
import springmvc.domain.MultiChoice;

public class MultiChoiceMapper implements RowMapper<MultiChoice>{
    
    public MultiChoice mapRow(ResultSet rs, int i) throws SQLException{
        MultiChoice multiChoice = new MultiChoice();
        multiChoice.setGameid(rs.getInt("idGame"));
        multiChoice.setName(rs.getString("gamename"));
        return multiChoice; 
    }
}
