
package springmvc.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import springmvc.domain.HighscoreDisplay;

    public class GameIDMapperResemble implements RowMapper<Integer>{
    
        public Integer mapRow(ResultSet rs, int i) throws SQLException{
        return rs.getInt("idgameresemble");
    }
}