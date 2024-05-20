package dasturlash.uz.repositories;

import dasturlash.uz.dtos.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public class TransactionRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public Boolean create(TransactionDTO transactionDTO) {
        String sql = "insert into transactions(card_id,amount,types,created_at,terminal_id)values(?,?,?,?,?)";
        PreparedStatementSetter preparedStatementSetter = ps -> {
            ps.setInt(1,transactionDTO.getCardId());
            ps.setDouble(2,transactionDTO.getAmount());
            ps.setString(3, String.valueOf(transactionDTO.getType()));
            ps.setTimestamp(4, Timestamp.valueOf(transactionDTO.getCreatedAt()));
            ps.setInt(5,transactionDTO.getTerminalId());
        };
       var isAdded = jdbcTemplate.update(sql,preparedStatementSetter);
       return isAdded != 0;
    }
}
