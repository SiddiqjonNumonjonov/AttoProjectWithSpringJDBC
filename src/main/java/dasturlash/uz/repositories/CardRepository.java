package dasturlash.uz.repositories;

import dasturlash.uz.dtos.CardDTO;
import dasturlash.uz.enums.GeneralStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Repository
public class CardRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public CardDTO getCardByCardNumber(String cardNumber) {
        String sql = "select * from card where cardNumber = ?";
        List<CardDTO> cardDTOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(CardDTO.class),cardNumber);

        if(cardDTOS.isEmpty()) {
            return null;
        }
        return cardDTOS.getFirst();
    }

    public Boolean add(CardDTO card) {
        String sql = "insert into card(cardNumber,expired_date,balance,status,visible,created_at) values(?,?,?,?,?,?)";

        PreparedStatementSetter preparedStatementSetter = ps -> {
            ps.setString(1,card.getCardNumber());
            ps.setDate(2, Date.valueOf(card.getExpiredDate()));
            ps.setDouble(3,card.getBalance());
            ps.setString(4, String.valueOf(card.getStatus()));
            ps.setBoolean(5,card.getVisible());
            ps.setTimestamp(6, Timestamp.valueOf(card.getCreatedAt()));
        };

         var isAdded = jdbcTemplate.update(sql,preparedStatementSetter);
         return isAdded != 0;
    }

    public List<CardDTO> cardLists() {
        String sql = "select * from card where visible = true";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(CardDTO.class));
    }

    public Boolean update(String cardNumber, LocalDate expiredDate) {

        String sql = "update card set expired_date = ? where cardNumber = ?";
        var isUpdated = jdbcTemplate.update(sql,expiredDate,cardNumber);
        return isUpdated != 0;
    }

    public Boolean changeStatus(String cardNumber, GeneralStatus generalStatus) {
        String sql = "update card set status = ? where cardNumber = ?";
        var isUpdated = jdbcTemplate.update(sql,generalStatus.name(),cardNumber);
        return isUpdated != 0;
    }

    public Boolean delete(String cardNumber) {
        String sql = "update card set visible = false where cardNumber = ?";
        var isUpdated = jdbcTemplate.update(sql,cardNumber);
        return isUpdated != 0;
    }

    public Boolean updateCardBalance(String cardNumber, double amount) {
        String sql = "update card set balance =  balance + ?  where cardNumber = ?";
        var isUpdated = jdbcTemplate.update(sql,amount,cardNumber);
        return isUpdated != 0;
    }
    public void minusBalance(String cardNumber , Double amount) {
        String sql = "update card set balance =  balance - ?  where cardNumber = ?";
        jdbcTemplate.update(sql,amount,cardNumber);
    }
}
