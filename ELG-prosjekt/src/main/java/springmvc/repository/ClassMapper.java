package springmvc.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import springmvc.ui.AddNewClassId;


public class ClassMapper implements RowMapper<AddNewClassId> {
    @Override
    public AddNewClassId mapRow(ResultSet rs, int i) throws SQLException {
        AddNewClassId addNewClassId = new AddNewClassId();
        addNewClassId.setClassId(rs.getString("classname"));        
        return addNewClassId;
    }
}