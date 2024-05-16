package dasturlash.uz.repositories;

import dasturlash.uz.dtos.TerminalDTO;
import dasturlash.uz.enums.GeneralStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class TerminalRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public TerminalDTO getTerminalByCode(String code) {
        String sql = "select * from terminal where  visible = true and code = ?";
      List<TerminalDTO> terminals =  jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(TerminalDTO.class), code);
      if(terminals.isEmpty()) {
          return null;
      }
      return terminals.getFirst();
    }

    public Boolean create(TerminalDTO terminal) {
        String sql = "insert into terminal(code,address,status,visible,created_at)" +
                "values(?,?,?,?,?)";

        PreparedStatementSetter preparedStatementSetter = ps -> {
            ps.setString(1,terminal.getCode());
            ps.setString(2,terminal.getAddress());
            ps.setString(3, String.valueOf(terminal.getStatus()));
            ps.setBoolean(4,terminal.getVisible());
            ps.setTimestamp(5, Timestamp.valueOf(terminal.getCreatedAt()));
        };
         var isAdded= jdbcTemplate.update(sql,preparedStatementSetter);
         return isAdded != 0;
    }

    public List<TerminalDTO> terminalLists() {
        String sql = "select * from terminal where visible = true";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(TerminalDTO.class));
    }

    public Boolean update(String code, String address) {

        String sql = "update terminal set address = ? where code = ?";
       var isUpdated =  jdbcTemplate.update(sql,address,code);
       return isUpdated != 0;
    }

    public Boolean changeStatus(String code, GeneralStatus generalStatus) {
        String sql = "update terminal set status = ? where code = ?";
        var isChanged =  jdbcTemplate.update(sql,generalStatus.name(),code);
        return isChanged != 0;
    }

    public Boolean delete(String code) {
        String sql = "update terminal set visible = false where code = ?";
         var isDeleted = jdbcTemplate.update(sql,code);
         return isDeleted != 0;
    }
}
