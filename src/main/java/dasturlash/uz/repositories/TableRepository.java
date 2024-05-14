package dasturlash.uz.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TableRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void createTables() {
        String sqlForProfile = "create table if not exists profile(" +
                "id serial primary key," +
                "name varchar(25) not null," +
                "surname varchar(25) not null," +
                "phone varchar(13) unique not null," +
                "password varchar(50) not null," +
                "created_at timeStamp default now()," +
                "visible boolean default true," +
                "status varchar(25) not null," +
                "role varchar(25) not null" +
                ")";

        jdbcTemplate.execute(sqlForProfile);
    }
}
