package springmvc.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ClassMapper implements RowMapper<String>{
    
    public String mapRow(ResultSet rs, int i) throws SQLException{
        return rs.getString("classname");
    }
}
