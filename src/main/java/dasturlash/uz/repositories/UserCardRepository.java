package dasturlash.uz.repositories;

import dasturlash.uz.dtos.CardDTO;
import dasturlash.uz.dtos.ProfileCard;
import dasturlash.uz.enums.GeneralStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserCardRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Boolean connectCardToProfile(ProfileCard profileCard) {
        String sql = "insert into profileCard(card_id,profile_id,visible,created_at)" +
                "values (?,?,?,?)";
        var isConnected = jdbcTemplate.update(sql, profileCard.getCardId(), profileCard.getProfileId(),
                profileCard.getVisible(), profileCard.getCreatedAt());
        return isConnected != 0;
    }

    public List<ProfileCard> cardLists(Integer id) {

        String sql = "select profileCard.visible as visible,card.id as card_id,profileCard.id as id, card.cardNumber as card_number, card.expired_date as expired_date," +
                " card.balance as card_balance,profileCard.created_at as joined_date from profileCard" +
                " inner join card on card.id = profileCard.card_id " +
                "inner join profile on profileCard.profile_id = profile.id " +
                "where profile.id = ? and profileCard.visible = true ";

        RowMapper<ProfileCard> rowMapper = new RowMapper<ProfileCard>() {
            @Override
            public ProfileCard mapRow(ResultSet rs, int rowNum) throws SQLException {

                ProfileCard profileCard = new ProfileCard();
                CardDTO cardDTO = new CardDTO();
                cardDTO.setId(rs.getInt("card_id"));
                cardDTO.setCardNumber(rs.getString("card_number"));
                cardDTO.setExpiredDate(String.valueOf(rs.getDate("expired_date")));
                cardDTO.setBalance(rs.getDouble("card_balance"));

                profileCard.setCreatedAt(rs.getTimestamp("joined_date").toLocalDateTime());
                profileCard.setCardDTO(cardDTO);
                profileCard.setId(rs.getInt("id"));
                profileCard.setVisible(rs.getBoolean("visible"));
                return profileCard;
            }
        };
        return jdbcTemplate.query(sql,rowMapper,id);
    }

    public Boolean changeStatus(String cardNumber, GeneralStatus status) {
        String sql = "update card set status = ? where cardNumber = ?";
        var isChanged = jdbcTemplate.update(sql,status.name(),cardNumber);
        return isChanged != 0;
    }

    public Boolean delete(Integer id) {
        String sql = "update profileCard set visible = 'false' where card_id = ?";
        var isChanged = jdbcTemplate.update(sql,id);
        return isChanged != 0;
    }
}