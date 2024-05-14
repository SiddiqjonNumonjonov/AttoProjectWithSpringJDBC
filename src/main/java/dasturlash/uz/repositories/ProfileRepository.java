package dasturlash.uz.repositories;

import dasturlash.uz.dtos.ProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class ProfileRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public Boolean create(ProfileDTO profileDTO) {
        String sql = "insert into profile(name,surname,password,phone,role,status,created_at,visible)" +
                " values (?,?,?,?,?,?,?,?)";

          PreparedStatementSetter preparedStatementSetter = ps -> {
              ps.setString(1,profileDTO.getName());
              ps.setString(2,profileDTO.getSurname());
              ps.setString(3,profileDTO.getPassword());
              ps.setString(4,profileDTO.getPhone());
              ps.setString(5, String.valueOf(profileDTO.getRole()));
              ps.setString(6, String.valueOf(profileDTO.getStatus()));
              ps.setTimestamp(7, Timestamp.valueOf((profileDTO.getCreatedAt())));
              ps.setBoolean(8,profileDTO.getVisible());
          };

           var isAdded = jdbcTemplate.update(sql,preparedStatementSetter);
           return isAdded != 0;

//            var isSaved = jdbcTemplate.update(sql,profileDTO.getName(),profileDTO.getSurname(),profileDTO.getPassword(),profileDTO.getPhone(),
//                profileDTO.getRole(),profileDTO.getStatus(),profileDTO.getCreatedAt(),profileDTO.getVisible());
//          return isSaved != 0;

    }
    public ProfileDTO getProfileById(Integer id) {
        String sql = "select * from profile where id = ?";
        List<ProfileDTO> profile = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(ProfileDTO.class),id);
        if(profile.isEmpty()) {
            return null;
        }
        return profile.getFirst();
    }
}
