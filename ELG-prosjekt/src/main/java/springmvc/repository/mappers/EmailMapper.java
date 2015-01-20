
package springmvc.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import springmvc.domain.HighscoreDisplay;

/**
 *
 * @author eiriksandberg
 */
    public class EmailMapper implements RowMapper<String>{
    
        public String mapRow(ResultSet rs, int i) throws SQLException{
        return rs.getString("email");
    }
}